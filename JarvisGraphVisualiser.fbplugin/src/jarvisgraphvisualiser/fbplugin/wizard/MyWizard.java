package jarvisgraphvisualiser.fbplugin.wizard;

import org.eclipse.jface.wizard.Wizard;

public class MyWizard extends Wizard {
	
	private FBParams params;
	private MyPage page;

	public MyWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	@Override
	public void addPages() {
		page = new MyPage();
		addPage(page);
	}
	
	@Override
	public boolean performFinish() {
		
		params = new FBParams(page.getUserId().getText(),
							  page.getAccessToken().getText(),
							  page.getNoFriends().getText());
		
		return true;
	}

	public FBParams getParams() {
		return params;
	}

	public void setParams(FBParams params) {
		this.params = params;
	}

}
