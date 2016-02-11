package jarvis.graph.visualiser.core.filter;

import jarvis.graph.visualiser.source.INode;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class JarvisFilterPattern extends ViewerFilter {
	
	private String filter;
	
	public JarvisFilterPattern(String filter) {
		super();
		this.filter = filter;
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		
		if (element instanceof INode) {
			INode node = (INode)element;
			
			return node.getTitle().toLowerCase().contains(filter.toLowerCase());
		}
		
		return false;
	}
	
	public String getFilter() {
		return filter;
	}
	
	public void setFilter(String filter) {
		this.filter = filter;
	}

}
