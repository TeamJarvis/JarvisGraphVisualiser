package jarvis.graph.visualiser.core.views.console;

import java.util.regex.Matcher;

public abstract class JarvisConsoleExecutioner {

	protected JarvisConsoleUtil consoleUtil;
	protected Matcher matcher;

	public JarvisConsoleExecutioner(JarvisConsoleUtil consoleUtil, Matcher matcher) {
		super();
		this.consoleUtil = consoleUtil;
		this.matcher = matcher;
	}

	public abstract void execute();

	protected String badCommandInfo(String doing, String command) {
		return "[JARVIS]>> Unrecognized command for " + doing + ". Check '" + command + " help' for available " + doing + " commands.";
	}

}
