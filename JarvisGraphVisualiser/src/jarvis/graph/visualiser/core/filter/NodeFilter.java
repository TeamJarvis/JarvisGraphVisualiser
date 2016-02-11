package jarvis.graph.visualiser.core.filter;
 
import jarvis.graph.visualiser.source.INode;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
 
public class NodeFilter extends ViewerFilter{

	private String text;
	public NodeFilter(String text)
	{
		this.text = text;
	}
	
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof INode) {
			INode node = (INode) element;
			boolean nazad = node.getTitle().toLowerCase()
					.contains(text.toLowerCase());
			return nazad;

		}
		return true;
	}

}
