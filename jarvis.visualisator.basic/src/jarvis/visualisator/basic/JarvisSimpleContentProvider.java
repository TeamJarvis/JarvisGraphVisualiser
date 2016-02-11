package jarvis.visualisator.basic;

import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.ISource;

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
	
}
