package day03;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import tools.AdventTools;
import tools.AdventTools.Pair;

public class CrossedWires {
    static int[][] v = { {0 , 1},   // N
                         {1 , 0},   // E
                         {0 ,-1},   // S
                         {-1, 0} }; // W
    static Map<Character, Integer> index;

    /**
     * Populates traversal set for each wire
     */
    public static Set<Pair> layWire(String[] wire) {
        Set<Pair> points = new HashSet<Pair>();
        // treat walking wires as a "walked path"
        int x = 0, y = 0, w = 0;
        // walk
        int steps, i, v_i, dx, dy;
        while (w < wire.length) {
            steps = Integer.parseInt(wire[w].substring(1));
            v_i = index.get(wire[w].charAt(0));
            i = 0;
            while (i < steps) {
                dx = v[v_i][0];
                dy = v[v_i][1];
                // update and append
                x = x + dx;
                y = y + dy;
                points.add(new Pair(x, y));
                i++;
            }
            w++;
        }
        return points;
    }

    /**
     * The wires twist and turn, but the two wires occasionally cross paths. 
     * To fix the circuit, you need to find the intersection point closest to the central port. 
     * Because the wires are on a grid, use the Manhattan distance for this measurement. 
     * While the wires do technically cross right at the central port where they both start, 
     * this point does not count, nor does a wire count as crossing with itself.
     */
    public static int intersectionDistance(List<String> wires) {
        String[] wire1 = wires.get(0).split(",");
        String[] wire2 = wires.get(1).split(",");

        // store traversed coordinates
        Set<Pair> points1 = layWire(wire1);
        Set<Pair> points2 = layWire(wire2);

        // set intersection
        points1.retainAll(points2);

        // store Manhattan distances
        List<Integer> distances = new ArrayList<Integer>();
        for (Pair pts : points1) {
            distances.add(Math.abs(pts.x) + Math.abs(pts.y));
        }
        Collections.sort(distances);
        return distances.get(0);
    }

    public static void traverseWire(String[] wire, Map<Pair, Integer> tracker) {
        // treat walking wires as a "walked path"
        int x = 0, y = 0, w = 0;
        // walk
        int steps, i, v_i, dx, dy;
        int s = 0;
        // handle each pair only once per wire
        Set<Pair> checked = new HashSet<Pair>();
        while (w < wire.length) {
            steps = Integer.parseInt(wire[w].substring(1));
            v_i = index.get(wire[w].charAt(0));
            i = 0;
            while (i < steps) {
                dx = v[v_i][0];
                dy = v[v_i][1];
                // update and append
                x = x + dx;
                y = y + dy;
                i++; s++;
                // check intersections
                Pair p = new Pair(x, y);
                if (tracker.keySet().contains(p) && !checked.contains(p)) {
                    tracker.put(p, tracker.get(p) + s); // track
                    checked.add(p);                     // prevent repeats
                }
            }
            w++;
        }
    }

    public static int intersectionSteps(List<String> wires) {
        String[] wire1 = wires.get(0).split(",");
        String[] wire2 = wires.get(1).split(",");

        // store traversed coordinates
        Set<Pair> intersections = layWire(wire1);

        // set intersection
        intersections.retainAll(layWire(wire2));

        // create tracker map
        Map<Pair, Integer> tracker;
        try {
            tracker = AdventTools.createMap(
                intersections.stream().collect(Collectors.toList()),
                new ArrayList<Integer>(Collections.nCopies(intersections.size(), 0))
            );
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        
        // perform traversals (which populates tracker map with step information)
        traverseWire(wire1, tracker);
        traverseWire(wire2, tracker);

        // find minimum steps
        return Collections.min(tracker.values());
    }

    /**
     * Wrapper method
     */
    public static void solve(String filename) {
        // populate map for easy access of directions
        try {                    
            index = AdventTools.createMap(
                // hardcoded values...
                Arrays.asList('R', 'L', 'U', 'D'),
                Arrays.asList(1, 3, 0, 2)
            );   
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }   
        List<String> wires = AdventTools.readInputByLine(filename);
        AdventTools.printSolutions(3, intersectionDistance(wires), intersectionSteps(wires));
    }
}