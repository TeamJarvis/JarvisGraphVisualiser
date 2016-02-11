package jarvis.graph.visualiser.core.wizard;

import jarvis.graph.visualiser.core.wizard.util.JarvisWizardSelection;
import jarvis.graph.visualiser.core.wizard.util.JarvisWizardUtil;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class JarvisWizardDialog extends Dialog {
	
	public static String Compose = "Compose";
	public static String Cancel = "Cancel";
	
	protected Label sources;
	protected Label visualisators;
	
	protected Combo sourcesCombo;
	protected Combo visualisatorsCombo;
	
	public JarvisWizardDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected Point getInitialSize() {
	    return new Point(450, 420);
	}
	
	@Override
	protected Button createButton(Composite arg0, int arg1, String arg2, boolean arg3) 
	{
		//Retrun null so that no default buttons like 'OK' and 'Cancel' will be created
		return null;
	}
	
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.getShell().setText("Compose new graph view");
		
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
	    layout.numColumns = 2;
		
		prepare(composite);
		
		initButtons(parent);
		
		return parent;
	}
	
	protected void initButtons(Composite composite){
		
		Composite buttonComposite = new Composite(composite, SWT.NONE); 
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL); 
		fillLayout.spacing = 10; 
		buttonComposite.setLayout(fillLayout);
		
		Button addButton = new Button(buttonComposite, SWT.PUSH); 
		addButton.setText(Compose);
		addButton.addSelectionListener(new JarvisWizardSelection(sourcesCombo,visualisatorsCombo,this));
		
		Button removeButton = new Button(buttonComposite, SWT.PUSH); 
		removeButton.setText(Cancel);
		removeButton.addSelectionListener(new JarvisWizardSelection(this));
		
	}
	
	private void prepare(Composite container){
		
		sources = new Label(container, SWT.NULL);
	    sources.setText("Choose source model to visualise");
	    
	    sourcesCombo = new Combo(container, SWT.DROP_DOWN|SWT.READ_ONLY);
	    sourcesCombo.setItems(JarvisWizardUtil.getSources());
	    
	    
		visualisators = new Label(container, SWT.NULL);
	    visualisators.setText("Choose source model visualisation style");
	    
	    visualisatorsCombo = new Combo(container, SWT.DROP_DOWN|SWT.READ_ONLY);
	    visualisatorsCombo.setItems(JarvisWizardUtil.getVisuelisator());
	    
	}

}
