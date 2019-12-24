import day01.*;
import day02.*;

public class Solver {
    static final String DAY01INPUT = "data\\input01.txt";
    static final String DAY02INPUT = "data\\input02.txt";
    static final String DAY03INPUT = "data\\input03.txt";
    public static void main(String[] args) {
        System.out.println();
        day01.RocketEquation.solve(DAY01INPUT);    
        day02.ProgramAlarm.solve(DAY02INPUT);
        day03.CrossedWires.solve(DAY03INPUT);
    }
}