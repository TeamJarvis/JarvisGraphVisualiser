package parser;

import jarvis.graph.visualiser.source.IAttribute;

public class JarvisField implements IAttribute{

	private String name;
	private String modifier;
	private String type;
	private String value = "(not initialized)";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "\t\tJarvisField, name:" + name + ", modifier:" + modifier +" type: " + type;
	}

	@Override
	public String getAttributeName() {
		return name;
	}

	@Override
	public String getAttributeValue() {
		return value;
	}

}
