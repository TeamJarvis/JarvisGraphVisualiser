package jarvisdbread;

import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultButtonModel;

import jarvis.graph.visualiser.source.ISource;
import jarvis.graph.visualiser.source.ISourceFactory;
import jarvisdbread.wizard.DBParams;
import jarvisdbread.wizard.MyWizard;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class SourceFactory implements ISourceFactory{
	
	private String name = "DB source model [TeamJarvis]";
	private String id ="jarvis.db.source.reader";
	
	@Override
	public ISource getSource() {
		
		MyWizard mywizard = new MyWizard();
		
		WizardDialog wizard = new WizardDialog(getShell(), mywizard);
		wizard.setBlockOnOpen(true);
		
		if (wizard.open() == Window.OK) {
			
			DBParams params = mywizard.getParams();
			
			MySource source = new MySource(params);
			
			return source;
			
		}
		
		return null;
	}
	
	private Shell getShell(){
		
		IWorkbench wb = PlatformUI.getWorkbench(); 
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		
		return win.getShell();
	}

	@Override
	public String getPluginName() {
		
		return name;
	}

	@Override
	public String getPluginID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public ISource getSource(String path) {
		try{
		String rePath = path.replace(';', '/');
		path = rePath;
			
		String[] params = path.split("\\?");
		DBParams dbParams = new DBParams(params[0], params[1], params[2], params[3]);
		
		MySource source = new MySource(dbParams);
		
		return source;
		
		}catch (PatternSyntaxException e) {
			MessageDialog.openWarning(Display.getCurrent().getActiveShell(), "Error", "Wrong URL");
		}
		
		return null;
	}

}
