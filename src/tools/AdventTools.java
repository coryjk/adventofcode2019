package tools;

import java.io.File;
import java.util.*;

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
}