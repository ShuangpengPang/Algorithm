package com.shuangpeng.competition.第305场周赛;

/**
 * @Description: Problem2367NumberOfArithmeticTriplets（算术三元组的数目）
 * @Date 2022/8/20 6:50 PM
 * @Version 1.0
 */
public class Problem2367NumberOfArithmeticTriplets {

    public int arithmeticTriplets(int[] nums, int diff) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0, j = 1, k = 2; k < n; i++) {
            while (j < n && nums[j] < nums[i] + diff) {
                j++;
            }
            if (j == n) {
                break;
            }
            while (k < n && nums[k] < nums[j] + diff) {
                k++;
            }
            if (k == n) {
                break;
            }
            if (nums[j] - nums[i] == diff && nums[k] - nums[j] == diff) {
                ans++;
            }
        }
        return ans;
    }
}
