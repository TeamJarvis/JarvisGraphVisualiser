package jarvis.graph.visualiser.core.filter;

import jarvis.graph.visualiser.core.views.JarvisGraphViewer;

import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.zest.core.viewers.GraphViewer;

public class JarvisFilterMenager {
	
	private static JarvisFilterMenager menager;
	
	
	private JarvisFilterMenager(){
		
	}
	
	public static JarvisFilterMenager getMenager(){
		if (menager == null) {
			menager = new JarvisFilterMenager();
		}
		
		return menager;
	}
	
	public String filter(String how2Filter,String filterWhat){
		GraphViewer graph = getGraph();
		
		return filterGraph(how2Filter, filterWhat, graph);
	}
	
	private GraphViewer getGraph(){
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart view = activePage.findView(JarvisGraphViewer.ID);

		JarvisGraphViewer graphViewer = (JarvisGraphViewer) view;
		
		return graphViewer.getGraphViewer();
	}
	
	public String resetFilter(){
		GraphViewer graph = getGraph();
		graph.resetFilters();
		graph.applyLayout();
		
		return "Filter restarted";
	}
	
	private String filterGraph(String how2Filter,String pattertn,GraphViewer graph){
		
		ViewerFilter[] filters = new ViewerFilter[1];
		
		//komanda je filter i onda konzola predje u stanje filter sto se oznacava sa filter>
		//nakon toga kucamo jedno od 2 filtera kao filter>node
		//on filtrira i vrati Graph filtered
		
		if (how2Filter.trim().equalsIgnoreCase("Node")) {
			NodeFilter filter = new NodeFilter(pattertn);
			filters[0] = filter;
		}else if(how2Filter.trim().equalsIgnoreCase("Search")){
			SearchNodeFilter filter = new SearchNodeFilter(pattertn);
			filters[0] = filter;
		}
		
		graph.resetFilters();
		graph.setFilters(filters);
		graph.applyLayout();
		
		return "Graph filtered";
		
	}
}
