package jarvisgraphvisualiser.fbplugin.wizard;

public class FBParams {
	
	private String userId;
	private String accessToken;
	private String noFriends;
	
	public FBParams(String userId, String accessToken, String noFriends) {
		super();
		this.userId = userId;
		this.accessToken = accessToken;
		this.noFriends = noFriends;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getNoFriends() {
		return noFriends;
	}
	public void setNoFriends(String noFriends) {
		this.noFriends = noFriends;
	}

}
