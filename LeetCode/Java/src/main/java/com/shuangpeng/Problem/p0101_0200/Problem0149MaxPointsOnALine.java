package com.shuangpeng.Problem.p0101_0200;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem0149MaxPointsOnALine {

    //        [[0,0],[1,65536],[65536,0]]

    // (a, b) (c, d) (e, f)
    // (d - b)e - ad = (c - a)f - bc

//    public static void main(String[] args) {
//        Problem0149MaxPointsOnALine a = new Problem0149MaxPointsOnALine();
//        int[][] points = {{0,-1},{0,3},{0,-4},{0,-2},{0,-4},{0,0},{0,0},{0,1},{0,-2},{0,4}};
//        a.maxPoints(points);
//    }

    public int maxPoints1(int[][] points) {
        if (points == null) {
            return 0;
        }
        if (points.length <= 2) {
            return points.length;
        }
        int max = 0;
        Map<String, Integer> map = new HashMap<>(points.length);
        for (int i = 0; i < points.length; i++) {
            String key = "" + points[i][0] + ',' + points[i][1];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int length = map.size();
        int[][] copy = new int[length][2];
        Set<String> keySet = map.keySet();
        int index = 0;
        for (String key : keySet) {
            String[] strings = key.split(",");
            copy[index][0] = Integer.parseInt(strings[0]);
            copy[index][1] = Integer.parseInt(strings[1]);
            index++;
        }
        for (int i = 0; i < length; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(i);
            long x1 = copy[i][0];
            long y1 = copy[i][1];
            max = Math.max(max, map.get("" + x1 + ',' + y1));
            for (int j = i + 1; j < length; j++) {
                if (!set.contains(j)) {
                    set.clear();
                    set.add(i);
                    set.add(j);
                    long x2 = copy[j][0];
                    long y2 = copy[j][1];
                    for (int k = j + 1; k < length; k++) {
                        long x3 = copy[k][0];
                        long y3 = copy[k][1];
                        if (((y2 - y1) * x3 - x1 * y2) == ((x2 - x1) * y3 - y1 * x2)) {
                            set.add(k);
                        }
                    }
                    int count = 0;
                    for (int item : set) {
                        String key = "" + copy[item][0] + ',' + copy[item][1];
                        count += map.get(key);
                    }
                    max = Math.max(max, count);
                }
            }
        }
        return max;
    }


    int[][] points;
    int n;
    HashMap<Double, Integer> lines = new HashMap<Double, Integer>();
    int horisontal_lines;

    public Pair<Integer, Integer> add_line(int i, int j, int count, int duplicates) {
    /*
    Add a line passing through i and j points.
    Update max number of points on a line containing point i.
    Update a number of duplicates of i point.
    */
        // rewrite points as coordinates
        int x1 = points[i][0];
        int y1 = points[i][1];
        int x2 = points[j][0];
        int y2 = points[j][1];
        // add a duplicate point
        if ((x1 == x2) && (y1 == y2))
            duplicates++;
            // add a horisontal line : y = const
        else if (y1 == y2) {
            horisontal_lines += 1;
            count = Math.max(horisontal_lines, count);
        }
        // add a line : x = slope * y + c
        // only slope is needed for a hash-map
        // since we always start from the same point
        else {
            double slope = 1.0 * (x1 - x2) / (y1 - y2) + 0.0;
            lines.put(slope, lines.getOrDefault(slope, 1) + 1);
            count = Math.max(lines.get(slope), count);
        }
        return new Pair(count, duplicates);
    }

    public int max_points_on_a_line_containing_point_i(int i) {
    /*
    Compute the max number of points
    for a line containing point i.
    */
        // init lines passing through point i
        lines.clear();
        horisontal_lines = 1;
        // One starts with just one point on a line : point i.
        int count = 1;
        // There is no duplicates of a point i so far.
        int duplicates = 0;

        // Compute lines passing through point i (fixed)
        // and point j (interation).
        // Update in a loop the number of points on a line
        // and the number of duplicates of point i.
        for (int j = i + 1; j < n; j++) {
            Pair<Integer, Integer> p = add_line(i, j, count, duplicates);
            count = p.getKey();
            duplicates = p.getValue();
        }
        return count + duplicates;
    }


    public int maxPoints0(int[][] points) {

        this.points = points;
        // If the number of points is less than 3
        // they are all on the same line.
        n = points.length;
        if (n < 3)
            return n;

        int max_count = 1;
        // Compute in a loop a max number of points
        // on a line containing point i.
        for (int i = 0; i < n - 1; i++)
            max_count = Math.max(max_points_on_a_line_containing_point_i(i), max_count);
        return max_count;
    }


    public int maxPoints12(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            int c = 1;
            Map<Double, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                if (points[i][0] == points[j][0]) {
                    c++;
                    answer = Math.max(answer, c);
                } else {
                    double slope = 0;
                    if (points[i][1] != points[j][1]) {
                        slope = (double) (points[i][1] - points[j][1]) / (double) (points[i][0] - points[j][0]);
                    }
                    int count = map.getOrDefault(slope, 1) + 1;
                    map.put(slope, count);
                    answer = Math.max(answer, count);
                }
            }
        }
        return answer;
    }

    public int maxPoints3(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (ret >= n - i || ret > n / 2) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gcdXY = gcd(Math.abs(x), Math.abs(y));
                    x /= gcdXY;
                    y /= gcdXY;
                }
                int key = y + x * 20001;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
            int maxn = 0;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                int num = entry.getValue();
                maxn = Math.max(maxn, num + 1);
            }
            ret = Math.max(ret, maxn);
        }
        return ret;
    }

    public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (answer > n / 2 || answer >= n - i) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                x = y == 0 ? 1 : x;
                y = x == 0 ? 1 : y;
                if (y < 0) {
                    y = -y;
                    x = -x;
                }
                int gcdXY = getGcd(Math.abs(x), Math.abs(y));
                x /= gcdXY;
                y /= gcdXY;
                int key = x * 20001 + y;
                int value = map.getOrDefault(key, 1) + 1;
                map.put(key, value);
                answer = Math.max(answer, value);
            }
        }
        return answer;
    }

    private int getGcd(int x, int y) {
        return y == 0 ? x : getGcd(y, x % y);
    }

//    public static void main(String[] args) {
//        Problem0149MaxPointsOnALine a = new Problem0149MaxPointsOnALine();
//        a.maxPoints(new int[][]{{2, 3}, {3, 3}, {-5, 3}});
//    }

    // (y2 - y1) / (x2 - x1) = (y3 - y1) / (x3 - x1)
    // (y2 - y1) * (x3 - x1) = (y3 - y1) * (x2 - x1)
    //
}
