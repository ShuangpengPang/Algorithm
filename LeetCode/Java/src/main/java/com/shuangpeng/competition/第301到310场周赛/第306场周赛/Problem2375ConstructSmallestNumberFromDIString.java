package com.shuangpeng.competition.第301到310场周赛.第306场周赛;

/**
 * @Description: Problem2375ConstructSmallestNumberFromDIString（根据模式串构造最小数字）
 * @Date 2022/9/1 8:03 PM
 * @Version 1.0
 */
public class Problem2375ConstructSmallestNumberFromDIString {

    // 比赛时写法
    public String smallestNumber0(String pattern) {
        int n = pattern.length();
        for (int i = 1; i < 10; i++) {
            int[] arr = new int[n + 1];
            boolean[] visited = new boolean[10];
            arr[0] = i;
            visited[i] = true;
            String s = dfs(pattern, arr, 0, visited);
            if (s != null) {
                return s;
            }
        }
        return "";
    }

    private String dfs(String s, int[] arr, int idx, boolean[] visited) {
        if (idx == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (int num : arr) {
                sb.append(num);
            }
            return sb.toString();
        }
        char c = s.charAt(idx);
        if (c == 'I') {
            for (int i = arr[idx] + 1; i < 10; i++) {
                if (!visited[i]) {
                    arr[idx + 1] = i;
                    visited[i] = true;
                    String str = dfs(s, arr, idx + 1, visited);
                    if (str != null) {
                        visited[i] = false;
                        return str;
                    }
                    visited[i] = false;
                }
            }
        } else {
            for (int i = 1; i < arr[idx]; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    arr[idx + 1] = i;
                    String str = dfs(s, arr, idx + 1, visited);
                    if (str != null) {
                        visited[i] = false;
                        return str;
                    }
                    visited[i] = false;
                }
            }
        }
        return null;
    }

    public String smallestNumber1(String pattern) {
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

    public String smallestNumber(String pattern) {
        int n = pattern.length();
        char[] cs = new char[n + 1];
        char c = '1';
        int i = 0;
        while (i < n) {
            if (i > 0 && pattern.charAt(i) == 'I') {
                i++;
            }
            while (i < n && pattern.charAt(i) == 'I') {
                cs[i++] = c++;
            }
            int s = i;
            while (i < n && pattern.charAt(i) == 'D') {
                i++;
            }
            for (int j = i; j >= s; j--) {
                cs[j] = c++;
            }
        }
        return new String(cs);
    }
}