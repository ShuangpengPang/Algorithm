package com.shuangpeng.Problem.p0501_0600;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem0593ValidSquare（有效的正方形）
 * @Date 2022/7/29 10:06 AM
 * @Version 1.0
 */
public class Problem0593ValidSquare {

    public boolean validSquare0(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (Arrays.equals(p1, p2)) {
            return false;
        }
        if (help(p1, p2, p3, p4)) {
            return true;
        }
        if (Arrays.equals(p1, p3)) {
            return false;
        }
        if (help(p1, p3, p2, p4)) {
            return true;
        }
        if (Arrays.equals(p1, p4)) {
            return false;
        }
        if (help(p1, p4, p2, p3)) {
            return true;
        }
        return false;
    }

    public boolean help(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] v1 = {p1[0] - p2[0], p1[1] - p2[1]};
        int[] v2 = {p3[0] - p4[0], p3[1] - p4[1]};
        if (checkMidPoint(p1, p2, p3, p4) && checkLength(v1, v2) && calCos(v1, v2)) {
            return true;
        }
        return false;
    }

    public boolean checkLength(int[] v1, int[] v2) {
        return (v1[0] * v1[0] + v1[1] * v1[1]) == (v2[0] * v2[0] + v2[1] * v2[1]);
    }

    public boolean checkMidPoint(int[] p1, int[] p2, int[] p3, int[] p4) {
        return (p1[0] + p2[0]) == (p3[0] + p4[0]) && (p1[1] + p2[1]) == (p3[1] + p4[1]);
    }

    public boolean calCos(int[] v1, int[] v2) {
        return (v1[0] * v2[0] + v1[1] * v2[1]) == 0;
    }

    public boolean validSquare1(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] arr = new int[4][2];
        arr[0] = p1;
        arr[1] = p2;
        arr[2] = p3;
        arr[3] = p4;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    int a = arr[i][0] - arr[j][0], b = arr[i][1] - arr[j][1];
                    int c = a * a + b * b;
                    if (c == 0) {
                        return false;
                    }
                    set.add(c);
                    if (set.size() > 2) {
                        return false;
                    }
                }
            }
        }
        return set.size() == 2;
    }

    public boolean validSquare2(int[] p1, int[] p2, int[] p3, int[] p4) {
        return check(p1, p2, p3) && check(p1, p2, p4) && check(p1, p3, p4) && check(p2, p3, p4);
    }

    private boolean check(int[] p1, int[] p2, int[] p3) {
        int a = calculate(p1, p2), b = calculate(p1, p3), c = calculate(p2, p3);
        if (a == 0 || b == 0 || c == 0) {
            return false;
        }
        if (a == b) {
            return a + b == c;
        } else if (a == c) {
            return a + c == b;
        } else if (b == c) {
            return b + c == a;
        }
        return false;
    }

    private int calculate(int[] p1, int[] p2) {
        return  (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int len1_2 = cal(p1, p2);
        int len1_3 = cal(p1, p3);
        int len1_4 = cal(p1, p4);
        if (len1_2 == 0 || len1_3 == 0 || len1_4 == 0){
            return false;
        }
        int len2_3 = cal(p2, p3);
        int len2_4 = cal(p2, p4);
        int len3_4 = cal(p3, p4);

        if(len1_2 == len1_3 && len2_3 == len1_4 && len1_2 == len2_4 && len1_3 == len3_4){
            return true;
        }else if(len1_2 == len1_4 && len2_4 == len1_3 && len1_2 == len2_3 && len1_4 == len3_4){
            return true;
        }else if(len1_3 == len1_4 && len3_4 == len1_2 && len1_3 == len2_3 && len1_4 == len2_4){
            return true;
        }
        return false;
    }

    public int cal(int[] p1, int[] p2){
        return (p1[0] - p2[0])*(p1[0] - p2[0]) + (p1[1] - p2[1])*(p1[1] - p2[1]);
    }
}

