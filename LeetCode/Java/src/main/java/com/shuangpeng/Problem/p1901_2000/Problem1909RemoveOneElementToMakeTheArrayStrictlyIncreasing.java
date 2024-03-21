package com.shuangpeng.Problem.p1901_2000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1909RemoveOneElementToMakeTheArrayStrictlyIncreasing（删除一个元素使数组严格递增）
 * @date 2024/3/21 11:44 AM
 */
public class Problem1909RemoveOneElementToMakeTheArrayStrictlyIncreasing {

    public boolean canBeIncreasing(int[] nums) {
        boolean remove = false;
        int first = Integer.MIN_VALUE, second = first;
        for (int i = 0; i < nums.length; i++) {
            if (second >= nums[i]) {
                if (remove) {
                    return false;
                }
                if (nums[i] > first) {
                    second = nums[i];
                }
                remove = true;
            } else {
                first = second;
                second = nums[i];
            }
        }
        return true;
    }
}
