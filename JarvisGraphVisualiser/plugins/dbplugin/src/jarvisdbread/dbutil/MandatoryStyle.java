package jarvisdbread.dbutil;

import jarvis.graph.visualiser.source.IAttribute;

public class MandatoryStyle implements IAttribute {
	
	private Mandatory mandatory;
	private String name;
	
	public MandatoryStyle(Mandatory mandatory) {
		this.mandatory = mandatory;
		this.name = "Mandatory";
	}
	
	@Override
	public String getAttributeName() {
		return name;
	}

	@Override
	public String getAttributeValue() {
		
		if (mandatory == Mandatory.YES) {
			return "<M>";
		}
		
		return "";
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMandatory(Mandatory mandatory) {
		this.mandatory = mandatory;
	}

}
