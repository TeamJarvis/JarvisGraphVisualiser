package jarvis.graph.visualiser.source.xsdschema;

import jarvis.graph.visualiser.source.AbstractNode;
import jarvis.graph.visualiser.source.IAttribModel;
import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.INodeModel;

public class XSDNode extends AbstractNode implements INode {
	
	private String title;
	private IAttribModel attributes;
	private INodeModel connectedNodes;
	
	public XSDNode() {
		super();
	}

	public XSDNode(String title, IAttribModel attributes, INodeModel connectedNodes) {
		super();
		this.title = title;
		this.attributes = attributes;
		this.connectedNodes = connectedNodes;
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public IAttribModel getAttributes() {
		return attributes;
	}
	
	public void setAttributes(IAttribModel attributes) {
		this.attributes = attributes;
	}

	@Override
	public INodeModel getConnectedNodes() {
		return connectedNodes;
	}

	public void setConnectedNodes(INodeModel connectedNodes) {
		this.connectedNodes = connectedNodes;
	}

}
