package jarvis.graph.visualiser.source;

import java.util.ArrayList;
import java.util.List;

public class DefaultNode extends AbstractNode implements INode {
	
	private String title;
	
	public DefaultNode(IAttribute att) {
		title = (att.getAttributeName()+": "+att.getAttributeValue());
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return title;
	}

	@Override
	public IAttribModel getAttributes() {
		// TODO Auto-generated method stub
		return new DefaultAttribModel();
	}

	@Override
	public INodeModel getConnectedNodes() {
		// TODO Auto-generated method stub
		return new DefaultNodeModel();
	}
	
	public static List<INode> getAttNodes(IAttribModel attModel) {
		if (attModel == null) {
			return new ArrayList<INode>();
		}
		List<INode> nodes = new ArrayList<INode>();
		for (IAttribClasses atts : attModel.getAttribs()) {
			for (IAttribute att : atts.getAttribs()) {
				nodes.add(new DefaultNode(att));
			}
		}
		return nodes;
	}

}
