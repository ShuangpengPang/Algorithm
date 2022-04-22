package com.shuangpeng.Problem.p0301_0400;

public class Problem0396RotateFunction {

    public int maxRotateFunction0(int[] nums) {
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

    public int maxRotateFunction1(int[] nums) {
        int result = 0;
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            result += nums[i] * i;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            result += nums[i] * n - sum;
            ans = Math.max(ans, result);
        }
        return ans;
    }

    public int maxRotateFunction(int[] nums) {
        int sum = 0, f = 0;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            f += nums[i] * i;
        }
        int ans = f;
        for (int i = n - 1; i > 0; --i) {
            f += sum - nums[i] * n;
            ans = Math.max(ans, f);
        }
        return ans;
    }
}
