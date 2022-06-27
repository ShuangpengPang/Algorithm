package com.shuangpeng.Problem.p0501_0600;

/**
 * @Description: Problem0522LongestUncommonSubsequenceII（最长特殊序列II）
 * @Date 2022/6/27 11:34 AM
 * @Version 1.0
 */
public class Problem0522LongestUncommonSubsequenceII {

    public int findLUSlength(String[] strs) {
        int ans = -1;
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            String s = strs[i];
            int m = s.length();
            if (m <= ans) {
                continue;
            }
            boolean valid = true;
            for (int j = 0; j < n; j++) {
                if (i == j || strs[j].length() < m) {
                    continue;
                }
                if (s.equals(strs[j]) || isSubsequence(s, strs[j])) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans = Math.max(ans, s.length());
            }
        }
        return ans;
    }

    private boolean isSubsequence(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n1;
    }
}
