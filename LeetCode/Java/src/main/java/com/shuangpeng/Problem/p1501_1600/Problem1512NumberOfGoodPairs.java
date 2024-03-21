package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1512NumberOfGoodPairs（好数对的数目）
 * @date 2024/3/21 4:11 PM
 */
public class Problem1512NumberOfGoodPairs {

    public int numIdenticalPairs(int[] nums) {
        int[] cnt = new int[101];
        int ans = 0;
        for (int num : nums) {
            ans += cnt[num]++;
        }
        return ans;
    }
}
