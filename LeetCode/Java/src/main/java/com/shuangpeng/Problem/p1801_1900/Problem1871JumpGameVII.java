package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1871JumpGameVII（跳跃游戏VII）
 * @date 2023/10/20 1:23 PM
 */
public class Problem1871JumpGameVII {

    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = true;
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            if (i - minJump >= 0 && dp[i - minJump]) {
                cnt++;
            }
            if (i - maxJump - 1 >= 0 && dp[i - maxJump - 1]) {
                cnt--;
            }
            if (s.charAt(i) == '0' && cnt > 0) {
                dp[i] = true;
            }
        }
        return dp[n - 1];
    }
}
