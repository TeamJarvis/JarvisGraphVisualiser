package jarvis.graph.visualiser.core.views.console;

import jarvis.graph.visualiser.core.views.JarvisGraphViewer;

import java.util.regex.Matcher;

import org.eclipse.ui.PlatformUI;

public class JarvisConsoleLayoutExecutioner extends JarvisConsoleExecutioner {
	
	private static final String HELP = "help";
	private static final String RADIAL = "radial";
	private static final String TREE = "tree";
	private static final String GRID = "grid";
	private static final String SPRING = "spring";
	private static final String DOING = "layouting";
	private static final String COMMAND = "setlayout";

	public JarvisConsoleLayoutExecutioner(JarvisConsoleUtil consoleUtil, Matcher matcher) {
		super(consoleUtil, matcher);
	}

	@Override
	public void execute() {
		String layout = matcher.group(2);
		JarvisGraphViewer.Layout newLayout = null;

		if (layout.equals(HELP)) {
			consoleUtil.helpSetLayout();
			return;
		} else if (layout.equals(RADIAL)) {
			newLayout = JarvisGraphViewer.Layout.RADIAL;
		} else if (layout.equals(TREE)) {
			newLayout = JarvisGraphViewer.Layout.TREE;
		} else if (layout.equals(SPRING)) {
			newLayout = JarvisGraphViewer.Layout.SPRING;
		} else if (layout.equals(GRID)) {
			newLayout = JarvisGraphViewer.Layout.GRID;
		}else{
			consoleUtil.printToConsole(badCommandInfo(DOING, COMMAND));
			return;
		}
		final JarvisGraphViewer siteView = (JarvisGraphViewer) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.findView(JarvisGraphViewer.ID);
		siteView.setLayout(newLayout);
	}

}
