import day01.*;
import day02.*;

public class Solver {
    static final String DAY01INPUT = "data\\input01.txt";
    static final String DAY02INPUT = "data\\input02.txt";
    public static void main(String[] args) {
        day01.RocketEquation.solve(DAY01INPUT);    
        day02.ProgramAlarm.solve(DAY02INPUT);
    }
}