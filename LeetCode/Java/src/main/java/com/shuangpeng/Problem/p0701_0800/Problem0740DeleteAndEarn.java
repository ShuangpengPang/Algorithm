package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

public class Problem0740DeleteAndEarn {

    public int deleteAndEarn0(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + nums[i]);
        }
        int n = map.size();
        List<int[]> list = new ArrayList<>(n);
        for (int key : map.keySet()) {
            list.add(new int[]{key, map.get(key)});
        }
        Collections.sort(list, Comparator.comparingInt(a -> a[0]));
        // dp[i][0]表示不选第i个数，dp[i][1]表示选第i个数，dp[i][2]表示第i个数必选
        int[][] dp = new int[n + 1][3];
        dp[1][2] = list.get(0)[1];
        for (int i = 2; i <= n; i++) {
            int num = list.get(i - 1)[0];
            int sum = list.get(i - 1)[1];
            int preNum = list.get(i - 2)[0];
            if (preNum + 1 != num) {
                dp[i][2] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]) + sum;
            } else if (dp[i - 1][2] > 0) {
                dp[i][0] = dp[i - 1][2];
                dp[i][1] = Math.max(Math.max(dp[i - 2][0], dp[i - 2][1]), dp[i - 2][2]) + sum;
            } else {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = Math.max(dp[i - 1][0], Math.max(Math.max(dp[i - 2][0], dp[i - 2][1]), dp[i - 2][2])) + sum;
            }
        }
        return Math.max(Math.max(dp[n][0], dp[n][1]), dp[n][2]);
    }

    public int deleteAndEarn1(int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }
        int[] sum = new int[maxValue + 1];
        for (int num : nums) {
            sum[num] += num;
        }
        int first = 0, second = sum[0];
        for (int i = 1; i <= maxValue; i++) {
            int temp = Math.max(first, second);
            second = first + sum[i];
            first = temp;
        }
        return Math.max(first, second);
    }

    public int deleteAndEarn2(int[] nums) {
        int maxValue = Integer.MIN_VALUE;
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }
        int[] sum = new int[maxValue + 1];
        for (int num : nums) {
            sum[num] += num;
        }
        int first = 0, second = sum[1];
        for (int i = 2; i <= maxValue; i++) {
            int temp = second;
            second = Math.max(second, first + sum[i]);
            first = temp;
        }
        return second;
    }

    public int deleteAndEarn(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<Integer> sums = new ArrayList<>();
        sums.add(nums[0]);
        int maxPoints = 0;
        for (int i = 1; i < n; i++) {
            int size = sums.size();
            if (nums[i] == nums[i - 1]) {
                sums.set(size - 1, sums.get(size - 1) + nums[i]);
            } else if (nums[i] == nums[i - 1] + 1) {
                sums.add(nums[i]);
            } else {
                maxPoints += getPoints(sums);
                sums.clear();
                sums.add(nums[i]);
            }
        }
        return maxPoints + getPoints(sums);
    }

    private int getPoints(List<Integer> sums) {
        int size = sums.size();
        if (size == 1) {
            return sums.get(0);
        }
        int first = sums.get(0), second = Math.max(sums.get(0), sums.get(1));
        for (int i = 2; i < size; i++) {
            int temp = second;
            second = Math.max(second, first + sums.get(i));
            first = temp;
        }
        return second;
    }
}
