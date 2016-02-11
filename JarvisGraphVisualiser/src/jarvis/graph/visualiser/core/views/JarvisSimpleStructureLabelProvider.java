package jarvis.graph.visualiser.core.views;

import jarvis.graph.visualiser.source.INode;

import org.eclipse.jface.viewers.LabelProvider;

public class JarvisSimpleStructureLabelProvider extends LabelProvider{
	
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

}
