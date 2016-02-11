package jarvis.graph.visualiser.contributions;

import jarvis.graph.visualiser.core.views.JarvisGraphViewer;

import org.eclipse.jface.action.ControlContribution;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;



public class LayoutSelection extends ControlContribution {

	public LayoutSelection(){
		super(null);
	}

	public LayoutSelection(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createControl(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		GridLayout gl_c = new GridLayout(2,false);
		gl_c.marginHeight = 0;
		c.setLayout(gl_c);
		Label label = new Label(c, SWT.NONE);
		label.setText("Layout algorithm");
		ComboViewer algCombo = new ComboViewer(c, SWT.DROP_DOWN|SWT.READ_ONLY);
		algCombo.setContentProvider(ArrayContentProvider.getInstance());
		algCombo.setInput(JarvisGraphViewer.Layout.values());
		algCombo.setSelection(new StructuredSelection(JarvisGraphViewer.Layout.RADIAL));
		
		algCombo.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				final JarvisGraphViewer siteView = (JarvisGraphViewer) PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage().findView(JarvisGraphViewer.ID);
					siteView.setLayout(
						(JarvisGraphViewer.Layout)((IStructuredSelection)event.getSelection()).getFirstElement());
			}
		});		
		
		return c;
	}


}
