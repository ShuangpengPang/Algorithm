package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * @description:（环形子数组的最大和）
 * @date 2023/7/20 2:43 PM
 **/
public class Problem0918MaximumSumCircularSubarray {

    public int maxSubarraySumCircular0(int[] nums) {
        int n = nums.length;
        int maxValue = Integer.MIN_VALUE;
        int num = 0;
        for (int i = 0; i < n; ++i) {
            num = nums[i] + Math.max(num, 0);
            maxValue = Math.max(maxValue, num);
        }
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        int sum = nums[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            sum += nums[i];
            right[i] = Math.max(right[i + 1], sum);
        }
        sum = 0;
        for (int i = 0; i < n - 1; ++i) {
            sum += nums[i];
            maxValue = Math.max(maxValue, sum + right[i + 1]);
        }
        return maxValue;
    }

    public int maxSubarraySumCircular1(int[] nums) {
        int n = nums.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < (n << 1); ++i) {
            int j = i % n;
            sum += nums[j];
            while (!queue.isEmpty() && queue.peek()[0] < i - n) {
                queue.poll();
            }
            if (!queue.isEmpty()) {
                ans = Math.max(ans, sum - queue.peek()[1]);
            }
            queue.offer(new int[]{i, sum});
        }
        return ans;
    }

    public int maxSubarraySumCircular2(int[] nums) {
        int n = nums.length;
        int N = n << 1;
        int[] preSum = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            preSum[i] = preSum[i - 1] + nums[(i - 1) % n];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= N; ++i) {
            if (deque.peekFirst() < i - n) {
                deque.pollFirst();
            }
            ans = Math.max(ans, preSum[i] - preSum[deque.peekFirst()]);
            while (!deque.isEmpty() && preSum[i] <= preSum[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offer(i);
        }
        return ans;
    }

    public int maxSubarraySumCircular3(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int ans = Integer.MIN_VALUE;
        int cur = 0;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            cur = nums[i] + Math.max(cur, 0);
            ans = Math.max(ans, cur);
            sum += nums[i];
        }
        int curA = 0, maxA = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; ++i) {
            curA = -nums[i] + Math.max(curA, 0);
            maxA = Math.max(maxA, curA);
        }
        ans = Math.max(ans, sum + maxA);
        curA = 0;
        maxA = Integer.MIN_VALUE;
        for (int i = 1; i < n; ++i) {
            curA = -nums[i] + Math.max(curA, 0);
            maxA = Math.max(maxA, curA);
        }
        return Math.max(ans, sum + maxA);
    }

    public int maxSubarraySumCircular4(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int ans = kadane(nums, 0, n - 1, true);
        ans = Math.max(ans, sum - kadane(nums, 0, n - 2, false));
        return Math.max(ans, sum - kadane(nums, 1, n - 1, false));
    }

    private int kadane(int[] nums, int s, int e, boolean getMax) {
        if (getMax) {
            int cur = 0;
            int ans = Integer.MIN_VALUE;
            for (int i = s; i <= e; ++i) {
                cur = nums[i] + Math.max(cur, 0);
                ans = Math.max(ans, cur);
            }
            return ans;
        } else {
            int cur = 0;
            int ans = Integer.MAX_VALUE;
            for (int i = s; i <= e; ++i) {
                cur = nums[i] + Math.min(cur, 0);
                ans = Math.min(ans, cur);
            }
            return ans;
        }
    }

    public int maxSubarraySumCircular5(int[] nums) {
        int n = nums.length, M = Integer.MIN_VALUE >> 1;
        int[] suffix = new int[n + 1];
        suffix[n] = M;
        for (int i = n - 1, sum = 0; i >= 0; i--) {
            sum += nums[i];
            suffix[i] = Math.max(suffix[i + 1], sum);
        }
        int ans = suffix[0], s1 = 0, s2 = M, maxLeft = M;
        for (int i = 0; i < n; i++) {
            s1 += nums[i];
            maxLeft = Math.max(maxLeft, s1);
            s2 = Math.max(s2 + nums[i], nums[i]);
            ans = Math.max(ans, Math.max(maxLeft + suffix[i + 1], s2));
        }
        return ans;
    }

    public int maxSubarraySumCircular6(int[] nums) {
        int n = nums.length, N = Integer.MAX_VALUE >> 1;
        int sum = 0, ans = -N, minSum = N;
        for (int i = 0, s1 = 0, s2 = 0; i < n; i++) {
            sum += nums[i];
            s1 = Math.max(s1 + nums[i], nums[i]);
            ans = Math.max(ans, s1);
            s2 = Math.min(s2 + nums[i], nums[i]);
            minSum = Math.min(minSum, s2);
        }
        return ans < 0 ? ans : Math.max(ans, sum - minSum);
    }

    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length, N = (n << 1), ans = Integer.MIN_VALUE;
        Deque<int[]> q = new ArrayDeque<>(N);
        for (int i = 0, s = 0; i < N; i++) {
            int num = i < n ? nums[i] : nums[i - n];
            s += num;
            while (!q.isEmpty() && q.peekFirst()[0] < i - n) {
                q.pollFirst();
            }
            ans = Math.max(ans, q.isEmpty() ? s : s - q.peekFirst()[1]);
            while (!q.isEmpty() && q.peekLast()[1] >= s) {
                q.pollLast();
            }
            q.offerLast(new int[]{i, s});
        }
        return ans;
    }
}
