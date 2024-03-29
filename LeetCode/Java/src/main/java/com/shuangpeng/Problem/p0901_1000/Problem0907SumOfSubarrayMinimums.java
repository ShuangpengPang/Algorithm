package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Description: 子数组的最小值之和
 * @Date 2022/10/28 11:03 AM
 **/
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

    public int sumSubarrayMins3(int[] A) {
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

    public int sumSubarrayMins4(int[] arr) {
        int n = arr.length, M = (int) 1e9 + 7;
        Deque<Integer> stack = new ArrayDeque<>();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                ans += (long) arr[j] * (j - (stack.isEmpty() ? -1 : stack.peek())) * (i - j);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            ans += (long) arr[j] * (j - (stack.isEmpty() ? -1 : stack.peek())) * (n - j);
        }
        return (int) (ans % M);
    }

    public int sumSubarrayMins5(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        long ans = 0, M = (long) 1e9 + 7;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            dp[i] = stack.isEmpty() ? arr[i] * (i + 1) : arr[i] * (i - stack.peek()) + dp[stack.peek()];
            ans += dp[i];
            stack.push(i);
        }
        return (int) (ans % M);
    }

    public int sumSubarrayMins(int[] arr) {
        Deque<int[]> stack = new ArrayDeque<>();
        long ans = 0, M = (long) 1e9 + 7;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()[0]] >= arr[i]) {
                stack.pop();
            }
            int sum = stack.isEmpty() ? arr[i] * (i + 1) : stack.peek()[1] + arr[i] * (i - stack.peek()[0]);
            ans += sum;
            stack.push(new int[]{i, sum});
        }
        return (int) (ans % M);
    }

//    public static void main(String[] args) {
//        Problem0907SumOfSubarrayMinimums a = new Problem0907SumOfSubarrayMinimums();
//        a.sumSubarrayMins(new int[]{3, 1, 2, 4});
//    }
}
