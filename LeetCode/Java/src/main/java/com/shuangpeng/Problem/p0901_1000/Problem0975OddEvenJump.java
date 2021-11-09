package com.shuangpeng.Problem.p0901_1000;

import java.util.*;

public class Problem0975OddEvenJump {

    public int oddEvenJumps0(int[] arr) {
        int n = arr.length;
        int[] small = new int[n];
        int[] large = new int[n];
        TreeMap<Integer, Integer> map = new TreeMap<>(Comparator.comparingInt(k -> k));
        map.put(Integer.MIN_VALUE, n);
        map.put(Integer.MAX_VALUE, n);
        for (int i = n - 1; i >= 0; --i) {
            small[i] = map.floorEntry(arr[i]).getValue();
            large[i] = map.ceilingEntry(arr[i]).getValue();
            map.put(arr[i], i);
        }
        boolean[][] dp = new boolean[n][2];
        dp[n - 1][0] = dp[n - 1][1] = true;
        int ans = 1;
        for (int i = n - 2; i >= 0; --i) {
            dp[i][0] = small[i] < n && dp[small[i]][1];
            dp[i][1] = large[i] < n && dp[large[i]][0];
            if (dp[i][1]) {
                ++ans;
            }
        }
        return ans;
    }

    public int oddEvenJumps(int[] arr) {
        int n = arr.length;
        Integer[] index = new Integer[n];
        for (int i = 0; i < n; ++i) {
            index[i] = i;
        }
        Arrays.sort(index, (a, b) -> {
            if (arr[a] != arr[b]) {
                return arr[a] - arr[b];
            }
            return a - b;
        });
        Deque<Integer> stack = new ArrayDeque<>();
        int[] large = new int[n];
        calculate(large, index, stack);
        Arrays.sort(index, (a, b) -> {
            if (arr[a] != arr[b]) {
                return arr[b] - arr[a];
            }
            return a - b;
        });
        int[] small = new int[n];
        calculate(small, index, stack);
        boolean[][] dp = new boolean[n][2];
        dp[n - 1][0] = dp[n - 1][1] = true;
        int ans = 1;
        for (int i = n - 2; i >= 0; --i) {
            dp[i][0] = small[i] < n && dp[small[i]][1];
            dp[i][1] = large[i] < n && dp[large[i]][0];
            if (dp[i][1]) {
                ++ans;
            }
        }
        return ans;
    }

    private void calculate(int[] arr, Integer[] index, Deque<Integer> stack) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            int idx = index[i];
            while (!stack.isEmpty() && stack.peekLast() < idx) {
                arr[stack.pollLast()] = idx;
            }
            stack.offerLast(idx);
        }
        while (!stack.isEmpty()) {
            arr[stack.pollLast()] = n;
        }
    }

//    public static void main(String[] args) {
//        Problem0975OddEvenJump a = new Problem0975OddEvenJump();
//        a.oddEvenJumps(new int[]{10, 13, 12, 14, 15});
////        a.oddEvenJumps(new int[]{1, 2, 1, 2, 1});
//    }
}
