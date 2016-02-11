package jarvis.graph.visualiser.core.views.console;

import jarvis.graph.visualiser.core.filter.JarvisFilterMenager;

import java.util.regex.Matcher;

public class JarvisConsoleFilteringExecutioner extends JarvisConsoleExecutioner {

	public JarvisConsoleFilteringExecutioner(JarvisConsoleUtil consoleUtil, Matcher matcher) {
		super(consoleUtil, matcher);
	}

	@Override
	public void execute() {
		String how2Filter = matcher.group(2);

		if (how2Filter.equals("help")) {
			consoleUtil.helpFilter();
		} else if (how2Filter.equals("reset")) {
			JarvisFilterMenager.getMenager().resetFilter();
		} else if (matcher.group(4) != null) {
			String filterWhat = matcher.group(4).trim();
			JarvisFilterMenager.getMenager().filter(how2Filter, filterWhat);
		} else {
			consoleUtil.printToConsole(badCommandInfo("filtering", "filter"));
		}
	}

}
