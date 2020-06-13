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
        String backupPath = file.getParent() + "/Hero Config Backups/";

        File backupFile = null;

        // Create folder for backups if doesn't exist
        File backupFolder = new File(backupPath);

        if (!backupFolder.exists()) {
            backupFolder.mkdir();
        }

        // Create backup suffixed with .bak
        if (!new File(backupPath + fileName + ".bak").exists()) {
            backupFile = new File(backupPath + fileName + ".bak");
        } else {
            boolean backupCreated = false;

            // Increment number at end of backup
            for (int i = 1; ; i++) {
                if (!backupCreated) {
                    String backupFileName = backupPath + fileName + ".bak" + i;
                    if (!new File(backupFileName).exists()) {
                        backupFile = new File(backupFileName);
                        backupCreated = true;
                    }
                } else
                    break;
            }
        }

        // Transfer content of files
        FileOutputStream outStream = new FileOutputStream(backupFile);
        FileInputStream inStream = new FileInputStream(file);

        byte[] buffer = new byte[1024];
        int length;

        // Copies over the files

        while ((length = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, length);
        }

        inStream.close();
        outStream.close();


    }

}
