package jarvisgraphvisualiser.fbplugin;

import jarvis.graph.visualiser.source.ISource;
import jarvis.graph.visualiser.source.ISourceFactory;
import jarvisgraphvisualiser.fbplugin.wizard.FBParams;
import jarvisgraphvisualiser.fbplugin.wizard.MyWizard;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class FBFriendship implements ISourceFactory {

	@Override
	public ISource getSource() {
		// String token = "210450945739121";
		// String id = "sebastijan.kaplar";
		// FBGraphUsers fb = new FBGraphUsers(token,id);
		// return fb;

		MyWizard mywizard = new MyWizard();
		WizardDialog wizard = new WizardDialog(getShell(), mywizard);
		wizard.setBlockOnOpen(true);
		if (wizard.open() == Window.OK) {
			
			FBParams params = mywizard.getParams();
//			if(tryParse(params.getNoFriends()) == null)
//				return null;
			
			FBGraphUsers fb = new FBGraphUsers(params.getAccessToken(),
					params.getUserId(), params.getNoFriends());
			return fb;
		}

		return null;
	}

	
	private Shell getShell() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		return win.getShell();
	}

	@Override
	public String getPluginName() {
		return "Fbplugin";
	}

	@Override
	public String getPluginID() {
		return "JarvisGraphVisualiser.fbplugin";
	}

	@Override
	public ISource getSource(String path) {
		  System.out.println(path);
          
//          String rePath = path.replace(';', '/');
//         
//          path = rePath;
//         
//          System.out.println(path);
                 
          String[] params = path.split(":");
         
          for (String string : params) {
                  System.out.println(string);
          }
         
          FBGraphUsers fb = new FBGraphUsers(params[0],
					params[1], params[2]);
          

         
          return fb;
	}

}
