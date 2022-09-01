package com.shuangpeng.competition.第302场周赛;

/**
 * @Description: Problem2341MaximumNumberOfPairsInArray（数组能形成多少数对）
 * @Date 2022/7/23 11:33 AM
 * @Version 1.0
 */
public class Problem2341MaximumNumberOfPairsInArray {

    // 比赛时写法
    public int[] numberOfPairs0(int[] nums) {
        int[] cnt = new int[101];
        for (int num : nums) {
            cnt[num]++;
        }
        int ans = 0;
        for (int i = 0; i <= 100; i++) {
            ans += cnt[i] >> 1;
        }
        return new int[]{ans, nums.length - (ans << 1)};
    }

    public int[] numberOfPairs(int[] nums) {
        int[] cnt = new int[101];
        int ans = 0;
        for (int num : nums) {
            cnt[num]++;
            if (cnt[num] == 2) {
                cnt[num] = 0;
                ans++;
            }
        }
        return new int[]{ans, nums.length - (ans << 1)};
    }
}
