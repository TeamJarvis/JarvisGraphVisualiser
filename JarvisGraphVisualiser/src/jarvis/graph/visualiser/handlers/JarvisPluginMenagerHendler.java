package jarvis.graph.visualiser.handlers;

import jarvis.graph.visualiser.core.pluginmenager.JarvisPluginMenagerDialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;

public class JarvisPluginMenagerHendler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		JarvisPluginMenagerDialog dialod = new JarvisPluginMenagerDialog(Display.getDefault().getActiveShell());
		dialod.open();
		
		return null;
	}

}
