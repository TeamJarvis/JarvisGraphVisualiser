package jarvis.graph.visualiser.core.views;

import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.ISource;
import jarvis.graph.visualiser.source.ISourceFactory;
import jarvis.graph.visualiser.visualisator.IVisualisators;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

public class JarvisSimpleContentProvider implements IGraphEntityContentProvider{

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof ISource)
		{
			ISource source = (ISource)inputElement;
			//sa novom verzijom vecina gore je nepotrebna
//			ArrayList<INode> imena = new ArrayList<INode>();
//			Set<INode> keySet = source.getDataGraph().keySet();
//			for(INode node : keySet)
//			{
//				imena.add(node);
//			}
//			return imena.toArray();
			return source.getNodeDataGraph().toArray();
		}
		else
			return new Object[]{inputElement};
	}

	
	@Override
	public Object[] getConnectedTo(Object entity) {
		if(entity instanceof INode)
		{
			return ((INode) entity).getConnectedNodes().getNodes().toArray();
		}
		return null;
	}	
	
	public static ISource initializePlugin() {

		String PLUG_IN_ID = "jarvis.graph.visualiser.source";

		IConfigurationElement[] extensions = Platform.getExtensionRegistry().getConfigurationElementsFor(PLUG_IN_ID);

		for (IConfigurationElement point : extensions) {
			System.out.println(point.getNamespaceIdentifier());
		}

		for (int i = 0; i < extensions.length; i++) {

			IConfigurationElement element = extensions[i];

			if (element.getAttribute("class") != null) {

				try {
					if (element.createExecutableExtension("class") instanceof IVisualisators) {
						IVisualisators top = (IVisualisators) element.createExecutableExtension("class");

					} else {
						ISourceFactory top = (ISourceFactory) element.createExecutableExtension("class");
						ISource source = top.getSource();
						
						if (source != null) {
							System.out.println(source.getName());

							if (source.getDescription() != null) {
								System.out.println("DESCR");
							} else {
								System.out.println("VRATIO NULL");
							}

							HashMap<INode, ArrayList<INode>> graph = source.getDataGraph();

							return source;

						} else {
							// System.out.println("NULL");
						}

					}
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} // ovde kraj
		return null;

	}

	/*
	@Override
	public Object[] getRelationships(Object source, Object dest) {
		if(source instanceof INode && dest instanceof INode)
		{
			INode s = (INode)source;
			INode d = (INode)dest;
			System.out.println("Source je " + s.getTitle());
			System.out.println("Dest je " + d.getTitle());
			if(s.getTitle().equals(d.getTitle()))
				return null;
			for(INode tmp : d.getConnectedNodes())
			{
				
				if(tmp.getTitle().equals(s.getTitle()))
				{
					System.out.println("Source " + s.getTitle() + ", je povezan " + tmp.getTitle() + "i to je mutual");
					Object[] o =  { d };
					return o;
				}

			}
			System.out.println("--------------------------------");
		}


		return null;
	}
*/
}
