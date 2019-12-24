package tools;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdventTools {
    public static List<String> readInputByLine(String filename) {
        List<String> list = new ArrayList<String>();
        Scanner s;
        try {
            s = new Scanner(new File(filename));
            while (s.hasNextLine()) {
                list.add(s.nextLine());
            }
            // close
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void printSolutions(int day, int ans1, int ans2) {
        String res = "Day " + (day < 10 ? "0"+day : day);
        res += "\n\tPart 1: " + ans1;
        res += ans2 == -1 ? "" : "\n\tPart 2: " + ans2;
        System.out.println(res);
    }

    /** 
     * Mapper for int functions (i.e. Integer::parseInt, or s -> Integer.parseInt(s), etc.)
     */
    public static <T, U> U[] map(T[] arr, Function<T, U> function, IntFunction<U[]> res) {
        return Arrays.stream(arr)
                     .map(function)
                     .toArray(res);
    }

    /**
     * Initializer for HashMap provided key and value lists
     */
    public static <T, U> Map<T, U> createMap(List<T> keys, List<U> values) throws Exception {
        if (keys.size() != values.size()) {
            throw new Exception("Error: Keys vector must have the same dimensions as the values vector");
        }
        Map<T, U> res;
        Object[][] dotProd = new Object[keys.size()][2];
        for (int k = 0; k < keys.size(); k++) {
            // populate key and value
            dotProd[k][0] = keys.get(k);
            dotProd[k][1] = values.get(k);
        }
        res = Stream.of(dotProd).collect(
            Collectors.toMap(pair -> (T) pair[0],
                             pair -> (U) pair[1])
        );
        return res;
    }

    /** 
     * Simple 2-element Tuple class 
     */
    public static class Pair {
        public final int x;
        public final int y;
        
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair)) {
                return false;
            }
            Pair p = (Pair) o;
            return this.x == p.x && this.y == p.y;
        }

        @Override
        public int hashCode() {
            int hash = 13;
            hash = hash*17 + this.x;
            hash = hash*17 + this.y;
            return hash;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}