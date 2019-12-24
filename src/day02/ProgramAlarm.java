package day02;

import java.util.Arrays;
import java.util.List;

import tools.AdventTools;

public class ProgramAlarm {
    static int ADD = 1;
    static int MULT = 2;
    static int HALT = 99;

    // target output for part 02
    static int OUTPUT = 19690720;

    /**
     * Opcodes: 
     * - 99: Halt 
     * - 01: Add values M[i+1], M[i+2] 
     * - 02: Multiply values M[i+1], M[i+2]
     */
    public static int[] runIntcode(int[] intcode, int in1, int in2) {
        // create copy
        int[] res = Arrays.copyOf(intcode, intcode.length);
        // set inputs
        res[1] = in1; res[2] = in2;
        // execute
        for (int i = 0; i < res.length; i+=4) {
            int opcode = res[i];
            // halt
            if (opcode == HALT) {
                break;
            }
            // add
            else if (opcode == ADD) {
                res[res[i+3]] = res[res[i+1]] + res[res[i+2]];
            }
            // multiply
            else if (opcode == MULT) {
                res[res[i+3]] = res[res[i+1]] * res[res[i+2]];
            }
        }
        return res;
    }

    public static int nounAndVerb(int[] intcode) {
        // try all possible inputs...
        for (int noun = 0; noun < 100; noun++) {
            for (int verb = 0; verb < 100; verb++) {
                // found
                if (runIntcode(intcode, noun, verb)[0] == OUTPUT) {
                    return noun * 100 + verb;
                }
            }
        }
        // not found
        return -1;
    }

    /**
     * Wrapper method
     */
    public static void solve(String filename) {
        List<String> raw = AdventTools.readInputByLine(filename);
        Integer[] proc = AdventTools.map(raw.get(0).split(","), 
                                         s -> Integer.parseInt(s),
                                         Integer[]::new);
        int[] input = new int[proc.length];
        for (int i = 0; i < input.length; i++) {
            input[i] = proc[i];
        }                             
        int[] res = runIntcode(input, 12, 2);
        AdventTools.printSolutions(2, res[0], nounAndVerb(input));
    }

}