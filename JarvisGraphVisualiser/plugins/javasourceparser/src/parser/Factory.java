package parser;

import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import jarvis.graph.visualiser.source.ISource;
import jarvis.graph.visualiser.source.ISourceFactory;

public class Factory implements ISourceFactory {

	@Override
	public ISource getSource() {
		SelectFileDialog sfd = new SelectFileDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
	
		int result = sfd.open();

		String path = null;
		if (result == Window.OK) {
			path = sfd.getPath();
		}
		return new JavaSource(path);
	}
	
	@Override
	public ISource getSource(String path) {
		return new JavaSource(path);
	}

	@Override
	public String getPluginName() {
		return "Java Source Parser";
	}

	@Override
	public String getPluginID() {
		return "JavaSourceParser";
	}
}
