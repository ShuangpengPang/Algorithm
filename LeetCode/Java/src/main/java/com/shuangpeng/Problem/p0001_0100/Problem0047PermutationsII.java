package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem0047PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, new boolean[nums.length], new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> list, List<List<Integer>> ans) {
        int n = nums.length, size = list.size();
        if (n == size) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!visited[i] && (i == 0 || visited[i - 1] || nums[i] != nums[i - 1])) {
                visited[i] = true;
                list.add(nums[i]);
                dfs(nums, visited, list, ans);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}
