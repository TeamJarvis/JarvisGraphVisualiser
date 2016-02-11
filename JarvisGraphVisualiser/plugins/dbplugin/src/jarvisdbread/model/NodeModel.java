package jarvisdbread.model;

import jarvis.graph.visualiser.source.INode;

import java.util.List;

public class NodeModel implements jarvis.graph.visualiser.source.INodeModel {

	protected List<INode> nodes;

	public NodeModel(List<INode> nodes) {
		super();
		this.nodes = nodes;
	}

	@Override
	public List<INode> getNodes() {
		
		return nodes;
	}

}
