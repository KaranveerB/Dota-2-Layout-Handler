package handler;

import java.io.File;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConfigFile {

	JSONObject[] layouts;

	public ConfigFile(File configFile) throws Exception {
		try {
			layouts = ConfigFile.getLayouts(configFile);
		} catch (Exception e) {
			// Exception can be IOException or JSONException
			// Grouped as Exception as they are handled the same (file can't be parsed)
			throw e;
		}
	}

	private static JSONObject[] getLayouts(File jsonFile) throws Exception {
		String jsonFileContent = FileHandler.readFile(jsonFile);
		JSONObject jsonFileObj = new JSONObject(jsonFileContent);
		JSONArray configs = (JSONArray) jsonFileObj.get("configs");

		Iterator<Object> iterator = configs.iterator();
		JSONObject[] layouts = new JSONObject[configs.length()];

		for (int i = 0; iterator.hasNext(); i++) {
			layouts[i] = (JSONObject) iterator.next();
		}

		return layouts;
	}

	public String[] getLayoutNames() throws JSONException {
		String[] layoutNames = new String[layouts.length];

		for (int i = 0; i < layoutNames.length; i++) {
			layoutNames[i] = layouts[i].getString("config_name");
		}

		return layoutNames;
	}

}
