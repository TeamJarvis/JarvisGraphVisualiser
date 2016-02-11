package parser;

import java.io.File;
import java.io.FilenameFilter;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class SelectFileDialog extends Dialog {

	private Text pathText;
	private String path;

	public SelectFileDialog(Shell parentShell) {
		super(parentShell);
		
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		getShell().setText("Choose project");
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
		chooseFileButton.setToolTipText("Choose project");
		chooseFileButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(getParentShell());

				dialog.setText("Choose project");

				String selected = dialog.open();
				if (selected != null) {
					pathText.setText(selected.replace("/", File.pathSeparator));
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
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OPEN_LABEL, true);
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
		path = pathText.getText().trim();
		super.okPressed();
	}

	public String getPath() {
		return path;
	}

	class FolderFilter implements FilenameFilter {
		public boolean accept(File dir, String name) {
			return new File(dir, name).isDirectory();
		}
	}

}
