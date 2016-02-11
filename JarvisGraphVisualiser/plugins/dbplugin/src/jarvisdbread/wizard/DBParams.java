package jarvisdbread.wizard;

public class DBParams {
	
	private String url;
	private String username;
	private String password;
	private String jdbcName;
	
	public DBParams(String url,String username,String password,String jdbcName) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.jdbcName = jdbcName;
	}

	public String getJdbcName() {
		return jdbcName;
	}

	public void setJdbcName(String jdbcName) {
		this.jdbcName = jdbcName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
