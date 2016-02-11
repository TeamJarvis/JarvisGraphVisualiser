package jarvisgraphvisualiser.fbplugin;

import java.util.List;

import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.INodeModel;

public class FBNodeModel implements INodeModel {

	protected List<INode> nodes;
	
	public FBNodeModel(List<INode> nodes) {
		super();
		this.nodes = nodes;
	}
	
	@Override
	public List<INode> getNodes() {
		return nodes;
	}

}
