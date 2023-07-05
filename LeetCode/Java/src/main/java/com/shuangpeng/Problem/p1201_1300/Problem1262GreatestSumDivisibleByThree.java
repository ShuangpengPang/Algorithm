package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1262GreatestSumDivisibleByThree（可被三整除的最大和）
 * @date 2023/6/15 2:00 PM
 */
public class Problem1262GreatestSumDivisibleByThree {

    public int maxSumDivThree0(int[] nums) {
        int sum = 0;
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        for (int num : nums) {
            sum += num;
            if ((num % 3) == 1) {
                list1.add(num);
            } else if ((num % 3) == 2) {
                list2.add(num);
            }
        }
        if (sum % 3 == 0) {
            return sum;
        }
        list1.sort(Comparator.comparingInt(a -> a));
        list2.sort(Comparator.comparingInt(a -> a));
        int ans = 0;
        for (int i = 0, s1 = 0; i <= list1.size() && i <= 2; i++) {
            for (int j = 0, s2 = 0; j <= list2.size() && j <= 2; j++) {
                if ((sum - s1 - s2) % 3 == 0) {
                    ans = Math.max(ans, sum - s1 - s2);
                }
                if (j < list2.size()) {
                    s2 += list2.get(j);
                }
            }
            if (i < list1.size()) {
                s1 += list1.get(i);
            }
        }
        return ans;
    }

    public int maxSumDivThree1(int[] nums) {
        int[] arr1 = new int[4], arr2 = new int[4];
        arr1[1] = arr1[2] = arr2[1] = arr2[2] = Integer.MAX_VALUE >> 1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if ((num % 3) == 1) {
                check(arr1, num);
            } else if ((num % 3) == 2) {
                check(arr2, num);
            }
        }
        arr1[2] += arr1[1];
        arr2[2] += arr2[1];
        int m = sum % 3;
        return sum - Math.min(arr1[m], arr2[3 - m]);
    }

    private void check(int[] arr, int num) {
        if (num <= arr[1]) {
            arr[2] = arr[1];
            arr[1] = num;
        } else if (num < arr[2]) {
            arr[2] = num;
        }
    }

    public int maxSumDivThree2(int[] nums) {
        int n = nums.length, N = Integer.MIN_VALUE;
        int[][] dp = new int[2][3];
        Arrays.fill(dp[0], N);
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int idx = i & 1, pre = idx ^ 1;
            int num = nums[i - 1], m = num % 3;
            for (int j = 0; j < 3; j++) {
                dp[idx][j] = Math.max(dp[pre][j], dp[pre][(j + m) % 3] + num);
            }
        }
        return dp[n & 1][0];
    }

    public int maxSumDivThree3(int[] nums) {
        int[][] dp = new int[2][3];
        dp[0][1] = dp[0][2] = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 1; i <= n; i++) {
            int idx = i & 1, p = idx ^ 1, num = nums[i - 1];
            int m = num % 3;
            for (int j = 0; j < 3; j++) {
                dp[idx][j] = Math.max(dp[p][j], dp[p][(j - m + 3) % 3] + num);
            }
        }
        return dp[n & 1][0];
    }

    public int maxSumDivThree(int[] nums) {
        PriorityQueue<Integer> q1 = new PriorityQueue<>(3, Comparator.reverseOrder());
        PriorityQueue<Integer> q2 = new PriorityQueue<>(3, Comparator.reverseOrder());
        int sum = 0;
        for (int num : nums) {
            sum += num;
            int m = num % 3;
            if (m != 0) {
                PriorityQueue<Integer> q = m == 1 ? q1 : q2;
                q.add(num);
                if (q.size() > 2) {
                    q.poll();
                }
            }
        }
        int m = sum % 3;
        if (m == 1) {
            sum -= Math.min(pollSum(q1, 1), pollSum(q2, 2));
        } else if (m == 2) {
            sum -= Math.min(pollSum(q1, 2), pollSum(q2, 1));
        }
        return Math.max(sum, 0);
    }

    private int pollSum(PriorityQueue<Integer> q, int count) {
        if (q.size() < count) {
            return Integer.MAX_VALUE;
        }
        while (q.size() > count) {
            q.poll();
        }
        int sum = 0;
        while (!q.isEmpty()) {
            sum += q.poll();
        }
        return sum;
    }
}
