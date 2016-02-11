package jarvis.graph.visualiser.handlers;

import jarvis.graph.visualiser.core.about.JarvisAboutDialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;

public class JarvisAboutDialogHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		
		JarvisAboutDialog dialog = new JarvisAboutDialog(Display.getCurrent().getActiveShell());
		dialog.open();
		
		return null;
	}

}
