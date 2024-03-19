package com.shuangpeng.Problem.p1801_1900;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1863SumOfAllSubsetXORTotals（找出所有子集的异或总和再求和）
 * @date 2024/3/18 3:24 PM
 */
public class Problem1863SumOfAllSubsetXORTotals {

    private int sum = 0;

    public int subsetXORSum(int[] nums) {
        sum = 0;
        dfs(nums, 0, 0);
        return sum;
    }

    private void dfs(int[] nums, int index, int s) {
        if (index == nums.length) {
            sum += s;
            return;
        }
        dfs(nums, index + 1, s);
        dfs(nums, index + 1, s ^ nums[index]);
    }
}

class Problem1863SumOfAllSubsetXORTotals0 {

    public int subsetXORSum0(int[] nums) {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        int sum = 0;
        for (int num : nums) {
            for (int i = list.size() - 1; i >= 0; i--) {
                int xor = num ^ list.get(i);
                sum += xor;
                list.add(xor);
            }
        }
        return sum;
    }

    public int subsetXORSum1(int[] nums) {
        int n = nums.length, N = 1 << n;
        int[] dp = new int[N];
        int sum = 0;
        for (int i = 1; i < N; i++) {
            dp[i] = nums[Integer.numberOfTrailingZeros(i)] ^ dp[i ^ (i & -i)];
            sum += dp[i];
        }
        return sum;
    }

    public int subsetXORSum(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans |= num;
        }
        return ans << nums.length - 1;
    }
}
