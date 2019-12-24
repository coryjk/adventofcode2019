package day01;

import java.util.List;
import tools.AdventTools;

public class RocketEquation {
    /**
     * Fuel required to launch a given module is based on its mass. 
     * Specifically, to find the fuel required for a module, take its mass, 
     * divide by three, round down, and subtract 2.
     */
    public static int sumFuelRequirements(List<String> input) {
        int sum = input.stream()
                       .mapToInt(i -> Integer.parseInt(i)/3 - 2)
                       .sum();
        return sum;
    }           

    private static int fuel(int n) {
        int f = n/3 - 2;
        if (f <= 0) {
            return 0;
        }
        return f + fuel(f);
    }

    /** 
     * So, for each module mass, calculate its fuel and add it to the total. 
     * Then, treat the fuel amount you just calculated as the input mass and repeat the 
     * process, continuing until a fuel requirement is zero or negative.
     */
    public static int sumRecursiveFuelRequirements(List<String> input) {
        int sum = input.stream()
                       .mapToInt(i -> fuel(Integer.parseInt(i)))
                       .sum();
        return sum;
    }

    /**
     * Wrapper method
     */
    public static void solve(String filename) {
        List<String> input = AdventTools.readInputByLine(filename);
        AdventTools.printSolutions(1, sumFuelRequirements(input), sumRecursiveFuelRequirements(input));
    }
}