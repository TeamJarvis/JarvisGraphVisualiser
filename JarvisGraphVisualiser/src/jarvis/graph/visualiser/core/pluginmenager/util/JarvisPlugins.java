package jarvis.graph.visualiser.core.pluginmenager.util;

import java.util.ArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleEvent;

public class JarvisPlugins {
	
	protected ArrayList<JarvisPlugin> plugins;
	
	public JarvisPlugins() {
		plugins = new ArrayList<JarvisPlugin>();
	}
	
	//add plugin -> START plugin
	public void add(JarvisPlugin newPlugin){
		plugins.add(newPlugin);
	}
	
	//modify plugin -> STOP plugin or else
	public void modifyPLugin(JarvisPlugin oldValue,JarvisPlugin newValue){
		
		for (JarvisPlugin plugin : plugins) {
			if (plugin.getName().equalsIgnoreCase(oldValue.getName()) &&
					plugin.getStatus() == oldValue.getStatus()) {
				
				plugin.setName(newValue.getName());
				plugin.setStatus(newValue.getStatus());
			}
		}
	}
	
	//remove plugin -> UNINSTAL plugin
	public boolean removePlugin(JarvisPlugin oldPlugin) {
		return plugins.remove(oldPlugin);
	}

	public ArrayList<JarvisPlugin> getPlugins() {
		return plugins;
	}

	public void setPlugins(ArrayList<JarvisPlugin> plugins) {
		this.plugins = plugins;
	}
	
	public void clear(){
		plugins.clear();
	}
	
	/*PLUGIN operations*/
	
	//START the plugin for specific URL
	public boolean startPlugin(String pluginToStart){
		int start = Bundle.STARTING;
		
		for (JarvisPlugin plugin : plugins) {
			if (plugin.getName().equalsIgnoreCase(pluginToStart)) {
				plugin.setStatus(start);
				return true;
			}
		}
		
		return false;
	}
	
	//STOP the plugin for specific URL
	public boolean stopPlugin(String pluginToStop){
		int stop = Bundle.STOPPING;
		
		for (JarvisPlugin plugin : plugins) {
			if (plugin.getName().equalsIgnoreCase(pluginToStop)) {
				plugin.setStatus(stop);
				return true;
			}
		}
		
		return false;
	}
	
	//UNINSTALL the plugin for specific URL
	public boolean uninstalPlugin(String pluginToUninstall){
		int uninstall = BundleEvent.UNINSTALLED;
		
		for (JarvisPlugin plugin : plugins) {
			if (plugin.getName().equalsIgnoreCase(pluginToUninstall)) {
				plugin.setStatus(uninstall);
				return true;
			}
		}
		
		return false;
	}
	
}
