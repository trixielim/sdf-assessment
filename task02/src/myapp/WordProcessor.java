package myapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordProcessor {
    
    public static String removePunctuations(String word) {
        
        List<Character> characters = Arrays.asList('.', ',', ':',';', '!', '-', '(', ')', '{', '}', '`', '\'', '\"','?');
        HashSet<Character> punctuations = new HashSet<>(characters);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            boolean containsPunctuation = punctuations.contains(currChar);
            if (!containsPunctuation) {
                sb.append(currChar);;
            }
        }
        return sb.toString();
    }

    public static String cleanWord(String word) {
        return WordProcessor.removePunctuations(word).toLowerCase().trim();
    }

    public static ArrayList<String> tokenize(ArrayList<String> lines) {
        ArrayList<String> wordsInLines = new ArrayList<String>();
        for (String line: lines) {
            String[] words = line.split(" ");
            for (String word: words) {
                String cleanedWord = WordProcessor.cleanWord(word);
                wordsInLines.add(cleanedWord);
            }
        }

        return wordsInLines;
    }

    public static void main(String[] args) throws IOException {
        String fileDirectory = args[0];
        ArrayList<String> fileNames = DirectoryReader.listFilesInDirectory(fileDirectory);
        System.out.println("fileNames: " + fileNames);

        for (String fileName: fileNames) {
            ArrayList<String> fileLines = TextReader.readLinesFromFile(fileName);
            ArrayList<String> wordsInFile = WordProcessor.tokenize(fileLines);
            System.out.println("Words in file: " + fileName);
            System.out.println(wordsInFile);
        }
    }
}