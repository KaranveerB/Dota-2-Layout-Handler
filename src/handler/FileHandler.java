package handler;

import java.io.*;

public class FileHandler {

	// Read the file's contents and returns it as a string
	public static String readFile(File file) throws IOException {
		String fileContent = "";

		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder sb = new StringBuilder();

		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		fileContent = sb.toString();

		return fileContent;
	}

	public static void createBackup(File file) throws IOException {
		String fileName = file.getName();
		String backupFolderPath = file.getParent() + "/Hero Config Backups/";
		String backupFilePath = "";

		// Create folder for backups if it doesn't exist
		File backupFolder = new File(backupFolderPath);
		if (!backupFolder.exists()) {
			backupFolder.mkdir();
		}


		// Create backup suffixed with .bak
		if (!new File(backupFolderPath + fileName + ".bak").exists()) {
			backupFilePath = backupFolderPath + fileName + ".bak";
		} else {
			boolean backupNameFound = false;

			// Increment number after .bak to avoid name conflicts
			for (int i = 1; ; i++) {
				if (!backupNameFound) {
					String tmpBackupFilePath = backupFolderPath + fileName + ".bak" + i;
					if (!new File(tmpBackupFilePath).exists()) {
						backupFilePath = tmpBackupFilePath;
						backupNameFound = true;
					}
				} else
					break;
			}
		}

		writeToFile(file, backupFilePath);
	}

	private static void writeToFile(File contentFile, String path) throws IOException {
		FileInputStream inStream = new FileInputStream(contentFile);
		FileOutputStream outStream = new FileOutputStream(new File(path));

		byte[] buffer = new byte[1024];
		int length;

		while ((length = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, length);
		}
	}

	public static void writeToFile(String content, String path) throws IOException {
		PrintWriter writer = new PrintWriter(new File(path));

		System.out.println(content);
		writer.print(content);
		writer.flush();
	}

}
