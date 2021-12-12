package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.List;

public class Problem0077Combinations {

    public List<List<Integer>> combine0(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(n, k, 1, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int n, int k, int num, List<Integer> list, List<List<Integer>> ans) {
        int size = list.size();
        if (size == k) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = num; i <= n + size + 1 - k; ++i) {
            list.add(i);
            dfs(n, k, i + 1, list, ans);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 1; i <= k; ++i) {
            temp.add(i);
        }
        temp.add(n + 1);
        int j = 0;
        while (j < k) {
            j = 0;
            ans.add(new ArrayList<>(temp.subList(0, k)));
            while (j < k && temp.get(j) + 1 == temp.get(j + 1)) {
                temp.set(j, j + 1);
                ++j;
            }
            temp.set(j, temp.get(j) + 1);
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0077Combinations a = new Problem0077Combinations();
//        a.combine(5, 3);
//    }
}
