package jarvis.graph.visualiser.source.xsdschema;

import jarvis.graph.visualiser.source.IAttribute;

public class XSDAttribute implements IAttribute {
	
	private String attributeName;
	private String attributeValue;
	
	public XSDAttribute() {
		super();
	}

	public XSDAttribute(String attributeName, String attributeValue) {
		super();
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
	}

	@Override
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	@Override
	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

}
