package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem0040CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(candidates, 0, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int[] candidates, int start, int target, int sum, List<Integer> list, List<List<Integer>> ans) {
        int n = candidates.length;
        if (sum > target) {
            return;
        }
        if (target == sum) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < n; ++i) {
            if (i == start || candidates[i] != candidates[i - 1]) {
                list.add(candidates[i]);
                dfs(candidates, i + 1, target, sum + candidates[i], list, ans);
                list.remove(list.size() - 1);
            }
        }
    }
}
