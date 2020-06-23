package handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Config {

	private final JSONObject config;
	private final JSONObject[] layouts;

	public Config(File configFile) throws Exception {
		config = new JSONObject(FileHandler.readFile(configFile));
		layouts = Config.getLayouts(config);
	}

	private static JSONObject[] getLayouts(JSONObject jsonObj) throws Exception {
		JSONArray configs = (JSONArray) jsonObj.get("configs");

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

	public boolean containsLayout(String newLayoutName) {
		for (String layoutName : this.getLayoutNames()) {
			if (newLayoutName.equals(layoutName)) {
				return true;
			}
		}

		return false;
	}

	public boolean containsRepeats() {
		String[] array = this.getLayoutNames();
		Set<String> set = new HashSet<String>();

		for (String element : array) {
			if (set.contains(element)) {
				return true;
			} else {
				set.add(element);
			}
		}
		return false;
	}

	public JSONObject getJSONObject() {
		return config;
	}

	public JSONObject getLayout(String layoutName) {
		for (JSONObject layout : layouts) {
			if (layoutName.equals(layout.getString("config_name"))) {
				return layout;
			}
		}
		return null;
	}

}
