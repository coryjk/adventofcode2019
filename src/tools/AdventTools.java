package tools;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;

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

    /** 
     * Mapper for int functions (i.e. Integer::parseInt, or s -> Integer.parseInt(s), etc.)
     */
    public static <T, U> U[] map(T[] arr, Function<T, U> function, IntFunction<U[]> res) {
        return Arrays.stream(arr)
                     .map(function)
                     .toArray(res);
    }
}