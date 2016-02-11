package jarvisdbread.dbutil;


import jarvis.graph.visualiser.source.IAttribute;

import java.util.ArrayList;

public class ColumnInformations implements IAttribute{
	
	protected String name;
	protected String type;
	protected MandatoryStyle mandatory;
	protected ArrayList<Privileges> privileges;
	
	public ColumnInformations(String name,String type,MandatoryStyle mandatory,ArrayList<Privileges> privileges) {
		this.name = name;
		this.type = type;
		this.mandatory = mandatory;
		this.privileges = privileges;
	}
	
	public MandatoryStyle getMandatory() {
		return mandatory;
	}
	
	public ArrayList<Privileges> getPrivileges() {
		return privileges;
	}

	@Override
	public String getAttributeName() {
		return name;
	}

	@Override
	public String getAttributeValue() {
		return type;
	}
	
}
