package myapp;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
    
        String fileDirectory = args[0];
        ProbabilityCalculator wd = new ProbabilityCalculator(fileDirectory);
        wd.printAllWordProbabilities();
    }

    public static Float calculateMean(ArrayList<Float> values) {
        return null;
    }
}
    // public static void main(String[] args) {

    //     if (args.length > 0) {
    //         Path p = Paths.get(args[0]);
    //         File f = p.toFile();
    //         System.out.println("Your file is" + f);

    //         }else{
    //             System.out.println("Please enter a valid directory name!");
    //         }}

    


        
        

    //     Map<String, Integer> wordFreq = new HashMap<>();

    //     // Open a file as input stream
    //     InputStream is = new FileInputStream(f);
    //     // Convert the input stream to a reader byte -> char (2 bytes)
    //     InputStreamReader isr = new InputStreamReader(is);
    //     // Read whole lines
    //     BufferedReader br = new BufferedReader(isr);
    //     String line;
    //     int numWords = 0;
    //     while ((line = br.readLine()) != null) {
    //         System.out.printf("> %s\n", line.toUpperCase());
    //         String[] words = line.split(" ");
    //         numWords += words.length;
    //         // Iterate the all the words in the line
    //         for (String w: words) {
    //             String t = w.trim().toLowerCase();
    //             if (t.length() <= 0) {
    //                 continue;
    //             }
    //             if (!wordFreq.containsKey(t)) {
    //                 // If word is not in map, initialize the word with freq 1
    //                 wordFreq.put(t, 1);
    //             } else {
    //                 // If word is in map, the increment the count
    //                 int c = wordFreq.get(t);
    //                 wordFreq.put(t, c + 1);
    //             }
    //         }
    //     }

    //     // Get a list of all the words
    //     Set<String> words = wordFreq.keySet();
    //     for (String w: words) {
    //         int count = wordFreq.get(w);
    //         System.out.printf(": %s = %d\n", w, count);
    //     }

    //     System.out.printf("Number of words: %d\n", numWords);
    //     System.out.printf("Number of unique words: %d\n", words.size());
    //     System.out.printf("Percentage of unique words: %f\n", words.size()/(float)numWords);

    //     br.close();
    //     isr.close();
    //     is.close();
    

