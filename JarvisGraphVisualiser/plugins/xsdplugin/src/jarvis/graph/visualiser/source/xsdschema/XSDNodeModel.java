package jarvis.graph.visualiser.source.xsdschema;

import java.util.ArrayList;
import java.util.List;

import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.INodeModel;

public class XSDNodeModel implements INodeModel {
	
	private List<INode> nodes = new ArrayList<INode>();

	public XSDNodeModel() {
		super();
	}

	public XSDNodeModel(List<INode> nodes) {
		super();
		this.nodes = nodes;
	}

	@Override
	public List<INode> getNodes() {
		return nodes;
	}

	public void setNodes(List<INode> nodes) {
		this.nodes = nodes;
	}

}
