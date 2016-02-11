package jarvis.graph.visualiser.core.views.tree;


import jarvis.graph.visualiser.core.views.JarvisGraphViewer;
import jarvis.graph.visualiser.source.INode;

import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphItem;
import org.eclipse.zest.core.widgets.GraphNode;

public class TreeView extends ViewPart {
	public static final String ID = "JarvisGraphVisualiser.view";

	protected TreeViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		//viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		FilteredTree filteredTree = new FilteredTree(parent, SWT.BORDER, new PatternFilter(), true);
		viewer = filteredTree.getViewer();
		viewer.setContentProvider(new JarvisTreeContentProvider());
		viewer.setLabelProvider(new JarvisTreeLabelProvider());
		viewer.setUseHashlookup(true);

		viewer.addDoubleClickListener(new IDoubleClickListener() {

		      @Override
		      public void doubleClick(DoubleClickEvent event) {
		        TreeViewer viewer = (TreeViewer) event.getViewer();
		        IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
		        Object selectedNode = thisSelection.getFirstElement();
		        viewer.setExpandedState(selectedNode,!viewer.getExpandedState(selectedNode));

		        if(selectedNode instanceof INode)
		        	selectGraphNode(selectedNode);
		      }
		});
		
	}
	/**
	 * Select node on graph
	 */
	private void selectGraphNode(Object selectedNode) {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = activePage.findView(JarvisGraphViewer.ID);
		JarvisGraphViewer jarvisGraphViewer = (JarvisGraphViewer)view;
		GraphViewer graphViewer = jarvisGraphViewer.getGraphViewer();
		Graph graph =  graphViewer.getGraphControl();
		List<GraphNode> nodes = graph.getNodes();
		for(GraphNode gNode : nodes)
		{
			INode nodee = (INode) gNode.getData();
			INode selected = (INode)selectedNode;
//			if(gNode.getData().equals(selectedNode))
//			{
//				graph.setSelection(new GraphItem[] { gNode });
//			}
			if(nodee.getTitle().equals(selected.getTitle()))
			{
				graph.setSelection(new GraphItem[] { gNode });
			}
		}
		
	}

	@Override
	public void setFocus() {
		
	}
	
	public TreeViewer getViewer() {
		return viewer;
	}
	
	public void setViewer(TreeViewer viewer) {
		this.viewer = viewer;
	}
}