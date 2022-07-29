package com.shuangpeng.Problem.p0501_0600;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem0593ValidSquare（有效的正方形）
 * @Date 2022/7/29 10:06 AM
 * @Version 1.0
 */
public class Problem0593ValidSquare {

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
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
}
