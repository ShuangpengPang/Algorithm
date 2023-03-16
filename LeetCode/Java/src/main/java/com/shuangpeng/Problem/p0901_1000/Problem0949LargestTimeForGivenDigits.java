package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0949LargestTimeForGivenDigits（给定数字能组成的最大时间）
 * @date 2023/3/15 11:45 PM
 */
public class Problem0949LargestTimeForGivenDigits {

    public String largestTimeFromDigits0(int[] arr) {
        int n = arr.length;
        int value = dfs(arr, new int[n], 0, new boolean[n]);
        if (value == -1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(value / 1000).append(value / 100 % 10).append(':').append(value % 100 / 10).append(value % 10);
        return sb.toString();
    }

    private int dfs(int[] arr, int[] result, int index, boolean[] visited) {
        int n = arr.length;
        if (index == n) {
            if (result[2] > 5) {
                return -1;
            }
            return result[0] * 1000 + result[1] * 100 + result[2] * 10 + result[3];
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (index == 0 && arr[i] > 2 || index == 1 && result[0] * 10 + arr[i] > 23) {
                    continue;
                }
                visited[i] = true;
                result[index] = arr[i];
                ans = Math.max(ans, dfs(arr, result, index + 1, visited));
                visited[i] = false;
            }
        }
        return ans;
    }

    public String largestTimeFromDigits(int[] arr) {
        int n = arr.length, ans = -1;
        for (int i = 0; i < n; i++) {
            if (arr[i] > 2) {
                continue;
            }
            int v = arr[i];
            for (int j = 0; j < n; j++) {
                if (j == i || v * 10 + arr[j] > 23) {
                    continue;
                }
                for (int x = 0; x < n; x++) {
                    if (x == i || x == j || arr[x] > 5) {
                        continue;
                    }
                    for (int y = 0; y < n; y++) {
                        if (y != i && y != j && y != x) {
                            ans = Math.max(ans, v * 1000 + arr[j] * 100 + arr[x] * 10 + arr[y]);
                        }
                    }
                }
            }
        }
        if (ans == -1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ans / 1000).append(ans / 100 % 10).append(':').append(ans / 10 % 10).append(ans % 10);
        return sb.toString();
    }
}
