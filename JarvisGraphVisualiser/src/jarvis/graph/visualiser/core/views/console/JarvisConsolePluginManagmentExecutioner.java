package jarvis.graph.visualiser.core.views.console;

import jarvis.graph.visualiser.core.pluginmenager.util.JarvisPluginMenager;

import java.util.regex.Matcher;

import org.osgi.framework.BundleException;

public class JarvisConsolePluginManagmentExecutioner extends JarvisConsoleExecutioner {

	private static final String STOP = "stop";
	private static final String START = "start";
	private static final String UNINSTALL = "uninstall";
	private static final String ACTIVATED = "activated";
	private static final String DEACTIVATED = "deactivated";
	private static final String UNINSTALLED = "uninstalled";

	public JarvisConsolePluginManagmentExecutioner(JarvisConsoleUtil consoleUtil, Matcher matcher) {
		super(consoleUtil, matcher);
	}

	@Override
	public void execute() {
		String toDo = matcher.group(2);
		if (toDo.equals("help")) {
			consoleUtil.helpPluginManager();
		} else {
			String pluginName = matcher.group(4);
			managePlugin(toDo, pluginName);
		}

	}

	private void managePlugin(String toDo, String pluginName) {
		try {
			if (toDo.equals(START)) {
				JarvisPluginMenager.getJarvisPluginMenger().start(pluginName);
				consoleUtil.printToConsole(info(ACTIVATED));
			} else if (toDo.equals(STOP)) {
				JarvisPluginMenager.getJarvisPluginMenger().stop(pluginName);
				consoleUtil.printToConsole(info(DEACTIVATED));
			} else if (toDo.equals(UNINSTALL)) {
				JarvisPluginMenager.getJarvisPluginMenger().uninstall(pluginName);
				consoleUtil.printToConsole(info(UNINSTALLED));
			} else {
				consoleUtil.printToConsole(badCommandInfo("plugin managment", "pluginmanager"));
			}
		} catch (BundleException e) {
			// e.printStackTrace();
			consoleUtil.printToConsole("[JARVIS]>> Error: " + e.getMessage());
		}
	}

	private String info(String message) {
		return "[JARVIS]>> Plugin " + message + ".";
	}

}
