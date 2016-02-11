package jarvis.graph.visualiser.core.pluginmenager.util;

public class JarvisPlugin {
	
	protected String name;
	protected int status;
	
	public JarvisPlugin(String name,int status) {
		this.status = status;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
}
