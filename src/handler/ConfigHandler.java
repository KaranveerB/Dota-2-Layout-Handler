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

		Config importConfig;
		Config exportConfig;

		// Parse files and print out config names
		try {
			importConfig = new Config(importConfigFile);
			printLayoutNames(importConfig, "Import");
		} catch (Exception e) {
			errorMsg = "\nError: Couldn't parse import config file";
		}

		try {
			exportConfig = new Config(exportConfigFile);
			printLayoutNames(exportConfig, "Export");
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

	private void printLayoutNames(Config config, String configType) throws Exception {
		String[] importLayoutNames = config.getLayoutNames();
		System.out.println("\n-- " + configType + " Config Layouts --");

		for (String layoutName : importLayoutNames) {
			System.out.println(layoutName);
		}
	}

}
