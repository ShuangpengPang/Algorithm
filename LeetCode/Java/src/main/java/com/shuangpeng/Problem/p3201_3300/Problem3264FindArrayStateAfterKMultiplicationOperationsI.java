package com.shuangpeng.Problem.p3201_3300;

import java.util.Arrays;
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

class Problem3264FindArrayStateAfterKMultiplicationOperationsI0 {

    private static final long N = (long) 1e9 + 7;

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        if (multiplier == 1) {
            return nums;
        }
        int mx = 0;
        for (int num : nums) {
            mx = Math.max(mx, num);
        }
        int bitLength = Integer.SIZE - Integer.numberOfLeadingZeros(mx);
        long[] powerM = new long[bitLength];
        int[] exp = new int[bitLength];
        int index = 0, e = 0;
        for (long power = 1, power2 = 1; power2 <= mx; power2 <<= 1, index++) {
            if (power < power2) {
                power *= multiplier;
                e++;
            }
            powerM[index] = power;
            exp[index] = e;
        }
        int n = nums.length;
        long[] arr = new long[n];
        int tmpK = k;
        for (int i = 0; i < n && tmpK >= 0; i++) {
            int j = bitLength - (Integer.SIZE - Integer.numberOfLeadingZeros(nums[i]));
            long p = powerM[j];
            int c = exp[j];
            if (nums[i] * p < mx) {
                p *= multiplier;
                c++;
            }
            arr[i] = nums[i] * p;
            tmpK -= c;
        }
        if (tmpK < 0) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> arr[a] != arr[b] ? Long.compare(arr[a], arr[b]) : a - b);
            for (int i = 0; i < n; i++) {
                arr[i] = nums[i];
                pq.offer(i);
            }
            for (int i = 0; i < k; i++) {
                int idx = pq.poll();
                arr[idx] *= multiplier;
                pq.offer(idx);
                nums[idx] = (int) (arr[idx] % N);
            }
            return nums;
        }
        k = tmpK;
        int m = k % n;
        long p = fastPower(multiplier, k / n);
        Integer[] indices = new Integer[n];
        Arrays.setAll(indices, i -> i);
        Arrays.sort(indices, (a, b) -> arr[a] != arr[b] ? Long.compare(arr[a], arr[b]) : a - b);
        for (int i = 0; i < n; i++) {
            int idx = indices[i];
            nums[idx] = (int) (arr[idx] % N * p % N * (i < m ? multiplier : 1) % N);
        }
        return nums;
    }

    private long fastPower(long num, int p) {
        long ans = 1;
        while (p > 0) {
            if ((p & 1) == 1) {
                ans = ans * num % N;
            }
            p >>= 1;
            num = num * num %  N;
        }
        return ans;
    }
}
