package com.shuangpeng.Problem.p0101_0200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0167TwoSumIIInputArrayIsSorted（两数之和II-输入有序数组）
 * @date 2023/2/2 3:14 PM
 */
public class Problem0167TwoSumIIInputArrayIsSorted {

    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (numbers[i] + numbers[j] != target) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[]{i + 1, j + 1};
    }
}
