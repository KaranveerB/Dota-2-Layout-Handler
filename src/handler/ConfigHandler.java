package handler;

import java.io.File;
import java.io.IOException;

public class ConfigHandler {

	public void handleConfig(String importConfigPath, String exportConfigPath) {
		File importConfigFile = new File(importConfigPath);
		File exportConfigFile = new File(exportConfigPath);

		checkIfFilesExist(importConfigFile, exportConfigFile);

		try {
			FileHandler.createBackup(exportConfigFile);
			System.out.println("\nBackup created successfully");
		} catch (IOException e) {
			System.out.println("\nWarning: Failed to create backup. Proceed with caution");
		}

		String errorMsg = ""; // Error message if configs can't be parsed
		// Shown after printing layout names for readability

		ConfigFile importConfig;
		ConfigFile exportConfig;

		// Index of *LayoutNames corresponds to index of Layouts in ConfigFile.java
		String[] importLayoutNames;
		String[] exportLayoutNames;

		// Parse files and print out config names
		try {
			importConfig = new ConfigFile(importConfigFile);
			importLayoutNames = importConfig.getLayoutNames();
			System.out.println("\n-- Import Configs --");

			for (String layoutName : importLayoutNames) {
				System.out.println(layoutName);
			}

		} catch (Exception e) {
			errorMsg = "\nError: Couldn't parse import config file";
		}

		try {
			exportConfig = new ConfigFile(exportConfigFile);
			exportLayoutNames = exportConfig.getLayoutNames();
			System.out.println("\n-- Export Configs --");

			for (String layoutName : exportLayoutNames) {
				System.out.println(layoutName);
			}

		} catch (Exception e) {
			errorMsg += "\nError: Couldn't parse export config file";
		}

		if (!errorMsg.isEmpty()) {
			System.out.println(errorMsg);
			System.exit(1);
		}

	}

	private void checkIfFilesExist(File importConfigFile, File exportConfigFile) {
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

		return;
	}

}
