package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem0508MostFrequentSubtreeSum（出现次数最多的子树元素和）
 * @Date 2022/6/19 10:27 AM
 * @Version 1.0
 */
public class Problem0508MostFrequentSubtreeSum {

    int freq = 0;
    Map<Integer, Integer> map;

    public int[] findFrequentTreeSum(TreeNode root) {
        freq = 0;
        map = new HashMap<>();
        dfs(root);
        int count = 0;
        for (int sum : map.keySet()) {
            if (map.get(sum) == freq) {
                ++count;
            }
        }
        int[] ans = new int[count];
        int i = 0;
        for (int sum : map.keySet()) {
            if (map.get(sum) == freq) {
                ans[i++] = sum;
            }
        }
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = root.val + dfs(root.left) + dfs(root.right);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        freq = Math.max(freq, map.get(sum));
        return sum;
    }
}
