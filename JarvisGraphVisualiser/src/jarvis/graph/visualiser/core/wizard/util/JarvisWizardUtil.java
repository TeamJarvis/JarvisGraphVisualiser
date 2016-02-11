package jarvis.graph.visualiser.core.wizard.util;

import jarvis.graph.visualiser.core.views.JarvisGraphDescription;
import jarvis.graph.visualiser.core.views.JarvisGraphViewer;
import jarvis.graph.visualiser.core.views.tree.TreeView;
import jarvis.graph.visualiser.source.ISource;
import jarvis.graph.visualiser.source.ISourceFactory;
import jarvis.graph.visualiser.visualisator.IVisualisators;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.zest.core.viewers.GraphViewer;

public class JarvisWizardUtil {

	/**
	 * Dovuci sve plagine sa ekstenzione tacke za view
	 * */
	public static String[] getVisuelisator() {
		String PLUG_IN_ID = "jarvis.graph.visualiser.visualisator";

		IConfigurationElement[] extensions = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(PLUG_IN_ID);
		String[] retVal = new String[extensions.length];

		for (int i = 0; i < extensions.length; i++) {

			IConfigurationElement element = extensions[i];

			if (element.getAttribute("class") != null) {
				try {
					if (element.createExecutableExtension("class") instanceof IVisualisators) {
						IVisualisators top = (IVisualisators) element
								.createExecutableExtension("class");
						retVal[i] = top.getPluginName();
						
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}

		return retVal;
	}

	/**
	 * Dovuci sve plagine sa ekstenzione tacke za source
	 * */
	public static String[] getSources() {
		String PLUG_IN_ID = "jarvis.graph.visualiser.source";

		IConfigurationElement[] extensions = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(PLUG_IN_ID);
		String[] retVal = new String[extensions.length];

		for (int i = 0; i < extensions.length; i++) {

			IConfigurationElement element = extensions[i];

			if (element.getAttribute("class") != null) {
				try {
					if (element.createExecutableExtension("class") instanceof ISourceFactory) {
						ISourceFactory top = (ISourceFactory) element
								.createExecutableExtension("class");
						retVal[i] = top.getPluginName();
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}

		return retVal;
	}

	private static ISourceFactory getSourceFactory(String name) {
		String PLUG_IN_ID = "jarvis.graph.visualiser.source";

		IConfigurationElement[] extensions = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(PLUG_IN_ID);

		for (int i = 0; i < extensions.length; i++) {

			IConfigurationElement element = extensions[i];

			if (element.getAttribute("class") != null) {
				try {
					if (element.createExecutableExtension("class") instanceof ISourceFactory) {
						ISourceFactory top = (ISourceFactory) element
								.createExecutableExtension("class");
						if (top.getPluginName().equals(name)) {
							return top;
						}
					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}

		return null;

	}

	private static IVisualisators getView(String name) {
		String PLUG_IN_ID = "jarvis.graph.visualiser.visualisator";

		IConfigurationElement[] extensions = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(PLUG_IN_ID);

		for (int i = 0; i < extensions.length; i++) {

			IConfigurationElement element = extensions[i];

			if (element.getAttribute("class") != null) {
				try {
					if (element.createExecutableExtension("class") instanceof IVisualisators) {
						IVisualisators top = (IVisualisators) element
								.createExecutableExtension("class");
						if (top.getPluginName().equals(name)) {
							return top;
						}

					}
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public static void doMagic(String viewName, String sourceName, String path) {

//		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//		
//		ISourceFactory sourceFactory = getSourceFactory(sourceName);
//		IVisualisators visualisator = getView(viewName);
//		
//		//uzmo source znaci napuni kolekcije i vrati graf
//		ISource source = sourceFactory.getSource();
//		
//		source.getName();
//		//dovuci view za prikaz grafova
//		IViewPart view = activePage.findView(JarvisGraphViewer.ID);
//		
//		JarvisGraphViewer graphViewer = (JarvisGraphViewer)view;
//		
//		graphViewer.setGraphViewer(visualisator.showGraph(source, graphViewer.getGraphViewer()));
//		
//		//dovuci view za prikaz stabla
//		IViewPart view2 = activePage.findView(TreeView.ID);
//		
//		TreeView treeView = (TreeView)view2;
//		treeView.getViewer().setInput(source);
//		
//		//dobavi graph description
//		IViewPart view3 = activePage.findView(JarvisGraphDescription.ID);
//		JarvisGraphDescription graphDescription = (JarvisGraphDescription)view3;
//		graphDescription.setS(source);
//		graphDescription.initDataBindings();

		try {

			IWorkbenchPage activePage = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();

			ISourceFactory sourceFactory = getSourceFactory(sourceName);
			IVisualisators visualisator = getView(viewName);

			// uzmo source znaci napuni kolekcije i vrati graf
			ISource source = null;
			if(!path.equals("")){
				source = sourceFactory.getSource(path);
			}else{
				source = sourceFactory.getSource();
			}
			
			if(source != null)
				source.getName();//za slucaj da mu treba internet onda mora nekako da se aktivira plagin
			if(source == null)
				return;
			// dovuci view za prikaz grafova
			IViewPart view = activePage.findView(JarvisGraphViewer.ID);

			JarvisGraphViewer graphViewer = (JarvisGraphViewer) view;
			GraphViewer graph = visualisator.showGraph(source, graphViewer.getGraphViewer());
			graphViewer.setGraphViewer(graph);

			// dovuci view za prikaz stabla
			IViewPart view2 = activePage.findView(TreeView.ID);

			TreeView treeView = (TreeView) view2;
			treeView.getViewer().setInput(source);
	
			// dobavi graph description
			IViewPart view3 = activePage.findView(JarvisGraphDescription.ID);
			JarvisGraphDescription graphDescription = (JarvisGraphDescription) view3;
			graphDescription.setS(source);
			graphDescription.sync();
			
		} catch (Exception exc) {
			exc.printStackTrace();
			MessageDialog
					.openError(
							Display.getCurrent().getActiveShell(),
							"Error occurs",
							"Error occurs\n"
									+ "[Hint] Check internet connection, database connection,file location,..");
		}

	}
}
