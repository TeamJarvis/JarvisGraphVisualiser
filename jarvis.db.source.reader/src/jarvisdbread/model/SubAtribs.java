package jarvisdbread.model;

import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribute;

import java.util.List;

public class SubAtribs implements IAttribClasses {
	
	protected List<IAttribute> atribs;
	protected String name;
	
	public SubAtribs(List<IAttribute> atribs,String name) {
		this.atribs = atribs;
		this.name = name;
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
