package myapp;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String fileDirectory = args[0];
        ProbabilityCalculator pc = new ProbabilityCalculator(fileDirectory);
        pc.printAllWordProbabilities();
    }
}