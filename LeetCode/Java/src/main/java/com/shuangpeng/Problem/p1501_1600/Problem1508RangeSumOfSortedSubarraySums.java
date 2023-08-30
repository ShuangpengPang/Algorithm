package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1508RangeSumOfSortedSubarraySums（子数组和排序后的区间和）
 * @date 2023/8/30 3:03 PM
 */
public class Problem1508RangeSumOfSortedSubarraySums {

    public int rangeSum(int[] nums, int n, int left, int right) {
        int sum = 0, N = (int) 1e9 + 7;
        for (int num : nums) {
            sum += num;
        }
        int[] cnt = new int[sum + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i, s = 0; j < n; j++) {
                s += nums[j];
                cnt[s]++;
            }
        }
        return (getSum(cnt, right) - getSum(cnt, left - 1) + N) % N;
    }

    private int getSum(int[] cnt, int count) {
        long sum = 0, N = (int) 1e9 + 7;
        for (int i = 0; i < cnt.length && count > 0; i++) {
            int c = Math.min(count, cnt[i]);
            sum = sum + (long) c * i;
            count -= c;
        }
        return (int) (sum % N);
    }
}
