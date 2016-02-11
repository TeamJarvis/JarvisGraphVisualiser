package jarvis.graph.visualiser.source.xsdschema;

import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribModel;
import jarvis.graph.visualiser.source.IAttribute;
import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.INodeModel;
import jarvis.graph.visualiser.source.ISource;
import jarvis.graph.visualiser.source.ISourceFactory;
import jarvis.graph.visualiser.source.xsdschema.view.dialogs.SelectFileDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;
import org.xml.sax.SAXException;

import com.sun.xml.xsom.XSAttributeDecl;
import com.sun.xml.xsom.XSAttributeUse;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSContentType;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSModelGroup;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSSchema;
import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.XSTerm;
import com.sun.xml.xsom.parser.XSOMParser;

public class XSDSourceFactory implements ISourceFactory {
	
	private List<INode> dataGraph = new ArrayList<INode>();

	@Override
	public ISource getSource() {
		SelectFileDialog sfd = new SelectFileDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell());
		int result = sfd.open();
		if (result == Window.OK) {
			return parseSchema(sfd.getPath());
		}
		return null;
	}

	private ISource parseSchema(String path) {
		XSOMParser parser = new XSOMParser();
		try {
			parser.parse(new File(path));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSchemaSet sset = null;
		try {
			sset = parser.getResult();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] namespaces = new String[sset.getSchemaSize()];
		for (int i = 0; i < sset.getSchemaSize(); i++) {
			namespaces[i] = sset.getSchema(i).getTargetNamespace();
		}
		for (int i = 0; i < namespaces.length; i++) {
			XSSchema schema = sset.getSchema(namespaces[i]);

			Map<String, XSElementDecl> ed = schema.getElementDecls();
			Set<String> keys = ed.keySet();
			for (String key : keys) {
				XSElementDecl decl = ed.get(key);
				List<IAttribute> attributes;
				if (decl.getType().isComplexType()) {
					attributes = getAttributes(decl.getType().asComplexType());
				}
				else {
					attributes = new ArrayList<IAttribute>();
				}
				if (decl.getTargetNamespace().trim().equals("")) {
					attributes.add(0, new XSDAttribute("namespace", decl.getTargetNamespace()));
				}
				else {
					attributes.add(0, new XSDAttribute("namespace", "default"));
				}
				IAttribClasses attribClass = new XSDAttribClasses(attributes, "Attributes");
				List<IAttribClasses> allAttributes = new ArrayList<IAttribClasses>();
				allAttributes.add(attribClass);
				IAttribModel attribModel = new XSDAttribModel(allAttributes);
				List<INode> children = new ArrayList<INode>();
				INodeModel nodeModel = new XSDNodeModel(children);
				XSDNode root = new XSDNode(decl.getName(), attribModel, nodeModel);
				dataGraph.add(root);
				getNodes(decl, root, children);
			}
		}
		String description = "Used namespaces:\n";
		for (int i = 0; i < namespaces.length; i++) {
			description += namespaces[i] + "\n";
		}
		ISource source = new XSDSource(dataGraph.get(0).getTitle(), description, dataGraph);
		//printSource();
		return source;
	}
	
	private void getNodes(XSElementDecl elDecl, INode parent, List<INode> children) {
		try {
			XSContentType xsContentType = elDecl.getType().asComplexType().getContentType();
			XSParticle particle = xsContentType.asParticle();
			if (particle != null)
			{
				XSTerm term = particle.getTerm();
				if (term.isModelGroup())
				{
					XSModelGroup xsModelGroup = term.asModelGroup();
					term.asElementDecl();
					XSParticle[] particles = xsModelGroup.getChildren();
					String propertyName = null;
					for (XSParticle p : particles)
					{
						XSTerm pterm = p.getTerm();
						if (pterm.isElementDecl())
						{            
							propertyName = pterm.asElementDecl().getName();
							List<IAttribute> attributes;
							if (pterm.asElementDecl().getType().isComplexType()) {
								attributes = getAttributes(pterm.asElementDecl().getType().asComplexType());
							}
							else {
								attributes = new ArrayList<IAttribute>();
							}
							if (!pterm.asElementDecl().getTargetNamespace().trim().equals("")) {
								attributes.add(0, new XSDAttribute("namespace", pterm.asElementDecl().getTargetNamespace()));
							}
							else {
								attributes.add(0, new XSDAttribute("namespace", "default"));
							}
							IAttribClasses attribClass = new XSDAttribClasses(attributes, "Attributes");
							List<IAttribClasses> allAttributes = new ArrayList<IAttribClasses>();
							allAttributes.add(attribClass);
							IAttribModel attribModel = new XSDAttribModel(allAttributes);
							List<INode> childChildren = new ArrayList<INode>();
							INodeModel nodeModel = new XSDNodeModel(childChildren);
							XSDNode child = new XSDNode(propertyName, attribModel, nodeModel);
							dataGraph.add(child);
							children.add(child);
						}
					}
					int i = 0;
					for (XSParticle p : particles)
					{
						XSTerm pterm = p.getTerm();
						if (pterm.isElementDecl())
						{
							getNodes(pterm.asElementDecl(), children.get(i), children.get(i).getConnectedNodes().getNodes());
							i++;
						}
					}
				}
			}
		} catch (Exception e) {
			return;
		}
	}
	
	private List<IAttribute> getAttributes(XSComplexType ct) {
		List<IAttribute> attributes = new ArrayList<IAttribute>();
		
		Collection<? extends XSAttributeUse> c = ct.getAttributeUses();
	    Iterator<? extends XSAttributeUse> i = c.iterator();
	    while (i.hasNext())
	    {
	      XSAttributeDecl attributeDecl = i.next().getDecl();
	      IAttribute attribute = new XSDAttribute(attributeDecl.getName(), attributeDecl.getType().getName());
	      attributes.add(attribute);
	    }
	    return attributes;
	}
	
	private void printSource() {
		int indent = 0;
		for (INode key : dataGraph) {
			printNTabs(indent);			
			System.out.println("Nadelement: " + key.getTitle());
			printNTabs(indent+1);
			System.out.println("Atributi:");
			List<IAttribute> attributes = key.getAttributes().getAttribs().get(0).getAttribs();
			for (IAttribute attribute : attributes) {
				printNTabs(indent+1);
				System.out.println(attribute.getAttributeName() + ": " + attribute.getAttributeValue());
			}
			printNTabs(indent+1);
			System.out.println("Podelementi:");
			for (INode child : key.getConnectedNodes().getNodes()) {
				printNode(child, indent+2);
			}
		}
	}
	
	private void printNode(INode node, int indent) {
		printNTabs(indent);
		System.out.println("Nadelement: " + node.getTitle());
		printNTabs(indent+1);
		System.out.println("Atributi:");
		List<IAttribute> attributes = node.getAttributes().getAttribs().get(0).getAttribs();
		for (IAttribute attribute : attributes) {
			printNTabs(indent+2);
			System.out.println(attribute.getAttributeName() + ": " + attribute.getAttributeValue());
		}
		printNTabs(indent+1);
		System.out.println("Podelementi:");
		for (INode child : node.getConnectedNodes().getNodes()) {
			printNode(child, indent+2);
		}
	}
	
	private void printNTabs(int n) {
		for (int i=0; i< n; i++) {
			System.out.print("\t");
		}
	}

	@Override
	public String getPluginName() {
		return "Xsdschema";
	}
	
	@Override
	public String getPluginID() {
		return "jarvis.graph.visualiser.source.xsdschema";
	}

	@Override
	public ISource getSource(String path) {
		return parseSchema(path);
	}

}
