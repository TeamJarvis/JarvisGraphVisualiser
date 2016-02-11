package jarvisgraphvisualiser.fbplugin;

import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.ISource;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.FacebookType;
import com.restfb.types.User;

public class FBGraphUsers implements ISource {
	protected JarvisFBManager jarvisFBManager;
	
	public FBGraphUsers(String ACCESS_TOKEN, String USER_ID, String noFriends)
	{
		jarvisFBManager = new JarvisFBManager(ACCESS_TOKEN, USER_ID, noFriends);
	}
	
	@Override
	public String getName() {
		return jarvisFBManager.getPrimaryNode().getName();
	}

	@Override
	public Object getDescription() {
		return jarvisFBManager.getPrimaryNode().getId();
	}

	@Override
	public HashMap<INode, ArrayList<INode>> getDataGraph() {
		return jarvisFBManager.getConnectedGraph();
	}

	@Override
	public List<INode> getNodeDataGraph() {
		
		return jarvisFBManager.getConnectedNodeGraph();
	}

	@Override
	public String getPluginID() {
		return "JarvisGraphVisualiser.fbplugin";
	}

	@Override
	public String getPluginName() {
		return "Fbplugin";
	}

}
