package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @Description: Problem0904FruitInfoBaskets（水果成篮）
 * @Date 2022/10/17 10:05 AM
 * @Version 1.0
 */
public class Problem0904FruitInfoBaskets {

    public int totalFruit0(int[] fruits) {
        int n = fruits.length, ans = 0;
        int[][] types = new int[2][2];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(types[i], -1);
        }
        int prev = -1;
        for (int i = 0; i < n; i++) {
            int idx = getIndex(types, fruits[i]);
            if (idx != -1) {
                types[idx][1] = i;
            } else {
                int emptyIndex = getEmptyIndex(types);
                if (emptyIndex != -1) {
                    types[emptyIndex][0] = fruits[i];
                    types[emptyIndex][1] = i;
                } else {
                    int minIndex = getMinIndex(types);
                    prev = types[minIndex][1];
                    types[minIndex][0] = fruits[i];
                    types[minIndex][1] = i;
                }
            }
            ans = Math.max(ans, i - prev);
        }
        return ans;
    }

    private int getIndex(int[][] types, int t) {
        if (types[0][0] == t) {
            return 0;
        } else if (types[1][0] == t) {
            return 1;
        }
        return -1;
    }

    private int getEmptyIndex(int[][] types) {
        if (types[0][0] == -1) {
            return 0;
        } else if (types[0][1] == -1) {
            return 1;
        }
        return -1;
    }

    private int getMinIndex(int[][] types) {
        if (types[0][1] < types[1][1]) {
            return 0;
        }
        return 1;
    }

    public int totalFruit1(int[] fruits) {
        int n = fruits.length, ans = 0;
        int minIndex = -1, maxIndex = -1, prev = -1;
        for (int i = 0; i < n; i++) {
            if (maxIndex != -1 && fruits[maxIndex] != fruits[i]) {
                if (minIndex != -1 && fruits[minIndex] != fruits[i]) {
                    prev = minIndex;
                }
                minIndex = maxIndex;
            }
            maxIndex = i;
            ans = Math.max(ans, i - prev);
        }
        return ans;
    }

    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int idx = -1, prev = -1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            if (fruits[i] != fruits[i - 1]) {
                if (idx != -1 && fruits[idx] != fruits[i]) {
                    prev = idx;
                }
                idx = i - 1;
            }
            ans = Math.max(ans, i - prev);
        }
        return ans;
    }
}
