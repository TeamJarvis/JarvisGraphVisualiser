package jarvis.graph.visualiser.core.views.console;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleInputStream;
import org.eclipse.ui.console.IOConsoleOutputStream;

public class JarvisConsole extends IOConsole {

	private IOConsoleInputStream inputStream;
	private IOConsoleOutputStream outputStream;

	private JarvisConsoleUtil consoleUtil;
	// filter string1 string2
	private Pattern filterPattern = Pattern.compile("([a-z]+) ([a-z]+)[ ]*([\"']([a-zA-Z. ]*)[\"'])?");

	// open "source" via "visualisator" from "path"
	private Pattern openPattern = Pattern.compile("open[ ]+([\"']([a-zA-Z. " + Pattern.quote("[]") + "]*)[\"'])[ ]+via[ ]+([\"']([a-zA-Z. "
			+ Pattern.quote("[]") + "]*)[\"'])[ ]+from [\"']([^\"']+)[\"']");

	private Scanner scanner;

	public JarvisConsole() {
		super("Jarvis Console", null);

		inputStream = getInputStream();
		outputStream = newOutputStream();

		consoleUtil = new JarvisConsoleUtil(outputStream);

		scanner = new Scanner(inputStream);

		getDocument().addDocumentListener(new IDocumentListener() {

			@Override
			public void documentChanged(DocumentEvent arg0) {
				if (arg0.getText().equals("\r\n")) {
					if (scanner.hasNext()) {
						command(scanner.nextLine());
					}
				}

			}

			@Override
			public void documentAboutToBeChanged(DocumentEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	public void command(String command) {
		if (command.equals("help")) {
			consoleUtil.help();
		} else if (command.equals("open help")) {
			//nije mi se dalo mijenjati regularni izraz.
			consoleUtil.helpOpen();
		} else {
			JarvisConsoleExecutioner executioner = null;
			String operation = "";
			Matcher matcher = filterPattern.matcher(command);
			Matcher openMatcher = openPattern.matcher(command);

			if (openMatcher.find()) {
				if (openMatcher.groupCount() > 0) {
					executioner = new JarvisConsoleOpenExecutioner(consoleUtil, openMatcher);
					executioner.execute();
					return;
				}
			} else if (matcher.find()) {
				if (matcher.groupCount() > 0) {
					operation = matcher.group(1);
					if (operation.equals("filter")) {
						executioner = new JarvisConsoleFilteringExecutioner(consoleUtil, matcher);
						executioner.execute();
					} else if (operation.equals("pluginmanager")) {
						executioner = new JarvisConsolePluginManagmentExecutioner(consoleUtil, matcher);
						executioner.execute();
					} else if (operation.equals("setlayout")) {
						executioner = new JarvisConsoleLayoutExecutioner(consoleUtil, matcher);
						executioner.execute();
					}
				}
			} else {
				consoleUtil.printToConsole(commandFailure(command));
			}
		}
	}

	public String commandFailure(String command) {
		return "[JARVIS]>> Command '" + command + "' not recognized. Enter 'help' for list of available commands.";
	}

}
