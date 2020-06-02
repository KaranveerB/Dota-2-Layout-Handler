package handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;


public class FileHandler {	
	// Read the file's contents and returns it as a string
	public static String readFile(File file) throws IOException {
		String fileContent = "";

		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder sb = new StringBuilder();

		try {
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			fileContent = sb.toString();
		} catch (IOException e) {
			throw e;
		} finally {
			br.close();
		}
		return fileContent;
	}
	
}
