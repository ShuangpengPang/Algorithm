package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3076ShortestUnCommonSubstringInAnArray（数组中的最短非公共子字符串）
 * @date 2024/3/23 12:23 AM
 */
public class Problem3076ShortestUnCommonSubstringInAnArray {

    public String[] shortestSubstrings(String[] arr) {
        Map<Long, Integer> map = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            String s = arr[i];
            int m = s.length();
            for (int j = 0; j < m; j++) {
                long value = 0;
                for (int k = j; k < m; k++) {
                    value = value * 27 + s.charAt(k) - 'a' + 1;
                    int idx = map.getOrDefault(value, i);
                    map.put(value, idx == i ? i : -1);
                }
            }
        }
        String[] ans = new String[n];
        Arrays.fill(ans, "");
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            if (entry.getValue() != -1) {
                int i = entry.getValue();
                String s = toString(entry.getKey());
                if (ans[i].length() == 0 || ans[i].length() > s.length() || ans[i].length() == s.length() && s.compareTo(ans[i]) < 0) {
                    ans[i] = s;
                }
            }
        }
        return ans;
    }

    private String toString(long value) {
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            sb.append((char) (value % 27 + 'a' - 1));
            value /= 27;
        }
        return sb.reverse().toString();
    }
}
