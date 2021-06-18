package com.shuangpeng.Problem;

public class Problem0413ArithmeticSlices {

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int diff = 0;
        int answer = 0;
        int count = 1;
        for (int i = 1; i < n; i++) {
            int d = nums[i] - nums[i - 1];
            if (d == diff) {
                count++;
            } else {
                count = 2;
                diff = d;
            }
            answer += (count >= 3 ? count - 2 : 0);
        }
        return answer;
    }
}
