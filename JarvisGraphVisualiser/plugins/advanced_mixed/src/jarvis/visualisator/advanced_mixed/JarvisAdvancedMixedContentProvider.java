package jarvis.visualisator.advanced_mixed;

import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.ISource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

public class JarvisAdvancedMixedContentProvider implements IGraphEntityContentProvider {

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
			List<Object> nodes = new ArrayList<Object>();
			nodes.addAll(source.getNodeDataGraph());
			for (INode node : source.getNodeDataGraph()) {
				nodes.addAll(node.getAttCopies());
			}
			return nodes.toArray();
		}
		else
			return new Object[]{inputElement};
	}

	@Override
	public Object[] getConnectedTo(Object entity) {
		if(entity instanceof INode)
		{
			List<Object> conns = new ArrayList<Object>();
			INode node = (INode) entity;
			conns.addAll(node.getConnectedNodes().getNodes());
			conns.addAll(node.getAttCopies());
			return conns.toArray();
		}
		return null;
	}

}
