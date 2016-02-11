package parser;

import java.util.List;

import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribute;

public class JarvisAttributeClasses implements IAttribClasses {
	
	private String name;
	private List<IAttribute> atribs;

	public JarvisAttributeClasses(String name, List<IAttribute> atribs) {
		super();
		this.name = name;
		this.atribs = atribs;
	}

	@Override
	public List<IAttribute> getAttribs() {
		return atribs;
	}

	@Override
	public String getName() {
		return name;
	}

}
