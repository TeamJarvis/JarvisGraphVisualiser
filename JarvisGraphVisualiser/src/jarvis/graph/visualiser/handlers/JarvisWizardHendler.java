package jarvis.graph.visualiser.handlers;

import jarvis.graph.visualiser.core.wizard.JarvisWizardDialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;

public class JarvisWizardHendler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		JarvisWizardDialog dialog = new JarvisWizardDialog(Display.getCurrent().getActiveShell());
		dialog.open();
		
		return null;
	}

}
