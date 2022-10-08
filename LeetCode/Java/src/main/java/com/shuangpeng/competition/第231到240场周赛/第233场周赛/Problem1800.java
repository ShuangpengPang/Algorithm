package com.shuangpeng.competition.第231到240场周赛.第233场周赛;

/**
 * @Description:（最大升序子数组和）
 * @Date 2022/10/8 6:21 PM
 **/
public class Problem1800 {

    public int maxAscendingSum0(int[] nums) {
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

    public int maxAscendingSum(int[] nums) {
        int ans = 0, sum = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] > nums[i - 1]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
