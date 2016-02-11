package jarvis.visualisator.advanced_mixed;

import jarvis.graph.visualiser.source.ISource;
import jarvis.graph.visualiser.visualisator.IVisualisators;

import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;

public class AdvancedMixedVisualisator implements IVisualisators {

	private String name = "Advanced mixed node visualisator [TeamJARVIS]";
	private String id = "jarvis.visualisator.advanced_mixed";
	
	@Override
	public GraphViewer showGraph(ISource source, GraphViewer view) {
		view.getGraphControl().getConnections().clear();
		view.getGraphControl().getNodes().clear();
		view.getGraphControl().clear();
		
		view.setContentProvider(new JarvisAdvancedMixedContentProvider());
		view.setLabelProvider(new JarvisAdvancedMixedStructuredLabelProvider());
		view.setLayoutAlgorithm(new RadialLayoutAlgorithm());
		
		if (view.getInput() == null) {
			view.setConnectionStyle( ZestStyles.CONNECTIONS_DIRECTED);//strelice
		}
		view.setInput(source);
		
		return view;
	}

	@Override
	public String getPluginName() {
		return name;
	}

	@Override
	public String getPluginID() {
		return id;
	}

}
