package jarvis.graph.visualiser.core.views.console;

import jarvis.graph.visualiser.core.wizard.util.JarvisWizardUtil;

import java.io.File;
import java.util.regex.Matcher;

public class JarvisConsoleOpenExecutioner extends JarvisConsoleExecutioner {

	public JarvisConsoleOpenExecutioner(JarvisConsoleUtil consoleUtil, Matcher matcher) {
		super(consoleUtil, matcher);
	}

	@Override
	public void execute() {
		String source = matcher.group(2);
		String visualizator = matcher.group(4);
		String path = matcher.group(5);
		
		JarvisWizardUtil.doMagic(visualizator, source, path.replace("/", File.pathSeparator));
	}

}
