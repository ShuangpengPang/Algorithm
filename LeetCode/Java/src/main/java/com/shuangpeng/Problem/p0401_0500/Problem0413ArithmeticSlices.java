package com.shuangpeng.Problem.p0401_0500;

public class Problem0413ArithmeticSlices {

    public int numberOfArithmeticSlices0(int[] nums) {
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

    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int count = 0, total = 0;
        for (int i = 2; i < n; ++i) {
            if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
                count++;
                total += count;
            } else {
                count = 0;
            }
        }
        return total;
    }
}
