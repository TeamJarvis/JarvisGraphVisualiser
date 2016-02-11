package jarvis.graph.visualiser.core.views.console;

import java.io.IOException;

import org.eclipse.ui.console.IOConsoleOutputStream;

public class JarvisConsoleUtil {

	private IOConsoleOutputStream outputStream;

	public JarvisConsoleUtil(IOConsoleOutputStream outputStream) {
		this.outputStream = outputStream;
	}
	
	public void helpOpen(){
		printToConsole("\topen - opens Jarvis Wizard Dialog");
		printToConsole("\t\tParameters: source plugin name, visualisator plugin name and path.");
		printToConsole("\t\tExample1: open \"(source plugin name)\"  via \"(visualisator plugin name)\" from \"path\"");
		printToConsole("\t\tExample2: open \'(source plugin name)\'  via \'(visualisator plugin name)\' from \'path\'");
	}

	public void helpSetLayout() {
		printToConsole("\tsetlayout - allows changes on current layout.");
		printToConsole("\t\tLayouts: radial, tree, spring and grid");
		printToConsole("\t\tExample: setlayout (insert layout here)");
	}

	public void helpPluginManager() {
		printToConsole("\tpluginmanager - allows starting, stoping and uninstallation of plugins.");
		printToConsole("\t\tOperations: start, stop and uninstall.");
		printToConsole("\t\tExample1: pluginmanager (insert operation here) \"PluginID\"");
		printToConsole("\t\tExample2: pluginmanager (insert operation here) \'PluginID\'");
	}

	public void helpFilter() {
		printToConsole("\tfilter - filters graph by given type of filtering and by node name.");
		printToConsole("\t\tCommands: node, search and reset");
		printToConsole("\t\tExample1: filter (node|search) \"NodeName\"");
		printToConsole("\t\tExample2: filter (node|search) \'NodeName\' ");
		printToConsole("\t\tExample3: filter reset");
	}

	public void help() {
		String operations = "[JARVIS]>> List of operations...";
		printToConsole(operations);
		printToConsole("\topen - opens Jarvis Wizard Dialog");
		printToConsole("\tfilter - filters graph by given type of filtering and by node name.");
		printToConsole("\tsetlayout - allows changes on current layout.");
		printToConsole("\tpluginmanager - allows starting, stoping and uninstallation of plugins.");
		printToConsole("For more details on command type: (commandName) help");
	}

	public void printToConsole(String message) {
		outputStream.setActivateOnWrite(true);
		try {
			outputStream.write(message);
			outputStream.write("\n");
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
