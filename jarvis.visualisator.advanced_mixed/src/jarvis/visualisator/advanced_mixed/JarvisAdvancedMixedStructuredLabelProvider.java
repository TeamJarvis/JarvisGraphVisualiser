package jarvis.visualisator.advanced_mixed;

import jarvis.graph.visualiser.source.DefaultNode;
import jarvis.graph.visualiser.source.INode;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.zest.core.viewers.IFigureProvider;

public class JarvisAdvancedMixedStructuredLabelProvider extends LabelProvider implements IFigureProvider {

	@Override
	public String getText(Object element) {
		if(element instanceof INode) {
			return ((INode)element).getTitle();
		}
		else if(element instanceof String) {
			System.out.println("Instanca je stringa " + element);
		}
		return "";
	}
	
	@Override
	public IFigure getFigure(Object element) {
		if(element instanceof DefaultNode) {
			Ellipse el = new Ellipse();
			ToolbarLayout layout = new ToolbarLayout();
			el.setLayoutManager(layout);
			Label l = new Label(((DefaultNode)element).getTitle());
			Font classFont = new Font(null, "Arial", 6, SWT.BOLD);
			l.setFont(classFont);
			el.setSize(l.getPreferredSize().width+10, l.getPreferredSize().height+10);
			el.setBackgroundColor(new Color(null, 255, 255, 206));
			el.setOpaque(true);
			el.add(l);
			return el;
		}
		else if(element instanceof INode) {
			RectangleFigure rect = new RectangleFigure();
			ToolbarLayout layout = new ToolbarLayout();
			rect.setLayoutManager(layout);
			Label l = new Label(((INode)element).getTitle());
			Font classFont = new Font(null, "Arial", 6, SWT.BOLD);
			l.setFont(classFont);
			rect.setSize(l.getPreferredSize().width+10, l.getPreferredSize().height+10);
			rect.setBackgroundColor(new Color(null, 255, 255, 206));
			rect.setOpaque(true);
			rect.add(l);
			return rect;
		}
		return null;
	}

}
