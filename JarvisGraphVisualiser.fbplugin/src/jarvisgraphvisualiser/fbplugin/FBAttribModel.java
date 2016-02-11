package jarvisgraphvisualiser.fbplugin;

import java.util.List;

import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribModel;

public class FBAttribModel implements IAttribModel {

	protected List<IAttribClasses> attribs;
	
	public FBAttribModel(List<IAttribClasses> attribs) {
		super();
		this.attribs = attribs;
	}

	@Override
	public List<IAttribClasses> getAttribs() {
		return attribs;
	}

}
