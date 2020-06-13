package handler;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ConfigHandler {

	private static void checkIfFilesExist(File importConfigFile, File exportConfigFile) {
		String errorMsg = "";

		if (!importConfigFile.isFile()) {
			errorMsg += "\nError: Could not find import config";
		}

		if (!exportConfigFile.isFile()) {
			errorMsg += "\nError: Could not find export config";
		}

		// Handle error message
		if (!errorMsg.isEmpty()) {
			System.out.println(errorMsg);
			System.exit(1);
		}

	}

	private static void printLayoutNames(Config config, String configType) throws Exception {
		String[] importLayoutNames = config.getLayoutNames();
		System.out.println("\n-- " + configType + " Config Layouts --");

		for (String layoutName : importLayoutNames) {
			System.out.println(layoutName);
		}
	}

	private static String promptForLayoutToExport(Scanner sc) {
		System.out.print("\nEnter config to transfer: ");
		return sc.nextLine().trim();
	}

	public void handleConfigs(String importConfigPath, String exportConfigPath) {
		File importConfigFile = new File(importConfigPath);
		File exportConfigFile = new File(exportConfigPath);

		ConfigHandler.checkIfFilesExist(importConfigFile, exportConfigFile);

		try {
			FileHandler.createBackup(exportConfigFile);
			System.out.println("\nBackup created successfully.");
		} catch (IOException e) {
			System.out.println("\nWarning: Failed to create backup. Proceed with caution.");
		}

		String errorMsg = ""; // Error message if configs can't be parsed
		// Shown after printing layout names for readability

		Config importConfig = null;
		Config exportConfig = null;

		// Parse files and print out layout names
		try {
			importConfig = new Config(importConfigFile);
			ConfigHandler.printLayoutNames(importConfig, "Import");
		} catch (Exception e) {
			errorMsg = "\nError: Couldn't parse import config file.";
		}

		try {
			exportConfig = new Config(exportConfigFile);
			ConfigHandler.printLayoutNames(exportConfig, "Export");
		} catch (Exception e) {
			errorMsg += "\nError: Couldn't parse export config file.";
		}

		if (!errorMsg.isEmpty()) {
			System.out.println(errorMsg);
			System.exit(1);
		}

		if (importConfig.containsRepeats() || exportConfig.containsRepeats()) {
			System.out.println("\nError: Existing duplicate layout names detected. This is not supported by Dota 2.\n"
					+ "Please create a new configuration or manually repair the old one.");
		}

		while (true) {
			Scanner sc = new Scanner(System.in);

			String layoutToExport = "";

			while (layoutToExport.equals("")) {
				String selectedLayout = promptForLayoutToExport(sc);

				if (!importConfig.containsLayout(selectedLayout)) {
					System.out.println(
							"\nError: Layout could not be found." +
									"Ensure the layout name entered is one of the following.");
					try {
						ConfigHandler.printLayoutNames(importConfig, "Import");
					} catch (Exception e) {
						System.out.println("\nError: Couldn't parse import config file.");
					}
					continue;
				}

				if (!exportConfig.containsLayout(selectedLayout)) {
					layoutToExport = selectedLayout;
				} else {
					System.out.println("\nError: Exporting layout would result in a result in repeat names.\n" +
							"This is not supported by Dota 2. Please manually edit the layout names to resolve this.");
				}
			}

			// TODO: Export Layout

			System.out.print("\nExport another layout? y/N");
			if (!sc.nextLine().toLowerCase().equals("y")) {
				System.out.println("\nExiting");
				System.exit(0);
			}
		}

	}

}
