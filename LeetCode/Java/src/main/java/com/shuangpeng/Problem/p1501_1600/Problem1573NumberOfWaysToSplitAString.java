package com.shuangpeng.Problem.p1501_1600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1573NumberOfWaysToSplitAString（分割字符串的方案数）
 * @date 2023/9/6 7:15 PM
 */
public class Problem1573NumberOfWaysToSplitAString {

    public int numWays(String s) {
        int n = s.length(), N = (int) 1e9 + 7;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                list.add(i);
            }
        }
        int m = list.size();
        if (m % 3 != 0) {
            return 0;
        }
        if (m == 0) {
            return (int) ((long) (n - 1) * (n - 2) / 2 % N);
        }
        int t = m / 3;
        return (int) ((long) (list.get(t) - list.get(t - 1)) * (list.get(t << 1) - list.get((t << 1) - 1)) % N);
    }
}
