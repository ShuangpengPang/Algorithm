package com.shuangpeng.Problem.p2601_2700;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2606FindTheSubstringWithMaximumCost（找到最大开销的子字符串）
 * @date 2024/1/8 4:39 PM
 */
public class Problem2606FindTheSubstringWithMaximumCost {

    public int maximumCostSubstring0(String s, String chars, int[] vals) {
        int[] map = new int[26];
        Arrays.setAll(map, i -> i + 1);
        int m = vals.length;
        for (int i = 0; i < m; i++) {
            map[chars.charAt(i) - 'a'] = vals[i];
        }
        int min = 0, ans = 0, n = s.length(), sum = 0;
        for (int i = 0; i < n; i++) {
            sum += map[s.charAt(i) - 'a'];
            ans = Math.max(ans, sum - min);
            min = Math.min(min, sum);
        }
        return ans;
    }

    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] values = new int[26];
        for (int i = 0; i < 26; i++) {
            values[i] = i + 1;
        }
        for (int i = 0; i < vals.length; i++) {
            values[chars.charAt(i) - 'a'] = vals[i];
        }
        int ans = 0, sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum = Math.max(0, sum) + values[s.charAt(i) - 'a'];
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
