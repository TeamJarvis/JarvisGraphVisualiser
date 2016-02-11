package jarvis.graph.visualiser.source;

public interface ISourceFactory {
	
	 public static final String NAME_PROPERTY = "jarvis.graph.visualiser.source.name";
	   
	 public static final String ICON_PROPERTY = "jarvis.graph.visualiser.source.icon";
	 
	 public ISource getSource();
	 
	 public ISource getSource(String path);
	 
	 public String getPluginName();
	 
	 public String getPluginID();

}
