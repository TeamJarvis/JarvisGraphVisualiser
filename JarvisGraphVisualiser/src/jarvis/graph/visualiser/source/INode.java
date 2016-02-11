package jarvis.graph.visualiser.source;

import java.util.List;


/**
 * Koristi se samo kao marker za prikazivanje razlicith izvora
 * @author Milos
 *
 */
public interface INode {
	
	public String getTitle();
	public IAttribModel getAttributes();
	public INodeModel getConnectedNodes();
	public List<INode> getAttCopies();
}
