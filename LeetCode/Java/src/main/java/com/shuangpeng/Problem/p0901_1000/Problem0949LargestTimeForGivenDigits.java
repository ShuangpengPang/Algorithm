package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0949LargestTimeForGivenDigits（给定数字能组成的最大时间）
 * @date 2023/3/15 11:45 PM
 */
public class Problem0949LargestTimeForGivenDigits {

    public String largestTimeFromDigits(int[] arr) {
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
            if (10 * result[0] + result[1] > 23 || result[2] > 5) {
                return -1;
            }
            return result[0] * 1000 + result[1] * 100 + result[2] * 10 + result[3];
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[index] = arr[i];
                ans = Math.max(ans, dfs(arr, result, index + 1, visited));
                visited[i] = false;
            }
        }
        return ans;
    }
}
