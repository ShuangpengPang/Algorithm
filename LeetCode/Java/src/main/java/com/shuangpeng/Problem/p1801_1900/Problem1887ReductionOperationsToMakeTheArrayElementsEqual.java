package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1887ReductionOperationsToMakeTheArrayElementsEqual（使数组元素相等的减少操作次数）
 * @date 2023/11/1 2:02 PM
 */
public class Problem1887ReductionOperationsToMakeTheArrayElementsEqual {

    public int reductionOperations0(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, ans = 0, cnt = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                cnt++;
            }
            ans += cnt;
        }
        return ans;
    }

    public int reductionOperations(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0, n = nums.length;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] != nums[i + 1]) {
                cnt += n - i - 1;
            }
        }
        return cnt;
    }
}
