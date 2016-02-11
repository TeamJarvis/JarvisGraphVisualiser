package jarvisgraphvisualiser.fbplugin;

import java.util.List;

import jarvis.graph.visualiser.source.IAttribClasses;
import jarvis.graph.visualiser.source.IAttribute;

public class SubAttribs implements IAttribClasses{

	protected List<IAttribute> atribs;
	protected String name;
	
	
	public SubAttribs(List<IAttribute> atribs,String name) {
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
