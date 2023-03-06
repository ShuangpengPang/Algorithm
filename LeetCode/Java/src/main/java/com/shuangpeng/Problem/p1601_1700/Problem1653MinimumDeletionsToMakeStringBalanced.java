package com.shuangpeng.Problem.p1601_1700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1653MinimumDeletionsToMakeStringBalanced（使字符串平衡的最少删除次数）
 * @date 2023/3/6 4:09 PM
 */
public class Problem1653MinimumDeletionsToMakeStringBalanced {

    public int minimumDeletions(String s) {
        int n = s.length();
        int[] cnt = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            cnt[i] = cnt[i + 1] + (s.charAt(i) == 'a' ? 1 : 0);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0, count = 0; i <= n; i++) {
            ans = Math.min(ans, count + cnt[i]);
            if (i < n && s.charAt(i) == 'b') {
                count++;
            }
        }
        return ans;
    }
}
