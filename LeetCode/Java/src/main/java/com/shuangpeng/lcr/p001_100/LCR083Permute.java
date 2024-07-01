package com.shuangpeng.lcr.p001_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR083Permute（全排列）
 * @date 2024/7/2 12:12 AM
 */
public class LCR083Permute {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, new boolean[nums.length], new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> list, List<List<Integer>> ans) {
        int n = nums.length;
        if (list.size() == n) {
            List<Integer> copy = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                copy.add(list.get(i));
            }
            ans.add(copy);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(nums[i]);
                dfs(nums, visited, list, ans);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }
}
