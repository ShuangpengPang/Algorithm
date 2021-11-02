package com.shuangpeng.Problem.p0501_0600;

import java.util.ArrayList;
import java.util.List;

public class Problem0526BeautifulArrangement {

    public int countArrangement0(int n) {
        int[] answer = new int[]{0};
        dfs(n, new boolean[n + 1], answer);
        return answer[0];
    }

    private void dfs(int i, boolean[] visited, int[] answer) {
        if (i == 1) {
            answer[0]++;
            return;
        }
        int n = visited.length - 1;
        for (int j = n; j >= 1; j--) {
            if (!visited[j] && (i % j == 0 || j % i == 0)) {
                visited[j] = true;
                dfs(i - 1, visited, answer);
                visited[j] = false;
            }
        }
    }

    public int countArrangement1(int n) {
        int[] p = {0};
        dfs(new ArrayList<>(), new boolean[n], p);
        return p[0];
    }

    private void dfs(List<Integer> list, boolean[] visited, int[] p) {
        int n = visited.length;
        int i = list.size();
        if (i == n) {
            p[0]++;
            return;
        }
        for (int j = 0; j < n; ++j) {
            if (!visited[j] && (((i + 1) % (j + 1) == 0) || ((j + 1) % (i + 1) == 0))) {
                visited[j] = true;
                list.add(j + 1);
                dfs(list, visited, p);
                list.remove(list.size() - 1);
                visited[j] = false;
            }
        }
    }

    public int countArrangement(int n) {
        final int M = 1 << n;
        int[] dp = new int[M];
        dp[0] = 1;
        for (int status = 1; status < M; ++status) {
            int count = Integer.bitCount(status);
            for (int i = 0; i < n; ++i) {
                if ((status & (1 << i)) != 0 && (((i + 1) % count == 0) || ((count % (i + 1)) == 0))) {
                    dp[status] += dp[status ^ (1 << i)];
                }
            }
        }
        return dp[M - 1];
    }

//    public static void main(String[] args) {
//        Problem0526BeautifulArrangement a = new Problem0526BeautifulArrangement();
//        a.countArrangement1(6);
//    }
}
