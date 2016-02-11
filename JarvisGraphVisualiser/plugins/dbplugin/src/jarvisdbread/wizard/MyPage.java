package jarvisdbread.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MyPage extends WizardPage {
	
	protected Text urlValue;
	protected Text usernameValue;
	protected Text passwordValue;
	protected Text driverValue;
	
	protected Label username;
	protected Label url;
	protected Label password;
	protected Label driver;
	
	public MyPage() {
		super("Datebase parameters");
	    setTitle("Datebase parameters");
	    setDescription("DB Wizard. Enter parameters to connect to DB and obtain tables.");
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		
	    container.setLayout(layout);
	    layout.numColumns = 2;
	    
	    prepare(container);
	    
	    // Required to avoid an error in the system
	    setControl(container);
	    setPageComplete(false);

	  }

	public Text getDriverValue() {
		return driverValue;
	}

	public Text getUrlValue() {
		return urlValue;
	}

	public void setUrlValue(Text urlValue) {
		this.urlValue = urlValue;
	}

	public Text getUsernameValue() {
		return usernameValue;
	}

	public void setUsernameValue(Text usernameValue) {
		this.usernameValue = usernameValue;
	}

	public Text getPasswordValue() {
		return passwordValue;
	}

	public void setPasswordValue(Text passwordValue) {
		this.passwordValue = passwordValue;
	}
	
	private void prepare(Composite container){
		
		url = new Label(container, SWT.NULL);
	    url.setText("Enter Datebase URL");
	    
	    urlValue = new Text(container, SWT.BORDER | SWT.SINGLE);
	    urlValue.setText("jdbc:jtds:sqlserver://MILOS-PC/Test");
	    urlValue.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				Text myurl = (Text)e.getSource();
				
				if (!myurl.getText().isEmpty() && !driverValue.getText().isEmpty()) {	
					setDescription("OK that is all informations JARVIS need.Pres Finish to show graph");
					setPageComplete(true);
				}else{
					setDescription("DB Wizard. Enter parameters to connect to DB and obtain tables.");
					setPageComplete(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	    
	    username = new Label(container, SWT.NULL);
	    username.setText("Enter username");
	    
	    usernameValue = new Text(container, SWT.BORDER | SWT.SINGLE);
	    usernameValue.setText("E13296");
	    
	    password = new Label(container, SWT.NULL);
	    password.setText("Enter password");
	    
	    passwordValue = new Text(container, SWT.BORDER | SWT.SINGLE);
	    passwordValue.setText("E13296");
	    
	    driver = new Label(container, SWT.NULL);
	    driver.setText("Enter JDBC name");
	    
	    driverValue = new Text(container,SWT.BORDER | SWT.SINGLE);
	    driverValue.setText("net.sourceforge.jtds.jdbc.Driver");
	    
	    driverValue.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				Text mydriver = (Text)e.getSource();
				
				if (!url.getText().isEmpty() && !mydriver.getText().isEmpty()) {	
					setDescription("OK that is all informations JARVIS need.Pres Finish to show graph");
					setPageComplete(true);
				}else{
					setDescription("DB Wizard. Enter parameters to connect to DB and obtain tables.");
					setPageComplete(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	    
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    urlValue.setLayoutData(gd);
	    driverValue.setLayoutData(gd);

	}


}
