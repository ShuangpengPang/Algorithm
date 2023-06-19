package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

    public int maxSumDivThree(int[] nums) {
        int n = nums.length, N = Integer.MIN_VALUE;
        int[][] dp = new int[2][3];
        Arrays.fill(dp[0], N);
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            int idx = i & 1, pre = idx ^ 1;
            int num = nums[i - 1], m = num % 3;
            for (int j = 0; j < 3; j++) {
                int k = (3 + j - m) % 3;
                dp[idx][j] = Math.max(dp[pre][j], dp[pre][k] + num);
            }
        }
        return dp[n & 1][0];
    }
}
