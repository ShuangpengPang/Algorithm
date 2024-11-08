package com.shuangpeng.Problem.p3201_3300;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3264FindArrayStateAfterKMultiplicationOperationsI（K次乘运算后的最终数组I）
 * @date 2024/11/8 11:18 AM
 */
public class Problem3264FindArrayStateAfterKMultiplicationOperationsI {

    public int[] getFinalState0(int[] nums, int k, int multiplier) {
        if (multiplier == 1) {
            return nums;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[a] != nums[b] ? nums[a] - nums[b] : a - b);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            pq.offer(i);
        }
        for (int i = 0; i < k; i++) {
            int idx = pq.poll();
            nums[idx] *= multiplier;
            pq.offer(idx);
        }
        return nums;
    }

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        if (multiplier == 1) {
            return nums;
        }
        int max = 0, n = nums.length;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> (int) (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
        for (int i = 0; i < n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            pq.offer(new long[]{nums[i], i});
        }
        long N = (long) 1e9 + 7;
        while (k > 0 && pq.peek()[0] <= max) {
            long[] pair = pq.poll();
            pair[0] *= multiplier;
            nums[(int) pair[1]] = (int) (pair[0] % N);
            pq.offer(pair);
            k--;
        }
        int m = k % n;
        for (int i = 0; i < m; i++) {
            long[] pair = pq.poll();
            nums[(int) pair[1]] = (int) (pair[0] * multiplier % N);
        }
        long p = fastPower(multiplier, k / n);
        for (int i = 0; i < n; i++) {
            nums[i] = (int) (nums[i] * p % N);
        }
        return nums;
    }

    private long fastPower(long num, int power) {
        long N = (long) 1e9 + 7;
        long ans = 1;
        while (power != 0) {
            if ((power & 1) == 1) {
                ans = ans * num % N;
            }
            num = num * num % N;
            power >>= 1;
        }
        return ans;
    }
}
