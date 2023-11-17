package com.shuangpeng.Problem.p1901_2000;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1915NumberOfWonderfulSubstrings（最美子字符串的数目）
 * @date 2023/11/17 2:40 PM
 */
public class Problem1915NumberOfWonderfulSubstrings {

    public long wonderfulSubstrings(String word) {
        int n = word.length(), mask = 0;
        long ans = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        freq.put(0, 1);
        for (int i = 0; i < n; i++) {
            mask ^= 1 << word.charAt(i) - 'a';
            ans += freq.getOrDefault(mask, 0);
            freq.merge(mask, 1, Integer::sum);
            for (int j = 0; j < 10; j++) {
                ans += freq.getOrDefault(mask ^ 1 << j, 0);
            }
        }
        return ans;
    }
}
