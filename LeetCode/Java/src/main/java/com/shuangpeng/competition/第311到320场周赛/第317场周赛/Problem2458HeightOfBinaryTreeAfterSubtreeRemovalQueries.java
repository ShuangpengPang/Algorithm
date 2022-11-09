package com.shuangpeng.competition.第311到320场周赛.第317场周赛;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2458HeightOfBinaryTreeAfterSubtreeRemovalQueries（移除子树后的二叉树高度）
 * @date 2022/11/9 5:06 PM
 */
public class Problem2458HeightOfBinaryTreeAfterSubtreeRemovalQueries {

    public int[] treeQueries(TreeNode root, int[] queries) {
        List<int[][]> maxList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int h = dfs(root, maxList, 0, map) - 1;
        int n = queries.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int val = queries[i], level = map.get(val);
            int[][] arr = maxList.get(level);
            if (val != arr[0][0]) {
                ans[i] = h;
            } else if (arr[1][0] != 0) {
                ans[i] = level + arr[1][1] - 1;
            } else {
                ans[i] = level - 1;
            }
        }
        return ans;
    }

    private int dfs(TreeNode root, List<int[][]> maxList, int level, Map<Integer, Integer> map) {
        if (level == maxList.size()) {
            maxList.add(new int[2][2]);
        }
        map.put(root.val, level);
        int ans = 0;
        if (root.left != null) {
            ans = dfs(root.left, maxList, level + 1, map);
        }
        if (root.right != null) {
            ans = Math.max(ans, dfs(root.right, maxList, level + 1, map));
        }
        ans++;
        int[][] arr = maxList.get(level);
        if (ans > arr[0][1]) {
            arr[1][0] = arr[0][0];
            arr[1][1] = arr[0][1];
            arr[0][0] = root.val;
            arr[0][1] = ans;
        } else if (ans > arr[1][1]) {
            arr[1][0] = root.val;
            arr[1][1] = ans;
        }
        return ans;
    }
}

class Problem2458HeightOfBinaryTreeAfterSubtreeRemovalQueries0 {
}