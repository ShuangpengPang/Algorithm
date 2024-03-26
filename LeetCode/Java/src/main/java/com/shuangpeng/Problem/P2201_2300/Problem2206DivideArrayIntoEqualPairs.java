package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2206DivideArrayIntoEqualPairs（将数组划分成相等数对）
 * @date 2024/3/26 4:16 PM
 */
public class Problem2206DivideArrayIntoEqualPairs {

    public boolean divideArray(int[] nums) {
        int maxValue = 0;
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }
        int[] map = new int[maxValue + 1];
        int count = 0;
        for (int num : nums) {
            count += 1 - (map[num] << 1);
            map[num] = 1 - map[num];
        }
        return count == 0;
    }
}
