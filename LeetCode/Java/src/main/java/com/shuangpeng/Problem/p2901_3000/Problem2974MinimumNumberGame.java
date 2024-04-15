package com.shuangpeng.Problem.p2901_3000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2974MinimumNumberGame（最小数字游戏）
 * @date 2024/4/15 10:55 AM
 */
public class Problem2974MinimumNumberGame {

    public int[] numberGame(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i += 2) {
            nums[i] = nums[i] ^ nums[i + 1];
            nums[i + 1] = nums[i] ^ nums[i + 1];
            nums[i] = nums[i] ^ nums[i + 1];
        }
        return nums;
    }
}
