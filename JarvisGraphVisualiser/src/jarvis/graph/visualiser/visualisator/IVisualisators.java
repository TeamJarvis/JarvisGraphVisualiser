package jarvis.graph.visualiser.visualisator;

import jarvis.graph.visualiser.source.ISource;

import org.eclipse.zest.core.viewers.GraphViewer;

public interface IVisualisators {
	
	public static final String NAME_PROPERTY = "jarvis.graph.visualiser.visualisators.name";
	   
	public static final String ICON_PROPERTY = "jarvis.graph.visualiser.visualisators.icon";
	
	public GraphViewer showGraph(ISource source,GraphViewer view);
	
	public String getPluginName();
	
	public String getPluginID();
}
