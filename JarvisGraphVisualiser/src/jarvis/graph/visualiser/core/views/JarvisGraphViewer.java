package jarvis.graph.visualiser.core.views;

import jarvis.graph.visualiser.core.views.tree.TreeView;
import jarvis.graph.visualiser.core.wizard.JarvisNodeDialog;
import jarvis.graph.visualiser.source.INode;

import java.util.List;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphItem;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;


public class JarvisGraphViewer extends ViewPart {

	public static final String ID = "JarvisGraphVisualiser.jarvisgraphviewer";
	IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	
	
	IViewPart view = activePage.findView(JarvisPartDescription.ID);
	JarvisPartDescription graphDescription = (JarvisPartDescription)view;
	
	private GraphViewer graphViewer;

	public enum Layout {
		RADIAL("Radial"),
		TREE("Tree"),
		GRID("Grid"),
		SPRING("Spring");
		
		private String title;

		private Layout(String title){
			this.title = title;
		}
		
		@Override
		public String toString(){
			return this.title;
		}
	}
	
	@Override
	public void createPartControl(final Composite parent) {
		

		graphViewer = new GraphViewer(parent, SWT.NONE);
		graphViewer.addSelectionChangedListener(new ISelectionChangedListener() {
		public void selectionChanged(SelectionChangedEvent event) {
			Graph graph = graphViewer.getGraphControl();
			graph.setParent(parent);
			if(graph.getSelection() != null && !graph.getSelection().isEmpty())
			{
				List<GraphItem> selection = graph.getSelection();
				if(selection.get(0) instanceof GraphNode)
				{
					GraphNode node = (GraphNode) selection.get(0);
					INode node2 = (INode)node.getData();
					graphDescription.setNode(node2);
					graphDescription.sync();
					
					
					
				}
			}
		}
		
		
		});
	
		
		graphViewer.addDoubleClickListener(new IDoubleClickListener() {
			
			@Override
			public void doubleClick(DoubleClickEvent event) {
		        IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
		        final Object selectedNode = thisSelection.getFirstElement();

		        if(selectedNode instanceof INode)
		        {
			        INode node = (INode)selectedNode;
	//		        selectTreeNode(selectedNode);
			        selectTreeNode(node);
			        JarvisNodeDialog d = new JarvisNodeDialog(
							Display.getCurrent()
									.getActiveShell(), node);
					d.open();
		        }	
			}
			
			
		});
		
		

	}
	
	
	
	private void selectTreeNode(Object selectedNode) {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = activePage.findView(TreeView.ID);
		TreeView treeViewer = (TreeView)view;
		if(selectedNode != null && selectedNode instanceof INode)
		{
			INode n = (INode)selectedNode;
			treeViewer.getViewer().setExpandedState(n, !treeViewer.getViewer().getExpandedState(n));
			ISelection sel = new StructuredSelection(selectedNode);
			treeViewer.getViewer().setSelection(sel, true);
		}
		
	}
	
	@Override
	public void setFocus() {
		graphViewer.getControl().setFocus();
	}
	

	public void setLayout(Layout firstElement) {
		switch(firstElement){
		case RADIAL: graphViewer.setLayoutAlgorithm(new RadialLayoutAlgorithm()); break;
		case TREE: graphViewer.setLayoutAlgorithm(new TreeLayoutAlgorithm()); break;
		case GRID: graphViewer.setLayoutAlgorithm(new GridLayoutAlgorithm()); break;
		case SPRING: graphViewer.setLayoutAlgorithm(new SpringLayoutAlgorithm()); break;
		}
		refresh();
	}

	private void refresh() {
		graphViewer.applyLayout();
		graphViewer.refresh();
	}
	
	
	public GraphViewer getGraphViewer() {
		return graphViewer;
	}
	
	public void setGraphViewer(GraphViewer graphViewer) {
		this.graphViewer = graphViewer;
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

}
