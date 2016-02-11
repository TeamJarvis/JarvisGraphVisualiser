package jarvis.visualisator.basic;

import jarvis.graph.visualiser.source.ISource;
import jarvis.graph.visualiser.visualisator.IVisualisators;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;

public class BasicVisualisator implements IVisualisators {
	
	private String name = "Basic node visualisator [TeamJARVIS]";
	private String id = "jarvis.visualisator.basic";
	
	@Override
	public String getPluginName() {
		return name;
	}

	@Override
	public GraphViewer showGraph(ISource source,GraphViewer view) {
		
		view.getGraphControl().getConnections().clear();
		view.getGraphControl().getNodes().clear();
		view.getGraphControl().clear();
		
		view.setContentProvider(new JarvisSimpleContentProvider());
		view.setLabelProvider(new JarvisSimpleStructureLabelProvider());
		view.setLayoutAlgorithm(new RadialLayoutAlgorithm());
		
		if (view.getInput() == null) {
			view.setConnectionStyle( ZestStyles.CONNECTIONS_DIRECTED);//strelice
		}
		view.setInput(source);		
		return view;
	}

	@Override
	public String getPluginID() {
		
		return id;
	}

}
