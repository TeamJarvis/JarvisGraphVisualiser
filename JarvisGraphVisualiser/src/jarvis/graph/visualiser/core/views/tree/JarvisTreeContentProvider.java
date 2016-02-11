package jarvis.graph.visualiser.core.views.tree;

import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribModel;
import jarvis.graph.visualiser.source.IAttribute;
import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.INodeModel;
import jarvis.graph.visualiser.source.ISource;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class JarvisTreeContentProvider implements ITreeContentProvider {
	
	private boolean treeInputSet = false;
	private Object[] EMPTY_ARRAY = new Object[0];
	private ISource source;
	
	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		source = (ISource)newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (treeInputSet)
        {
			return getChildren(inputElement);
        }
        else
        {
            treeInputSet = true;
            return new Object[] { inputElement };
        }

	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof INode){
			INode node = (INode)parentElement;
//			System.out.println("Node je: " + node.getTitle());
			Object[] all = new Object[2];
			all[0] = node.getConnectedNodes();
			all[1] = node.getAttributes();
			
			return all;
		}else if(parentElement instanceof INodeModel){
			INodeModel model = (INodeModel)parentElement;
			
			return model.getNodes().toArray();
		}else if(parentElement instanceof IAttribModel){
			IAttribModel model = (IAttribModel)parentElement;
			
			return model.getAttribs().toArray();
		}else if (parentElement instanceof IAttribClasses) {
			IAttribClasses classes = (IAttribClasses)parentElement;
			
			return classes.getAttribs().toArray();
		}else if(parentElement instanceof IAttribute){
			IAttribute atribs = (IAttribute)parentElement;
			
			Object[] rets = new Object[2];
			
			rets[0] = atribs.getAttributeName();
			rets[1] = atribs.getAttributeValue();
			
			return rets;
			
		}else if(parentElement instanceof ISource){
			ISource source = (ISource)parentElement;
			
			return source.getNodeDataGraph().toArray();
		}
		
		return EMPTY_ARRAY;
	}

	@Override
	public Object getParent(Object element) {
		
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof IAttribute){
			
			return true;
		}else if(element instanceof INode){

			return true;
		}else if(element instanceof ISource){
			
			return true;
		}else if(element instanceof INodeModel){
			
			return (((INodeModel) element).getNodes().size() > 0);
		}else if(element instanceof IAttribModel){
			
			return (((IAttribModel) element).getAttribs().size() > 0);
		}else if(element instanceof IAttribClasses){
			
			return(((IAttribClasses) element).getAttribs().size() > 0);
		}
		
		return false;
	}
	
	private int getRootDistance(Object node, int distance) {
		if (getParent(node) != null) {
			distance++;
			getRootDistance(getParent(node), distance);
		}
		return distance;
	}
}
