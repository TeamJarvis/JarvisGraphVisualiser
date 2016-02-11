package jarvis.graph.visualiser.core.wizard.util;

import jarvis.graph.visualiser.core.wizard.JarvisWizardDialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;

public class JarvisWizardSelection extends SelectionAdapter {

	protected Combo sourceName;
	protected Combo viewName;
	protected Dialog dialog;

	public JarvisWizardSelection(Combo sourceName, Combo viewName, Dialog dialog) {
		super();
		this.sourceName = sourceName;
		this.viewName = viewName;
		this.dialog = dialog;

	}

	public JarvisWizardSelection(Dialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		super.widgetSelected(e);

		Button button = (Button) e.getSource();

		if (button.getText().equals(JarvisWizardDialog.Compose)) {
			try {
				test(sourceName.getItem(sourceName.getSelectionIndex()),
					 viewName.getItem(viewName.getSelectionIndex()));
				JarvisWizardUtil.doMagic(viewName.getItem(viewName.getSelectionIndex()),
										 sourceName.getItem(sourceName.getSelectionIndex()), "");
				
				dialog.close();
				
			} catch (NotValidException em) {
				MessageDialog.openError(Display.getCurrent().getActiveShell(),
						"Error", em.getMessage());
			} catch (IllegalArgumentException e2) {
				MessageDialog.openError(Display.getCurrent().getActiveShell(),
						"Error", "Source and View must be selected!");
			}
		}else{
			dialog.close();
		}
	}

	private void test(String source, String view) throws NotValidException {
		if (source == null || view == null) {
			throw new NotValidException("Source and View must be selected!");
		}
	}
}
