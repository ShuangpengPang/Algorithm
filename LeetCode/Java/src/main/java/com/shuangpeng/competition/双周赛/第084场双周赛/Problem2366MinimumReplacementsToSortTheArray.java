package com.shuangpeng.competition.双周赛.第084场双周赛;

/**
 * @Description: Problem2366MinimumReplacementsToSortTheArray（将数组排序的最少替换次数）
 * @Date 2022/8/20 5:53 PM
 * @Version 1.0
 */
public class Problem2366MinimumReplacementsToSortTheArray {

    // 比赛时写法
    public long minimumReplacement0(int[] nums) {
        int n = nums.length;
        long ans = 0L;
        int max = nums[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] <= max) {
                max = nums[i];
                continue;
            }
            int cnt = nums[i] / max;
            if (nums[i] % max != 0) {
                cnt++;
            }
            ans += cnt - 1;
            max = nums[i] / cnt;
        }
        return ans;
    }

    public long minimumReplacement(int[] nums) {
        int n = nums.length;
        long ans = 0;
        for (int i = n - 2, next = nums[n - 1]; i >= 0; i--) {
            if (nums[i] > next) {
                int cnt = (nums[i] - 1) / next;
                next = nums[i] / (cnt + 1);
                ans += cnt;
            } else {
                next = nums[i];
            }
        }
        return ans;
    }
}
