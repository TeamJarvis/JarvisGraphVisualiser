package jarvis.graph.visualiser.core.filter;

import jarvis.graph.visualiser.core.views.JarvisGraphViewer;
import jarvis.graph.visualiser.util.KeyAdapter;

import org.eclipse.jface.action.ControlContribution;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class JarvisNodeFilter extends ControlContribution {
	
	private Filters filter;
	
	public JarvisNodeFilter() {
		super(null);
	}

	public JarvisNodeFilter(String id) {
		super(id);
		
	}
	
	public enum Filters {
		FILTERNODE("Filter Node"),
		SEARCHNODE("Search Node");
		
		private String title;

		private Filters(String title){
			this.title = title;
		}
		
		@Override
		public String toString(){
			return this.title;
		}
	}
	
	public void setLayout(Filters firstElement) {
		switch(firstElement){
			case FILTERNODE: 
				filter = Filters.FILTERNODE;
				break;
			case SEARCHNODE: 
				filter = Filters.SEARCHNODE;
				break;
		}
		
	}
	
	@Override
	protected Control createControl(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		GridLayout gl_c = new GridLayout(4,false);
		gl_c.marginHeight = 0;
		c.setLayout(gl_c);
		
		Label label = new Label(c, SWT.NONE);
		label.setText("Filter nodes:");
		
		ComboViewer algCombo = new ComboViewer(c, SWT.DROP_DOWN|SWT.READ_ONLY);
		algCombo.setContentProvider(ArrayContentProvider.getInstance());
		algCombo.setInput(Filters.values());
		algCombo.setSelection(new StructuredSelection(Filters.FILTERNODE));
		algCombo.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				setLayout((Filters)((IStructuredSelection)event.getSelection()).getFirstElement());
						
			}
		});
		
		final Text text2Filter = new Text(c, SWT.BORDER | SWT.SINGLE);
		text2Filter.setSize(300, 200);
		text2Filter.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				Text input = (Text)e.getSource();
				
				if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
					filter(input.getText());
				}
				
				if (e.keyCode == SWT.ESC)
				{
					if (text2Filter.getText().trim().length() > 0 || text2Filter.getText().trim().length() == 0) {
						text2Filter.setText("");
						resetFilters();
					}
				}
			}
		}
		);
		
		Button button = new Button(c, SWT.PUSH);
		button.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ELCL_SYNCED));
		button.setToolTipText("Reset filter");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (text2Filter.getText().trim().length() > 0 || text2Filter.getText().trim().length() == 0) {
					text2Filter.setText("");
					resetFilters();
				}
			}
		});
		
		return c;
	}
	
	private void filter(String text)
	{
		
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = activePage.findView(JarvisGraphViewer.ID);

		JarvisGraphViewer graphViewer = (JarvisGraphViewer) view;
		ViewerFilter[] filters = new ViewerFilter[1];
		
		if (filter == Filters.SEARCHNODE) {
			SearchNodeFilter filter = new SearchNodeFilter(text);
			filters[0] = filter;
		}else{
			NodeFilter filter = new NodeFilter(text);
			filters[0] = filter;
		}
		
		graphViewer.getGraphViewer().resetFilters();
		graphViewer.getGraphViewer().setFilters(filters);
		graphViewer.getGraphViewer().applyLayout();
		
	}
	
	public void resetFilters()
	{
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = activePage.findView(JarvisGraphViewer.ID);
		JarvisGraphViewer graphViewer = (JarvisGraphViewer) view;
		graphViewer.getGraphViewer().resetFilters();
		graphViewer.getGraphViewer().applyLayout();
	}
	

}
