package handler;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Config {

	JSONObject[] layouts;

	public Config(File configFile) throws Exception {
		try {
			layouts = Config.getLayouts(configFile);
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

	public boolean containsRepeats() {
		String[] layoutNames = this.getLayoutNames();
		Set<String> set = new HashSet<String>();

		for (String layoutName : layoutNames) {
			if (set.contains(layoutName)) {
				return true;
			} else {
				set.add(layoutName);
			}
		}
		return false;
	}

}
