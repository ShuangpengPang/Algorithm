package com.shuangpeng.Problem.p3301_3400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3397MaximumNumberOfDistinctElementsAfterOperations（执行操作后不同元素的最大数量）
 * @date 2025/4/8 17:32
 */
public class Problem3397MaximumNumberOfDistinctElementsAfterOperations {

    public int maxDistinctElements0(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, ans = 0, N = Integer.MIN_VALUE, x = N, y = x;
        for (int num : nums) {
            int l = num - k, r = num + k;
            if (y < l) {
                y = N;
            }
            x = Math.max(x, l);
            if (x <= r) {
                x++;
                ans++;
            } else if (y == N) {
                y = r;
                ans--;
            }
        }
        return ans;
    }

    public int maxDistinctElements(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, ans = 0, x = Integer.MIN_VALUE;
        for (int num : nums) {
            x = Math.max(x, num - k);
            if (x <= num + k) {
                x++;
                ans++;
            }
        }
        return ans;
    }
}
