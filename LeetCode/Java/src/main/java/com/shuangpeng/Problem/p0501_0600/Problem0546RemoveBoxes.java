package com.shuangpeng.Problem.p0501_0600;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem0546RemoveBoxes {

    public int removeBoxes0(int[] boxes) {
        int n = boxes.length;
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<int[]> list = map.getOrDefault(boxes[i], new ArrayList<>());
            if (i > 0 && boxes[i] == boxes[i - 1]) {
                list.get(list.size() - 1)[1] = i;
            } else {
                list.add(new int[]{i, i});
            }
            map.put(boxes[i], list);
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                List<int[]> intervals = map.get(boxes[j]);
                int index = binarySearch(intervals, j);
                dp[i][j] = dp[i][j - 1] + 1;
                for (int k = index; k >= 0 && intervals.get(k)[1] >= i; k--) {
                    int[] interval = intervals.get(k);
                    int s = Math.max(i, interval[0]);
                    int e = Math.min(j - 1, interval[1]);
                    int t = s - 1 >= i ? dp[i][s - 1] : 0;
                    dp[i][j] = Math.max(dp[i][j], t + dp[e + 1][j - 1] + (e - s + 2) * (e - s + 2));
                }
            }
        }
        return dp[0][n - 1];
    }

    private int binarySearch(List<int[]> intervals, int i) {
        int left = 0, right = intervals.size() - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            int[] interval = intervals.get(mid);
            if (interval[1] < i) {
                left = mid + 1;
            } else if (i < interval[0]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n - i; k++) {
                dp[i][i][k] = (k + 1) * (k + 1);
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                for (int k = 0; k < n - j; k++) {
                    dp[i][j][k] = dp[i][j - 1][0] + (k + 1) * (k + 1);
                    for (int m = j - 1; m >= i; m--) {
                        if (boxes[j] == boxes[m]) {
                            dp[i][j][k] = Math.max(dp[i][j][k], dp[i][m][k + 1] + dp[m + 1][j - 1][0]);
                        }
                    }
                }
            }
        }
        return dp[0][n - 1][0];
    }

//    [1,3,2,2,2,3,4,3,1]

//    public static void main(String[] args) {
//        Problem0546RemoveBoxes a = new Problem0546RemoveBoxes();
//        a.removeBoxes(new int[]{1, 3, 2, 2, 2, 3, 4, 3, 1});
//    }
}
