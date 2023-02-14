package myapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextReader {
    public static ArrayList<String> readLinesFromFile(String fileName) throws IOException {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String currLine = null;
        ArrayList<String> fileLines = new ArrayList<String>();
        while ((currLine = br.readLine()) != null) {
            fileLines.add(currLine);
        }
        return fileLines;
    }

    public static void main(String[] args) throws IOException {
        String fileDirectory = args[0];
        ArrayList<String> fileNames = DirectoryReader.listFilesInDirectory(fileDirectory);
        System.out.println("fileNames: " + fileNames);

        for (String fileName: fileNames) {
            ArrayList<String> fileLines = TextReader.readLinesFromFile(fileName);
            System.out.println("fileLines for fileName: " + fileName);
            System.out.println(fileLines);
        }

    }
}

