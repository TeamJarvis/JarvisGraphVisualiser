package jarvis.graph.visualiser.source.xsdschema;

import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.ISource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XSDSource implements ISource {
	
	private String name;
	private Object description;
	private List<INode> nodeDataGraph;
	
	public XSDSource() {
		super();
	}

	public XSDSource(String name, Object description, List<INode> nodeDataGraph) {
		super();
		this.name = name;
		this.description = description;
		this.nodeDataGraph = nodeDataGraph;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getDescription() {
		return description;
	}

	public void setDescription(Object description) {
		this.description = description;
	}
	
	@Deprecated
	public HashMap<INode, ArrayList<INode>> getDataGraph() {
		return null;
	}
	
	@Override
	public List<INode> getNodeDataGraph() {
		return nodeDataGraph;
	}

	public void setDataGraph(List<INode> nodeDataGraph) {
		this.nodeDataGraph = nodeDataGraph;
	}

	@Override
	public String getPluginID() {
		return "jarvis.graph.visualiser.source.xsdschema";
	}

	@Override
	public String getPluginName() {
		return "Xsdschema";
	}

}
