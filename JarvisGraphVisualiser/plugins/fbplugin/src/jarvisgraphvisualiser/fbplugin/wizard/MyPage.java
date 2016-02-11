package jarvisgraphvisualiser.fbplugin.wizard;

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
	
	protected Text accessToken;
	protected Text userId;
	protected Text noFriends;

	protected Label accessTokenLabel;
	protected Label userIdLabel;
	protected Label noFriendsLabel;
	
	public MyPage() {
		super("FB Connector");
	    setTitle("FB Connector");
	    setDescription("FB Connection Wizard.");
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


	private void prepare(Composite container){
		
		accessTokenLabel = new Label(container, SWT.NULL);
		accessTokenLabel.setText("Access Token");
	    
		accessToken = new Text(container, SWT.BORDER | SWT.SINGLE);
		accessToken.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				Text myurl = (Text)e.getSource();
				
				if (!myurl.getText().isEmpty()) {	
					setPageComplete(true);
				}else{
					setDescription("Insufficient data, please fill all fields.");
					setPageComplete(false);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	    
		userIdLabel = new Label(container, SWT.NULL);
		userIdLabel.setText("FB User Id");
	    
		userId = new Text(container, SWT.BORDER | SWT.SINGLE);
	    

		userId.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				Text id = (Text)e.getSource();
				
				if (!userId.getText().isEmpty() && !id.getText().isEmpty()) {	
					setPageComplete(true);
				}else{
					setDescription("Insufficient data, please fill all fields.");
					setPageComplete(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				
			}
		});
	    
		
		noFriendsLabel = new Label(container, SWT.NULL);
		noFriendsLabel.setText("Displayed friends");
		
		noFriends = new Text(container, SWT.BORDER | SWT.SINGLE);
		noFriends.setSize(64, 32);
		
		
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    accessToken.setLayoutData(gd);
	    userId.setLayoutData(gd);
	    noFriends.setLayoutData(gd);

	}
	
	
	public Text getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(Text accessToken) {
		this.accessToken = accessToken;
	}

	public Text getUserId() {
		return userId;
	}

	public void setUserId(Text userId) {
		this.userId = userId;
	}

	public Label getAccessTokenLabel() {
		return accessTokenLabel;
	}

	public void setAccessTokenLabel(Label accessTokenLabel) {
		this.accessTokenLabel = accessTokenLabel;
	}

	public Label getUserIdLabel() {
		return userIdLabel;
	}

	public void setUserIdLabel(Label userIdLabel) {
		this.userIdLabel = userIdLabel;
	}

	public Text getNoFriends() {
		return noFriends;
	}

	public void setNoFriends(Text noFriends) {
		this.noFriends = noFriends;
	}


}
