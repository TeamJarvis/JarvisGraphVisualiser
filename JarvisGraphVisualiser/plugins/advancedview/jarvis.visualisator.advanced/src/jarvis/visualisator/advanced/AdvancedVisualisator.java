package jarvis.visualisator.advanced;

import jarvis.graph.visualiser.source.ISource;
import jarvis.graph.visualiser.visualisator.IVisualisators;

import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;

public class AdvancedVisualisator implements IVisualisators {
	
	private String name = "Advanced node visualisator [TeamJARVIS]";
	private String id = "jarvis.visualisator.advanced";
	
	public AdvancedVisualisator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public GraphViewer showGraph(ISource source, GraphViewer view) {
		view.getGraphControl().getConnections().clear();
		view.getGraphControl().getNodes().clear();
		view.getGraphControl().clear();
		
		view.setContentProvider(new JarvisAdvancedContentProvider());
		view.setLabelProvider(new JarvisAdvancedStructureLabelProvider());
		view.setLayoutAlgorithm(new RadialLayoutAlgorithm());
		
		if (view.getInput() == null) {
			view.setConnectionStyle( ZestStyles.CONNECTIONS_DIRECTED);//strelice
		}
		view.setInput(source);
		
		return view;
	}

	@Override
	public String getPluginName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String getPluginID() {
		// TODO Auto-generated method stub
		return id;
	}

}
