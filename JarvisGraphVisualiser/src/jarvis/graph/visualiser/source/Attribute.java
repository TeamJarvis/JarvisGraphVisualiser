package jarvis.graph.visualiser.source;

public class Attribute implements IAttribute {

	String aName;
	String aValue;
	
	public Attribute(String aName, String aValue)
	{
		this.aName = aName;
		this.aValue = aValue;
	}
	
	@Override
	public String getAttributeName() {
		return this.aName;
	}

	@Override
	public String getAttributeValue() {
		return this.aValue;
	}

}
