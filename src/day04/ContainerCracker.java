package day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tools.AdventTools;

public class ContainerCracker {
    // two numbers for input
    static final int BEGIN = 234208;
    static final int END   = 765869;

    /** 
     * facts about the password:
     *  - It is a six-digit number.
     *  - The value is within the range given in your puzzle input.
     *  - Two adjacent digits are the same (like 22 in 122345).
     *  - Going from left to right, the digits never decrease; they only ever increase or stay the same (like 111123 or 135679).
     */
    public static int countValidPasswords(int begin, int end) {
        int count = 0;
        int lastDigit = -1;
        List<Integer> notDescendingDigits = new ArrayList<Integer>();
        List<Integer> digitCount = new ArrayList<Integer>(Collections.nCopies(10,0));
        // find non-descending numbers
        outer: for (int n = begin; n <= end; n++) {
            lastDigit = -1;
            // ensure is not descending (save digits at same time)
            for (int i = n; i > 0; i /= 10) {
                if (lastDigit != -1 && i%10 > lastDigit) {
                    continue outer;
                }
                lastDigit = i%10;
            }
            notDescendingDigits.add(n);
        }
        // count digits
        for (Integer n : notDescendingDigits) {
            for (int i = n; i > 0; i /= 10) {
                digitCount.set(i%10, digitCount.get(i%10) + 1);
            }
            // check criteria
            if (Collections.max(digitCount) >= 2) {
                count++;
            }
            digitCount = new ArrayList<Integer>(Collections.nCopies(10,0));
        }
        return count;
    }

    public static void solve() {
        AdventTools.printSolutions(4, countValidPasswords(BEGIN,END), -1);
    }
}