package jarvis.graph.visualiser.core.filter;

import jarvis.graph.visualiser.source.INode;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class SearchNodeFilter extends ViewerFilter{

	private String text;
	private INode glavni = null;
	
	public SearchNodeFilter(String text)
	{
		this.text = text;
	}
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
	    if (element instanceof INode) {
	    	INode node = (INode) element;
	    	if(glavni != null)
	    	{
	    		for(INode n : glavni.getConnectedNodes().getNodes())
	    		{
	    			if(n.getTitle().toLowerCase().equals(node.getTitle().toLowerCase()))
	    				return true;
	    		}
	    	}
	    	
	    	boolean nazad = node.getTitle().toLowerCase().equals(text.toLowerCase());
	    	if(nazad)
	    	{
	    		glavni = node;
	    		return nazad;
	    	}
	    	else
	    	{
	    		for(INode n2 : node.getConnectedNodes().getNodes())
	    		{
	    			if(n2.getTitle().toLowerCase().equals(text.toLowerCase()))
	    				return true;
	    		}
	    		return false;
	    	}
	    }
	    
	     return true;
	}

}
