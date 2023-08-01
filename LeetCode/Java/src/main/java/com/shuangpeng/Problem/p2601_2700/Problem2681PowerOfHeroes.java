package com.shuangpeng.Problem.p2601_2700;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2681PowerOfHeroes（英雄的力量）
 * @date 2023/8/1 10:28 AM
 */
public class Problem2681PowerOfHeroes {

    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        long ans = 0, sum = 0, M = (long) 1e9 + 7;
        for (long num : nums) {
            ans = (ans + num * num % M * (sum + num)) % M;
            sum = ((sum << 1) + num) % M;
        }
        return (int) ans;
    }
}
