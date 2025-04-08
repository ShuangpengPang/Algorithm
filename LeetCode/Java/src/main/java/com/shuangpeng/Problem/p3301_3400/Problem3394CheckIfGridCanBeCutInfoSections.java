package com.shuangpeng.Problem.p3301_3400;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3394CheckIfGridCanBeCutInfoSections（判断网格图能否被切割成块）
 * @date 2025/4/8 15:49
 */
public class Problem3394CheckIfGridCanBeCutInfoSections {

    public boolean checkValidCuts(int n, int[][] rectangles) {
        return check(rectangles, 0) || check(rectangles, 1);
    }

    private boolean check(int[][] rectangle, int s) {
        TreeSet<int[]> set = new TreeSet<>(Comparator.comparingInt(a -> a[0]));
        for (int[] r : rectangle) {
            int[] p = {r[s], r[s + 2]};
            int[] f = set.lower(p);
            if (f != null && f[1] > p[0]) {
                p[0] = f[0];
                p[1] = Math.max(p[1], f[1]);
                set.remove(f);
            }
            for (int[] e = set.ceiling(p); e != null && e[0] < p[1]; e = set.ceiling(e)) {
                p[1] = Math.max(p[1], e[1]);
                set.remove(e);
            }
            set.add(p);
        }
        return set.size() >= 3;
    }

//    public static void main(String[] args) {
//        Problem3394CheckIfGridCanBeCutInfoSections a = new Problem3394CheckIfGridCanBeCutInfoSections();
//        int[][] r = {{0,0,2,2},{2,0,4,2},{4,0,6,2},{0,2,3,4},{0,4,1,6},{1,4,3,6},{3,2,4,6},{4,2,5,6},{5,2,6,6}};
//        a.checkValidCuts(6, r);
//    }
}

class Problem3394CheckIfGridCanBeCutInfoSections0 {

    public boolean checkValidCuts(int n, int[][] rectangles) {
        int m = rectangles.length;
        int[][] a = new int[m][2], b = new int[m][2];
        for (int i = 0; i < m; i++) {
            int[] r = rectangles[i];
            a[i][0] = r[0];
            a[i][1] = r[2];
            b[i][0] = r[1];
            b[i][1] = r[3];
        }
        return check(a) || check(b);
    }

    private boolean check(int[][] a) {
        Arrays.sort(a, Comparator.comparingInt(e -> e[0]));
        int n = a.length, cnt = 0;
        for (int i = 0, r = -1; i < n && cnt < 3; i++) {
            if (a[i][0] >= r) {
                cnt++;
            }
            r = Math.max(r, a[i][1]);
        }
        return cnt == 3;
    }
}

class Problem3394CheckIfGridCanBeCutInfoSections1 {
    static int bits = 30;
    static int mask = (1 << bits) - 1;
    public boolean checkValidCuts(int n, int[][] rectangles) {

        int m = rectangles.length;
        long[] start = new long[m];

        for (int i = 0; i < m; i++)
            start[i] = ((long) rectangles[i][0] << bits) + rectangles[i][2];

        Arrays.sort(start);
        if(validate(start))
            return true;

        for (int i = 0; i < m; i++)
            start[i] = ((long) rectangles[i][1] << bits) + rectangles[i][3];

        Arrays.sort(start);
        return validate(start);
    }

    private boolean validate(long[] nums) {

        int count = 0;
        int maxRight = -1;

        for (long x: nums) {
            int left = (int) (x >> bits), right = (int) (x & mask);

            if (left >= maxRight && ++count == 3)
                return true;

            maxRight = Math.max(maxRight, right);
        }

        return false;
    }
}
