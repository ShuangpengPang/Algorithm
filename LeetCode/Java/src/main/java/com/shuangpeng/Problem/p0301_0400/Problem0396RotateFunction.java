package com.shuangpeng.Problem.p0301_0400;

public class Problem0396RotateFunction {

    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0, origin = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            origin += i * nums[i];
        }
        int answer = origin;
        for (int i = 1; i < n; i++) {
            origin += sum - (nums[n - i] * n);
            answer = Math.max(answer, origin);
        }
        return answer;
    }
}
