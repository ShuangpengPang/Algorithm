package com.shuangpeng.Problem.p0701_0800;

/**
 * @Description: Problem0713SubarrayProductLessThanK
 * @Date 2022/5/5 10:17 AM
 * @Version 1.0
 */
public class Problem0713SubarrayProductLessThanK {

    public int numSubarrayProductLessThanK0(int[] nums, int k) {
        int n = nums.length;
        int last = -1, product = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (last == -1) {
                if (nums[i] < k) {
                    last = i;
                    product = nums[i];
                    ++ans;
                }
            } else {
                while (last < i && product * nums[i] >= k) {
                    product /= nums[last];
                    ++last;
                }
                if (product * nums[i] < k) {
                    product *= nums[i];
                    ans += i - last + 1;
                } else {
                    last = -1;
                    product = 0;
                }
            }
        }
        return ans;
    }

    public int numSubarrayProductLessThanK1(int[] nums, int k) {
        if (k <= 0) {
            return 0;
        }
        int n = nums.length;
        double[] preSum = new double[n];
        preSum[0] = Math.log(nums[0]);
        for (int i = 1; i < n; ++i) {
            preSum[i] = preSum[i - 1] + Math.log(nums[i]);
        }
        int ans = 0;
        final double d = 1e-10;
        double logK = Math.log(k);
        for (int i = 0; i < n; ++i) {
            int left = 0, right = i;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                double sum = mid == 0 ? preSum[i] : preSum[i] - preSum[mid - 1];
                if (sum + d < logK) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans += i - left + 1;
        }
        return ans;
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length;
        int ans = 0, product = 1, idx = 0;
        for (int i = 0; i < n; ++i) {
            product *= nums[i];
            while (idx <= i && product >= k) {
                product /= nums[idx];
                ++idx;
            }
            ans += i - idx + 1;
        }
        return ans;
    }
}
