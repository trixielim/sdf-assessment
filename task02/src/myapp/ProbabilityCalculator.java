package myapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ProbabilityCalculator {
    private ArrayList<String> words = null;
    private HashMap<String, HashMap<String, Integer>> wordCountTable = null;
    private HashMap<String, HashMap<String, Double>> wordProbabilityTable = null;

    public ProbabilityCalculator(String fileDirectory) throws IOException {
        this.words = ProbabilityCalculator.getAllWordsInFiles(fileDirectory);
        this.wordCountTable = ProbabilityCalculator.buildWordCountTable(this.words);
        this.wordProbabilityTable = ProbabilityCalculator.buildWordProbabilityTable(this.wordCountTable);
    }

    public static ArrayList<String> getAllWordsInFiles(String fileDirectory) throws IOException {
        ArrayList<String> fileNames = DirectoryReader.listFilesInDirectory(fileDirectory);

        ArrayList<String> allWordsInFiles = new ArrayList<String>();

        for (String fileName: fileNames) {
            ArrayList<String> fileLines = TextReader.readLinesFromFile(fileName);
            ArrayList<String> wordsInFile = WordProcessor.tokenize(fileLines);
            allWordsInFiles.addAll(wordsInFile);
        }
        return allWordsInFiles;
    }

    private static Integer getTotalCountInTable(HashMap<String, Integer> wordCountTable) {
        Collection<Integer> nextWordCounts = wordCountTable.values();
        Integer totalWordCount = 0;
        for (Integer currWordCount: nextWordCounts) {
            totalWordCount += currWordCount;
        }
        return totalWordCount;
    }

    public static Double wordProbability(Integer wordCount, Integer allWordCount) {
        return (wordCount / (double) allWordCount);
    }

    private static HashMap<String, Double> getSingleWordProbabilityTable(HashMap<String, Integer> nextWordCountTable) {
       
        Integer totalWordCount = ProbabilityCalculator.getTotalCountInTable(nextWordCountTable);

        HashMap<String, Double> nextWordProbabilityTable = new HashMap<String, Double>();

        for (HashMap.Entry<String, Integer> entry: nextWordCountTable.entrySet()) {
            String currWord = entry.getKey();
            Integer currWordCount = entry.getValue();
            Double wordProbability = ProbabilityCalculator.wordProbability(currWordCount, totalWordCount);
            nextWordProbabilityTable.put(currWord, wordProbability);
        }
        return nextWordProbabilityTable;
    }

    public static HashMap<String, HashMap<String, Double>> buildWordProbabilityTable(HashMap<String, HashMap<String, Integer>> wordCountTable) {
    
        HashMap<String, HashMap<String, Double>> table = new HashMap<String, HashMap<String, Double>>();

        for (HashMap.Entry<String, HashMap<String, Integer>> entry: wordCountTable.entrySet()) {
            String previousWord = entry.getKey();
            HashMap<String, Integer> nextWordCountTable = entry.getValue();
            HashMap<String, Double> nextWordProbabilityTable = ProbabilityCalculator.getSingleWordProbabilityTable(nextWordCountTable);
            table.put(previousWord, nextWordProbabilityTable);
        }
        return table;
    }

    public static HashMap<String, HashMap<String, Integer>> buildWordCountTable(ArrayList<String> words) {
        HashMap<String, HashMap<String, Integer>> table = new HashMap<String, HashMap<String, Integer>>();

        String previousWord = null;
        for (String word: words) {
            
            if (previousWord == null) {
                previousWord = word;
                continue;
            }

            if (!table.containsKey(previousWord)) {
                table.put(previousWord, new HashMap<String, Integer>());
            }
            HashMap<String, Integer> previousWordTable = table.get(previousWord);

            Integer newWordCount = previousWordTable.getOrDefault(word, 0) + 1;
            previousWordTable.put(word, newWordCount);

            previousWord = word;
        }

        return table;
    }

    public void printAllWordProbabilities() {
  
        for (HashMap.Entry<String, HashMap<String, Double>> entry: this.wordProbabilityTable.entrySet()) {
            String currWord = entry.getKey();
            HashMap<String, Double> currWordTable = entry.getValue();
            System.out.println(currWord);
            for (HashMap.Entry<String, Double> wordEntry: currWordTable.entrySet()) {
                String currNextWord = wordEntry.getKey();
                Double currNextWordProbability = wordEntry.getValue();
                System.out.println("\t" + currNextWord + " " + currNextWordProbability);
            }
        }
    }


    public static void main(String[] args) throws IOException {
   
        String fileDirectory = args[0];
        ProbabilityCalculator wd = new ProbabilityCalculator(fileDirectory);
        System.out.println("Words:");
        System.out.println(wd.words);
        System.out.println("Word Count Table:");
        System.out.println(wd.wordCountTable);
        System.out.println("Word Probability Table:");
        System.out.println(wd.wordProbabilityTable);
    }
}
