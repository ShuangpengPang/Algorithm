package com.shuangpeng.Problem.p0601_0700;

public class Problem0689MaximumSumOf3NonOverlappingSubarrays {

    public int[] maxSumOfThreeSubarrays0(int[] nums, int k) {
        int n = nums.length;
        if (n < 3) {
            return null;
        }
        int[] sums = new int[n - k + 1];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int j = i - k + 1;
            if (j > 0) {
                sum -= nums[j - 1];
            }
            if (j >= 0) {
                sums[j] = sum;
            }
        }
        int[] left = new int[sums.length];
        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < left.length; i++) {
            if (sums[i] > max) {
                max = sums[i];
                maxIndex = i;
            }
            left[i] = maxIndex;
        }
        int[] right = new int[sums.length];
        max = Integer.MIN_VALUE;
        maxIndex = 0;
        for (int i = right.length - 1; i >= 0; i--) {
            if (sums[i] >= max) {
                max = sums[i];
                maxIndex = i;
            }
            right[i] = maxIndex;
        }
        int maxSum = Integer.MIN_VALUE;
        int a = 0, b = 0, c = 0;
        for (int i = k; i <= n - (k << 1); i++) {
            int add = sums[i] + sums[left[i - k]] + sums[right[i + k]];
            if (add > maxSum) {
                maxSum = add;
                a = left[i - k];
                b = i;
                c = right[i + k];
            }
        }
        return new int[]{a, b, c};
    }

    public int[] maxSumOfThreeSubarrays1(int[] nums, int k) {
        int n = nums.length;
        int sum1 = 0, sum2 = 0, sum3 = 0;
        int maxSum1 = 0, maxSum12 = 0, maxSum = 0;
        int maxSum1Index = 0, maxSum12Index1 = 0, maxSum12Index2 = 0;
        int[] ans = new int[3];
        for (int i = k << 1; i < n; ++i) {
            sum1 += nums[i - (k << 1)];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i >= k * 3 - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSum1Index = i - 3 * k + 1;
                }
                if (maxSum1 + sum2 > maxSum12) {
                    maxSum12 = maxSum1 + sum2;
                    maxSum12Index1 = maxSum1Index;
                    maxSum12Index2 = i - 2 * k + 1;
                }
                if (maxSum12 + sum3 > maxSum) {
                    maxSum = maxSum12 + sum3;
                    ans[0] = maxSum12Index1;
                    ans[1] = maxSum12Index2;
                    ans[2] = i - k + 1;
                }
                sum1 -= nums[i - 3 * k + 1];
                sum2 -= nums[i - 2 * k + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return ans;
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        int[][] dp = new int[4][n + 1];
        int[][] index = new int[4][n + 1];
        int[] ans = new int[3];
        for (int i = 1; i <= n; ++i) {
            sum += nums[i - 1];
            if (i >= k) {
                for (int j = 1; j <= 3; ++j) {
                    if (i >= j * k) {
                        if (dp[j][i - 1] >= dp[j - 1][i - k] + sum) {
                            index[j][i] = index[j][i - 1];
                            dp[j][i] = dp[j][i - 1];
                        } else {
                            index[j][i] = i - k;
                            dp[j][i] = dp[j - 1][i - k] + sum;
                        }
                    }
                }
                if (i >= 3 * k) {
                    ans[2] = index[3][i];
                    ans[1] = index[2][ans[2]];
                    ans[0] = index[1][ans[1]];
                }
                sum -= nums[i - k];
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0689MaximumSumOf3NonOverlappingSubarrays a = new Problem0689MaximumSumOf3NonOverlappingSubarrays();
//        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};
//        a.maxSumOfThreeSubarrays(nums, 2);
//    }
}
