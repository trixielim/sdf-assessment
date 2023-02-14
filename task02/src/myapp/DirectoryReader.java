package myapp;

import java.io.File;
import java.util.ArrayList;

public class DirectoryReader {
    public static ArrayList<String> listFilesInDirectory(String directoryName) {
        ArrayList<String> fileNames = new ArrayList<String>();
        File directory = new File(directoryName);
        File[] files = directory.listFiles();
        for (File file: files) {
            // ignores directories
            if (file.isFile()) {
                fileNames.add(directoryName + "/" + file.getName());
            }
        }
        return fileNames;
    }

    public static void main(String[] args) {
        String directoryName = args[0];
        ArrayList<String> fileNames = DirectoryReader.listFilesInDirectory(directoryName);
        for (String fileName: fileNames) {
            System.out.println(fileName);
        }
    }
}

