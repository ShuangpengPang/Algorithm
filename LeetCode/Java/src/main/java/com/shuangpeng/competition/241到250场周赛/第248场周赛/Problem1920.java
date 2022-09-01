package com.shuangpeng.competition.第248场周赛;

public class Problem1920 {

    public int[] buildArray0(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i] = nums[nums[i]];
        }
        return answer;
    }

    public int[] buildArray(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int value = nums[nums[i]] % 1000;
            nums[i] += value * 1000;
        }
        for (int i = 0; i < n; i++) {
            nums[i] /= 1000;
        }
        return nums;
    }
}
