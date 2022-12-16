package com.shuangpeng.competition.第231到240场周赛.第231场周赛;

/**
 * @description: 构成特定和需要添加的最少元素
 * @date 2022/12/16 11:18 AM
 **/
public class Problem1785MinimumElementsToAddToFormAGivenSum {

    public int minElements0(int[] nums, int limit, int goal) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long diff = Math.abs(goal - sum);
        if (diff == 0) {
            return 0;
        }
        if (diff % limit == 0) {
            return (int) (diff / limit);
        }
        return (int) (diff / limit + 1);
    }

    public int minElements1(int[] nums, int limit, int goal) {
        if (nums == null || nums.length == 0 || limit == 0) {
            return 0;
        }
        long target = goal;
        for (int num : nums) {
            target -= num;
        }
        return (int) Math.ceil(((double) Math.abs(target)) / limit);
    }

    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0L;
        for (int num : nums) {
            sum += num;
        }
        return (int) ((Math.abs(goal - sum) + limit - 1) / limit);
    }
}
