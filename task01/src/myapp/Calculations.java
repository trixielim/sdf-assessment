package myapp;

import java.util.ArrayList;

public class Calculations {
    public static Float calculateMean(ArrayList<Float> values) {
        Float sum = 0.0f;
        if (values.size() == 0) {
            return sum;
        }
        for (float value : values) {
            sum += value;
        }
        return (float) sum / values.size();
    }
    
    public static Float calculateStandardDeviation(ArrayList<Float> values) {
        Float mean = Calculations.calculateMean(values);
        Float variance = 0.0f;
        for (Float value : values) {
            variance += (value - mean) * (value - mean);
        }
        variance /= values.size();
        return (float) Math.sqrt(variance);
    }
}
