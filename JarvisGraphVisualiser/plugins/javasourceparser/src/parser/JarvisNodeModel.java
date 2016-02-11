package parser;

import java.util.List;

import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.INodeModel;

public class JarvisNodeModel implements INodeModel {
	
	private List<INode> nodes;
	
	public JarvisNodeModel(List<INode> nodes){
		this.nodes = nodes;
	}

	@Override
	public List<INode> getNodes() {
		return nodes;
	}

}
