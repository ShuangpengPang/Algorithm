package com.shuangpeng.competition.第254场周赛;

import java.util.Arrays;

public class Problem1968 {

    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int left = 0, right = n - 1;
        int[] answer = new int[n];
        boolean flag = true;
        int i = 0;
        while (left <= right) {
            if (flag) {
                answer[i] = nums[left];
                left++;
            } else {
                answer[i] = nums[right];
                right--;
            }
            flag = !flag;
            i++;
        }
        return answer;
    }
}
