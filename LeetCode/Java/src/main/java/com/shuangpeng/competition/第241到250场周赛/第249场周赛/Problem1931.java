package com.shuangpeng.competition.第241到250场周赛.第249场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem1931 {

    public int colorTheGrid(int m, int n) {
        int mod = (int) 1e9 + 7;
        int max = (int) Math.pow(3, m);
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            int mask = getMask(i);
            if (check(mask, m)) {
                array.add(mask);
            }
        }
        int size = array.size();
        List<Integer>[] map = new List[size];
        for (int i = 0; i < size; i++) {
            map[i] = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                if (isValid(array.get(i), array.get(j), m)) {
                    map[i].add(j);
                }
            }
        }
        int[] dp = new int[size];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n - 1; i++) {
            int[] temp = new int[size];
            for (int j = 0; j < size; j++) {
                for (int k : map[j]) {
                    temp[j] = (temp[j] + dp[k]) % mod;
                }
            }
            dp = temp;
        }
        int answer = 0;
        for (int i = 0; i < size; i++) {
            answer = (answer + dp[i]) % mod;
        }
        return answer;
    }

    private int getMask(int k) {
        int mask = 0;
        int b = 1;
        while (k > 0) {
            mask = mask + (k % 3) * b;
            b *= 10;
            k /= 3;
        }
        return mask;
    }

    private boolean check(int mask, int m) {
        int previous = mask % 10;
        for (int i = 0; i < m - 1; i++) {
            mask /= 10;
            int remain = mask % 10;
            if (previous == remain) {
                return false;
            }
            previous = remain;
        }
        return true;
    }

    private boolean isValid(int m1, int m2, int m) {
        for (int i = 0; i < m; i++) {
            if ((m1 % 10) == (m2 % 10)) {
                return false;
            }
            m1 /= 10;
            m2 /= 10;
        }
        return true;
    }

    // 3 * 2 * 3
    // 0 1 0
    // 1 0 1
    // 0 1
}
