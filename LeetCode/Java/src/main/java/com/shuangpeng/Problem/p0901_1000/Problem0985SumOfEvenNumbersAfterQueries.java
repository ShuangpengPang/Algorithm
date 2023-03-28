package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0985SumOfEvenNumbersAfterQueries（查询后的偶数和）
 * @date 2023/3/28 3:16 PM
 */
public class Problem0985SumOfEvenNumbersAfterQueries {

    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int sum = 0;
        for (int num : nums) {
            if ((num & 1) == 0) {
                sum += num;
            }
        }
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int val = queries[i][0], index = queries[i][1];
            if ((nums[index] & 1) == 0) {
                sum -= nums[index];
            }
            nums[index] += val;
            if ((nums[index] & 1) == 0) {
                sum += nums[index];
            }
            ans[i] = sum;
        }
        return ans;
    }
}
