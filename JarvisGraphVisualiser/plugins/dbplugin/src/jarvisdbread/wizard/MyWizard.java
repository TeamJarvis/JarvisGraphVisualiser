package jarvisdbread.wizard;

import org.eclipse.jface.wizard.Wizard;

public class MyWizard extends Wizard {
	
	private DBParams params;
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
		
		params = new DBParams(page.getUrlValue().getText(),
							  page.getUsernameValue().getText(),
							  page.getPasswordValue().getText(),
							  page.getDriverValue().getText());
		
		return true;
	}

	public DBParams getParams() {
		return params;
	}

	public void setParams(DBParams params) {
		this.params = params;
	}

}
