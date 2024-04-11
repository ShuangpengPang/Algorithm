package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2903FindIndicesWithIndexAndValueDifferenceI（找出满足差值条件的下标I）
 * @date 2024/4/11 3:13 PM
 */
public class Problem2903FindIndicesWithIndexAndValueDifferenceI {

    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int n = nums.length, minIndex = 0, maxIndex = 0;
        for (int i = indexDifference, j = 0; i < n; i++, j++) {
            if (nums[j] < nums[minIndex]) {
                minIndex = j;
            }
            if (nums[j] > nums[maxIndex]) {
                maxIndex = j;
            }
            if (Math.abs(nums[minIndex] - nums[i]) >= valueDifference) {
                return new int[]{minIndex, i};
            }
            if (Math.abs(nums[maxIndex] - nums[i]) >= valueDifference) {
                return new int[]{maxIndex, i};
            }
        }
        return new int[]{-1, -1};
    }
}
