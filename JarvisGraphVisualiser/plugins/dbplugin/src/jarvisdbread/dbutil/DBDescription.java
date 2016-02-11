package jarvisdbread.dbutil;



public class DBDescription {
	
	protected String productName;
	protected String productVersion;
	
	protected String driverName;
	protected String driverVersion;
	
	protected int isolation;
	
	public DBDescription(String productName,String productVersion,
			             String driverName,String driverVersion,
			             int isolation) {
		
		this.productName = productName;
		this.productVersion = productVersion;
		this.driverName = driverName;
		this.driverVersion = driverVersion;
		this.isolation = isolation;
		
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverVersion() {
		return driverVersion;
	}

	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}

	public int getIsolation() {
		return isolation;
	}

	public void setIsolation(int isolation) {
		this.isolation = isolation;
	}
}
