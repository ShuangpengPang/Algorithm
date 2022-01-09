package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.List;

public class Problem0089GrayCode {

    public List<Integer> grayCode0(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }
        int length = (int) Math.pow(2, n);
        List<Integer> answer = new ArrayList<>(length);
        answer.add(0);
        int count = 0;
        while (count < n) {
            int size = answer.size();
            int base = 1 << count;
            for (int i = size - 1; i >= 0; i--) {
                answer.add(base + answer.get(i));
            }
            count++;
        }
        return answer;
    }

    public List<Integer> grayCode1(int n) {
        List<Integer> ans = new ArrayList<>();
        dfs(0, new boolean[1 << n], new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int num, boolean[] visited, List<Integer> list, List<Integer> ans) {
        if (!ans.isEmpty()) {
            return;
        }
        list.add(num);
        int n = visited.length;
        if (list.size() == n) {
            if (check(num, 0)) {
                for (int i = 0; i < n; ++i) {
                    ans.add(list.get(i));
                }
            }
            list.remove(list.size() - 1);
            return;
        }
        visited[num] = true;
        for (int i = 1; i < n; i <<= 1) {
            int j = num + i;
            if (j < n && !visited[j] && j == (num ^ i)) {
                dfs(j, visited, list, ans);
            }
            j = num - i;
            if (j > 0 && !visited[j] && j == (num ^ i)) {
                dfs(j, visited, list, ans);
            }
        }
        list.remove(list.size() - 1);
        visited[num] = false;
    }

    private boolean check(int i, int j) {
        int x = i ^ j;
        return (x & (x - 1)) == 0;
    }

    public List<Integer> grayCode2(int n) {
        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        int m = 1 << n;
        for (int i = 1; i < m; i <<= 1) {
            int size = ans.size();
            for (int j = size - 1; j >= 0; --j) {
                ans.add(ans.get(j) | i);
            }
        }
        return ans;
    }

    public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < 1 << n; i++) {
            ret.add((i >> 1) ^ i);
        }
        return ret;
    }

//    public static void main(String[] args) {
//        Problem0089GrayCode a = new Problem0089GrayCode();
//        a.grayCode0(4);
//    }
}
