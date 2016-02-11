package jarvis.graph.visualiser.source.xsdschema.view.dialogs;

import java.io.File;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectFileDialog extends Dialog {

	private Text pathText;
	private String path;

	public SelectFileDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		getShell().setText("Choose .xsd file");
		getShell().setSize(490, 110);
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(3, false);
		container.setLayout(layout);
		Label pathLabel = new Label(container, SWT.NONE);
		pathLabel.setText("Path:");
		GridData pathTextData = new GridData(400, 20);
		pathTextData.horizontalAlignment = SWT.FILL;
		pathTextData.horizontalAlignment = SWT.BEGINNING;
		pathText = new Text(container, SWT.SINGLE | SWT.BORDER);
		pathText.setLayoutData(pathTextData);
		Button chooseFileButton = new Button(container, SWT.PUSH);
		chooseFileButton.setText("...");
		chooseFileButton.setToolTipText("Choose file");
		chooseFileButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(getParentShell(), SWT.OPEN);
				fd.setText("Choose .xsd file");
				fd.setFilterExtensions(new String[] { "*.xsd" });
				String selected = fd.open();
				if (selected != null) {
					pathText.setText(selected.replace('\\', '/'));
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		createButtonBar(container);
		container.pack();
		return container;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Create", true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Control createButtonBar(Composite parent) {
		// TODO Auto-generated method stub
		Control result = super.createButtonBar(parent);
		GridData buttonBarData = (GridData) result.getLayoutData();
		buttonBarData.horizontalSpan = 3;
		buttonBarData.horizontalAlignment = SWT.END;
		return result;
	}

	@Override
	protected void okPressed() {
		// TODO Auto-generated method stub
		try {
			if (!pathText.getText().trim().endsWith(".xsd")) {
				throw new Exception();
			}
			@SuppressWarnings("unused")
			File file = new File(pathText.getText().trim());
			path = pathText.getText().trim();
			super.okPressed();
		} catch (Exception e) {
			MessageDialog.openError(getParentShell(), "Error",
					"There is no .xsd file on selected path");
		}
	}

	public String getPath() {
		return path;
	}

}
