package com.shuangpeng.Problem;

public class Problem0526BeautifulArrangement {

    public int countArrangement(int n) {
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
}
