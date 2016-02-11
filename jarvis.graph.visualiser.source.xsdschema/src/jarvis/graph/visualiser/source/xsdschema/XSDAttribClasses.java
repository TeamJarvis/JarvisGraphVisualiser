package jarvis.graph.visualiser.source.xsdschema;

import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribute;

import java.util.ArrayList;
import java.util.List;

public class XSDAttribClasses implements IAttribClasses {
	
	private List<IAttribute> attribs = new ArrayList<IAttribute>();
	private String name;

	public XSDAttribClasses() {
		super();
	}

	public XSDAttribClasses(List<IAttribute> attribs, String name) {
		super();
		this.attribs = attribs;
		this.name = name;
	}

	@Override
	public List<IAttribute> getAttribs() {
		return attribs;
	}
	
	public void setAttribs(List<IAttribute> attribs) {
		this.attribs = attribs;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	

}
