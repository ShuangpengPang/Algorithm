package com.shuangpeng.competition.第233场周赛;

public class Problem1800 {

    public int maxAscendingSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int right = 1, answer = nums[0], sum = nums[0];
        while (right < nums.length) {
            if (nums[right] > nums[right - 1]) {
                sum += nums[right];
            } else {
                sum = nums[right];
            }
            answer = Math.max(answer, sum);
            right++;
        }
        return answer;
    }
}
