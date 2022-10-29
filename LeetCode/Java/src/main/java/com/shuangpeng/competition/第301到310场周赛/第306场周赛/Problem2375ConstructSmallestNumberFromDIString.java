package com.shuangpeng.competition.第301到310场周赛.第306场周赛;

/**
 * @Description: Problem2375ConstructSmallestNumberFromDIString（根据模式串构造最小数字）
 * @Date 2022/9/1 8:03 PM
 * @Version 1.0
 */
public class Problem2375ConstructSmallestNumberFromDIString {

    public String smallestNumber(String pattern) {
        int n = pattern.length() + 1;
        int[] arr = new int[n];
        boolean[] visited = new boolean[10];
        for (int i = 1; i < 10; i++) {
            arr[0] = i;
            visited[i] = true;
            if (dfs(pattern, 0, arr, visited)) {
                break;
            }
            visited[i] = false;
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    private boolean dfs(String pattern, int idx, int[] arr, boolean[] visited) {
        if (idx == pattern.length()) {
            return true;
        }
        int start = 1, end = 10;
        if (pattern.charAt(idx) == 'I') {
            start = arr[idx] + 1;
        } else {
            end = arr[idx];
        }
        for (int i = start; i < end; i++) {
            if (!visited[i]) {
                visited[i] = true;
                arr[idx + 1] = i;
                if (dfs(pattern, idx + 1, arr, visited)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}