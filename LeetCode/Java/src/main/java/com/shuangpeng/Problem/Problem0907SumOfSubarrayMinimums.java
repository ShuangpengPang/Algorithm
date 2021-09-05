package com.shuangpeng.Problem;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

public class Problem0907SumOfSubarrayMinimums {

    public int sumSubarrayMins0(int[] arr) {
        int n = arr.length;
        Deque<Integer> stack = new LinkedList<>();
        stack.offerLast(-1);
        stack.offerLast(0);
        final int M = (int) 1e9 + 7;
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            while (stack.peekLast() != -1 && arr[stack.peekLast()] > arr[i]) {
                int j = stack.pollLast();
                int k = stack.peekLast();
                ans += (long) (j - k) * (i - j) % M * arr[j] % M;
                if (ans >= M) {
                    ans -= M;
                }
            }
            stack.offerLast(i);
        }
        while (stack.peekLast() != -1) {
            int i = stack.pollLast();
            int j = stack.peekLast();
            ans += (long) (i - j) * (n - i) % M * arr[i] % M;
            if (ans >= M) {
                ans -= M;
            }
        }
        return ans;
    }

    public int sumSubarrayMins1(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        left[0] = -1;
        for (int i = 1; i < n; ++i) {
            if (arr[i] > arr[i - 1]) {
                left[i] = i - 1;
            } else if (arr[i] < arr[i - 1]) {
                int j = i - 1;
                while (j >= 0 && arr[i] <= arr[j]) {
                    j = left[j];
                }
                left[i] = j;
            } else {
                left[i] = left[i - 1];
            }
        }
        int[] right = new int[n];
        right[n - 1] = n;
        for (int i = n - 2; i >= 0; --i) {
            if (arr[i] >= arr[i + 1]) {
                right[i] = i + 1;
            } else if (arr[i] < arr[i + 1]) {
                int j = i + 1;
                while (j < n && arr[i] < arr[j]) {
                    j = right[j];
                }
                right[i] = j;
            }
        }
        int ans = 0;
        final int M = (int) 1e9 + 7;
        for (int i = 0; i < n; ++i) {
            ans += (long) (i - left[i]) * (right[i] - i) % M * arr[i] % M;
            if (ans >= M) {
                ans -= M;
            }
        }
        return ans;
    }

    public int sumSubarrayMins2(int[] A) {
        int MOD = 1_000_000_007;

        Stack<RepInteger> stack = new Stack();
        int ans = 0, dot = 0;
        for (int j = 0; j < A.length; ++j) {
            // Add all answers for subarrays [i, j], i <= j
            int count = 1;
            while (!stack.isEmpty() && stack.peek().val >= A[j]) {
                RepInteger node = stack.pop();
                count += node.count;
                dot -= node.val * node.count;
            }
            stack.push(new RepInteger(A[j], count));
            dot += A[j] * count;
            ans += dot;
            ans %= MOD;
        }

        return ans;
    }

    class RepInteger {
        int val, count;
        RepInteger(int v, int c) {
            val = v;
            count = c;
        }
    }

    public int sumSubarrayMins(int[] A) {
        int n = A.length;
        final int M = (int) 1e9 + 7;
        Deque<RepInteger> stack = new ArrayDeque<>();
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int count = 1;
            while (!stack.isEmpty() && A[i] <= stack.peekLast().val) {
                RepInteger repInteger = stack.pollLast();
                count += repInteger.count;
                sum -= repInteger.count * repInteger.val;
            }
            stack.offerLast(new RepInteger(A[i], count));
            sum += count * A[i];
            ans += sum;
            if (ans >= M) {
                ans -= M;
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0907SumOfSubarrayMinimums a = new Problem0907SumOfSubarrayMinimums();
//        a.sumSubarrayMins(new int[]{3, 1, 2, 4});
//    }
}
