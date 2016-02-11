package jarvis.graph.visualiser.source;

import java.util.List;

public abstract class AbstractNode implements INode {
	
	private List<INode> attCopies;
	
	@Override
	public List<INode> getAttCopies() {
		if (attCopies != null) {
			return attCopies;
		}
		else {
			attCopies = DefaultNode.getAttNodes(getAttributes());
			return attCopies;
		}
	}

}
