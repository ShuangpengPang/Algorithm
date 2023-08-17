package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2680MaximumOR（最大或值）
 * @date 2023/8/17 4:52 PM
 */
public class Problem2680MaximumOR {

    public long maximumOr(int[] nums, int k) {
        int[] cnt = new int[31];
        int or = 0;
        for (int num : nums) {
            or |= num;
            for (int i = 0; i < 31; i++) {
                if ((num >> i & 1) == 1) {
                    cnt[i]++;
                }
            }
        }
        long ans = or;
        for (int num : nums) {
            int value = or;
            for (int i = 0; i < 31; i++) {
                if ((num >> i & 1) == 1 && cnt[i] == 1) {
                    value ^= 1 << i;
                }
            }
            ans = Math.max(ans, value | ((long) num << k));
        }
        return ans;
    }
}
