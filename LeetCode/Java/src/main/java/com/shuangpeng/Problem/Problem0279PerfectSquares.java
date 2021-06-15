package com.shuangpeng.Problem;

import java.util.*;

public class Problem0279PerfectSquares {

    // 动态规划
    public int numSquares0(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int j = 2;
        for (int i = 2; i <= n; i++) {
            if (i == j * j) {
                dp[i] = 1;
                j++;
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int k = 1; k <= i / 2; k++) {
                min = Math.min(min, dp[k] + dp[i - k]);
            }
            dp[i] = min;
        }
        return dp[n];
    }

    // 动态规划（优化）
    public int numSquares1(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int maxSquare;
        for (maxSquare = 1; maxSquare * maxSquare <= n; maxSquare++) {
            dp[maxSquare * maxSquare] = 1;
        }
        dp[0] = 0;
        if (dp[n] == 1) {
            return 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < maxSquare; j++) {
                if (i < j * j) {
                    break;
                }
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }

//    public static void main(String[] args) {
//        Problem0279PerfectSquares a = new Problem0279PerfectSquares();
//        a.numSquares(12);
//    }

    public int numSquares2(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        for (int c = 1; c < n; c++) {
            if (isBreak(n, c)) {
                return c;
            }
        }
        return n;
    }

    public boolean isBreak(int n, int count) {
        if (count < 0) {
            return false;
        }
        if (n == 0 && count >= 0) {
            return true;
        }
        if (n == 1 && count >= 1) {
            return true;
        }
        int maxSquare = 1;
        while (maxSquare * maxSquare <= n) {
            maxSquare++;
        }
        if (n == (maxSquare - 1) * (maxSquare - 1) && count >= 1) {
            return true;
        }
        for (int i = maxSquare - 1; i >= 1; i--) {
            if (isBreak(n - i * i, count - 1)) {
                return true;
            }
        }
        return false;
    }

    // 广度优先遍历
    public int numSquares3(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        Set<Integer> squareSet = new HashSet<>();
        for (int i = 1; i * i <= n; i++) {
            squareSet.add(i * i);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n);
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int data = queue.poll();
                if (squareSet.contains(data)) {
                    return count;
                }
                for (int square : squareSet) {
                    if (data - square < 0) {
                        continue;
                    }
                    queue.offer(data - square);
                }
            }
        }
        return n;
    }

    public int numSquares4(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int m = n;
        while (m % 4 == 0) {
            m /= 4;
        }
        if (m % 8 == 7) {
            return 4;
        }

        Set<Integer> squareSet = new HashSet<>();
        for (int i = 0; i * i <= n; i++) {
            squareSet.add(i * i);
        }
        if (squareSet.contains(n)) {
            return 1;
        }
        for (int square : squareSet) {
            if (squareSet.contains(n - square)) {
                return 2;
            }
        }
        return 3;
    }

    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }
}
