package handler;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConfigHandler {
	public void handleConfig(String importConfigPath, String exportConfigPath) {
		File importConfig = new File(importConfigPath);
		File exportConfig = new File(exportConfigPath);

		checkIfFilesExist(importConfig, exportConfig);

	}

	// Check if files exist. Error if at least one does not
	private void checkIfFilesExist(File importConfig, File exportConfig) {
		String errorMsg = "";

		if (!importConfig.isFile()) {
			errorMsg += "\nError: Could not find config";
		}
		if (!exportConfig.isFile()) {
			errorMsg += "\nError: Could not find export config";
		}

		// Handle error message
		if (!errorMsg.isEmpty()) {
			System.out.println(errorMsg);
			System.exit(1);
		}

		return;
	}

	// Get configuration names
	protected static String[] getConfigNames(JSONArray jsonArr) {
		Iterator<Object> iterator = jsonArr.iterator();
		JSONObject[] configs = new JSONObject[jsonArr.length()];

		for (int i = 0; iterator.hasNext(); i++) {
			configs[i] = (JSONObject) iterator.next();
		}

		String[] configNames = new String[jsonArr.length()];

		for (int i = 0; i < configs.length; i++)
			configNames[i] = (String) configs[i].get("config_name");
		return configNames;

	}
}
