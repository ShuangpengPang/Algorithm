package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3046SplitTheArray（分割数组）
 * @date 2024/4/20 10:54 AM
 */
public class Problem3046SplitTheArray {

    public boolean isPossibleToSplit(int[] nums) {
        int[] cnt = new int[101];
        for (int num : nums) {
            if (++cnt[num] > 2) {
                return false;
            }
        }
        return true;
    }
}
