package jarvisdbread.model;

import jarvis.graph.visualiser.source.IAttribClasses;

import java.util.List;

public class AttribModel implements jarvis.graph.visualiser.source.IAttribModel {

	protected List<IAttribClasses> attribs;
	
	public AttribModel(List<IAttribClasses> attribs) {
		super();
		this.attribs = attribs;
	}

	@Override
	public List<IAttribClasses> getAttribs() {
		return attribs;
	}


}
