package jarvisdbread.dbutil;

import jarvis.graph.visualiser.source.IAttribute;

public class Privileges implements IAttribute{
	
	protected String name;
	protected String granted;
	protected String grantor;
	
	public Privileges(String name,String granted,String grantor) {
		this.name = name;
		this.granted = granted;
		this.grantor = grantor;
	}
	
	public String getGranted() {
		return granted;
	}

	@Override
	public String getAttributeName() {
		return name;
	}

	@Override
	public String getAttributeValue() {
		return granted+" / "+grantor;
	}
}
