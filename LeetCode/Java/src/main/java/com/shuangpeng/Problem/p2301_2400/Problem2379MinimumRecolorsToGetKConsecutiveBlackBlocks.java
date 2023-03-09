package com.shuangpeng.Problem.p2301_2400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2379MinimumRecolorsToGetKConsecutiveBlackBlocks（得到K个黑块的最少涂色次数）
 * @date 2023/3/9 10:42 AM
 */
public class Problem2379MinimumRecolorsToGetKConsecutiveBlackBlocks {

    public int minimumRecolors(String blocks, int k) {
        int n = blocks.length();
        int ans = Integer.MAX_VALUE;
        for (int i = 0, c = 0; i < n; i++) {
            if (blocks.charAt(i) == 'W') {
                c++;
            }
            if (i >= k && blocks.charAt(i - k) == 'W') {
                c--;
            }
            if (i >= k - 1) {
                ans = Math.min(ans, c);
            }
        }
        return ans;
    }
}
