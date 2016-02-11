package jarvis.graph.visualiser.core.views.tree;

import jarvis.graph.visualiser.source.IAttribModel;
import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribute;
import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.ISource;
import jarvis.graph.visualiser.source.INodeModel;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class JarvisTreeLabelProvider extends LabelProvider {
	
	@Override
	public Image getImage(Object element) {
		if(element instanceof INode){
			
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEF_VIEW);
		}else if(element instanceof INodeModel){
			
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}else if(element instanceof IAttribModel){
			
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ADD);
		}else if(element instanceof IAttribClasses){
			
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
		}else if(element instanceof IAttribute){
			
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ELCL_SYNCED_DISABLED);
		}else if(element instanceof ISource){
			
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ETOOL_HOME_NAV);
		}else if(element instanceof String){
			
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FILE);
		}else{
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ETOOL_DELETE_DISABLED);
		}
		
	}
	
	@Override
	public String getText(Object element) {
		if(element instanceof INode){
			INode elem = (INode)element;
			
			return elem.getTitle();
		}else if(element instanceof INodeModel){
			
			return "Nodes";
		}else if(element instanceof IAttribModel){
			
			return "Attributes";
		}else if(element instanceof IAttribClasses){
			
			return (((IAttribClasses) element).getName());
		}else if(element instanceof IAttribute){
			IAttribute atrib = (IAttribute)element;
			
			return atrib.getAttributeName();
		}else if(element instanceof ISource){
			ISource source = (ISource)element;
			
			return source.getName();
		}else if(element instanceof String){
			String st = (String)element;
			
			return st;
		}else{
			
			return "No value";
		}
		
	}
}
