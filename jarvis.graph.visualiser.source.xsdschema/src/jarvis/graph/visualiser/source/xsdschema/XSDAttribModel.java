package jarvis.graph.visualiser.source.xsdschema;

import java.util.ArrayList;
import java.util.List;

import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribModel;

public class XSDAttribModel implements IAttribModel {
	
	private List<IAttribClasses> attribClasses = new ArrayList<IAttribClasses>();

	public XSDAttribModel() {
		super();
	}

	public XSDAttribModel(List<IAttribClasses> attribClasses) {
		super();
		this.attribClasses = attribClasses;
	}

	@Override
	public List<IAttribClasses> getAttribs() {
		return attribClasses;
	}

	public void setAttribClasses(List<IAttribClasses> attribClasses) {
		this.attribClasses = attribClasses;
	}
	
	

}
