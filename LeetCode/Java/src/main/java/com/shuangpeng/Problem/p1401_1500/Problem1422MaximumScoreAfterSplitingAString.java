package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1422MaximumScoreAfterSplitingAString（分割字符串的最大得分）
 * @Date 2022/8/14 10:17 AM
 * @Version 1.0
 */
public class Problem1422MaximumScoreAfterSplitingAString {

    public int maxScore(String s) {
        int total = 0;
        int n = s.length();
        int zeros = 0, ones = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                total++;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                zeros++;
            } else {
                ones++;
            }
            ans = Math.max(ans, zeros + total - ones);
        }
        return ans;
    }
}
