package jarvis.graph.visualiser.source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ISource {
	
	public String getName();
	public Object getDescription();
	@Deprecated
	public HashMap<INode, ArrayList<INode>> getDataGraph();
	public List<INode> getNodeDataGraph();
	public String getPluginID();
	public String getPluginName();
}
