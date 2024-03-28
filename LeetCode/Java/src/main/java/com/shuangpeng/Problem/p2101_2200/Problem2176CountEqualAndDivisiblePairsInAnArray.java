package com.shuangpeng.Problem.p2101_2200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2176CountEqualAndDivisiblePairsInAnArray（统计数组中相等且可以被整除的数对）
 * @date 2024/3/28 4:56 PM
 */
public class Problem2176CountEqualAndDivisiblePairsInAnArray {

    public int countPairs0(int[] nums, int k) {
        int cnt = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] == nums[j] && i * j % k == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public int countPairs(int[] nums, int k) {
        int maxValue = 0, n = nums.length;
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }
        int[] head = new int[maxValue + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int h = head[nums[i]];
            for (int j = h; j > i; j = nums[j]) {
                if (i * j % k == 0) {
                    ans++;
                }
            }
            head[nums[i]] = i;
            nums[i] = h;
        }
        return ans;
    }
}
