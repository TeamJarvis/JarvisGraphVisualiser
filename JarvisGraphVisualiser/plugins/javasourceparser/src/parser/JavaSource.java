package parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jarvis.graph.visualiser.source.INode;
import jarvis.graph.visualiser.source.ISource;

public class JavaSource implements ISource {
	
	private String path;
	
	public JavaSource(String path){
		this.path = path;
	}

	@Override
	public String getName() {
		return "Java Source";
	}

	@Override
	public Object getDescription() {
		return "Java Source Description";
	}

	@Override
	public HashMap<INode, ArrayList<INode>> getDataGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<INode> getNodeDataGraph() {
		JarvisParser parser = new JarvisParser(path);
		return parser.getNodeConnectedGraph();
	}

	@Override
	public String getPluginName() {
		return "Java Source Parser";
	}

	@Override
	public String getPluginID() {
		return "JavaSourceParser";
	}

}
