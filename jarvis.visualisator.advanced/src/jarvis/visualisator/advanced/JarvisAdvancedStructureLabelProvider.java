package jarvis.visualisator.advanced;

import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribModel;
import jarvis.graph.visualiser.source.IAttribute;
import jarvis.graph.visualiser.source.INode;
import jarvis.visualisator.util.CompartmentFigure;
import jarvis.visualisator.util.JarvisFigure;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.zest.core.viewers.IFigureProvider;

public class JarvisAdvancedStructureLabelProvider extends LabelProvider implements IFigureProvider{
	
	@Override
	public String getText(Object element) {
		if(element instanceof INode)
		{
			return ((INode)element).getTitle();
		}
		else if(element instanceof String)
		{
			System.out.println("Instanca je stringa " + element);
		}
		return "";
	}
			
	
	public static IFigure createClassFigure1(Font classFont, Image classImage,
			Image publicField, INode node) {
		Label classLabel1 = new Label(node.getTitle(), classImage);
		classLabel1.setFont(classFont);

		JarvisFigure classFigure = new JarvisFigure(classLabel1);
		if(node != null)
		{
			ArrayList<Label> atts = new ArrayList<Label>();
			
			IAttribModel nodeModel =  node.getAttributes();
			List<IAttribClasses> nodeClasses = nodeModel.getAttribs();
			for(IAttribClasses nodeClass : nodeClasses)
			{
				atts = new ArrayList<Label>();
				for(IAttribute attrib : nodeClass.getAttribs())
				{
					atts.add(new Label (attrib.getAttributeName() + " : " + attrib.getAttributeValue(), publicField));
				}
				classFigure.addCompartmentFigure(new CompartmentFigure(), atts);
			}
		}
		classFigure.setSize(-1, -1);

		return classFigure;
	}

	@Override
	public IFigure getFigure(Object element) {
		if(element instanceof INode)
		{
			INode node = (INode)element;
			Font classFont = new Font(null, "Arial", 12, SWT.BOLD);
			Image circleImage = new Image(Display.getDefault(),
					JarvisFigure.class.getResourceAsStream("circle.png"));
			Image publicField = new Image(Display.getDefault(),
					JarvisFigure.class.getResourceAsStream("methpub_obj.gif"));
			return createClassFigure1(classFont, circleImage, publicField, node);
		}

		return null;
	}

}
