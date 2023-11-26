package com.shuangpeng.Problem.p2401_2500;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2471MinimumNumberOfOperationsToSortABinaryTreeByLevel（逐层排序二叉树所需的最少操作数目）
 * @date 2023/11/26 11:59 AM
 */
public class Problem2471MinimumNumberOfOperationsToSortABinaryTreeByLevel {

    public int minimumOperations(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        dfs(root, lists, 0);
        int ans = 0;
        for (List<Integer> list : lists) {
            List<Integer> tmp = new ArrayList<>(list);
            int size = tmp.size();
            Integer[] arr = new Integer[size], ids = new Integer[size], map = new Integer[size];
            Arrays.setAll(arr, i -> i);
            Arrays.setAll(ids, i -> i);
            Arrays.sort(ids, Comparator.comparingInt(a -> list.get(a)));
            for (int i = 0; i < size; i++) {
                map[ids[i]] = i;
            }
            for (int i = 0; i < size; i++) {
                int id = ids[i];
                while (arr[i] != id) {
                    int j = map[arr[i]];
                    int t = arr[j];
                    arr[j] = arr[i];
                    arr[i] = t;
                    ans++;
                }
            }
        }
        return ans;
    }

    private void dfs(TreeNode root, List<List<Integer>> lists, int level) {
        if (root == null) {
            return;
        }
        if (level == lists.size()) {
            lists.add(new ArrayList<>());
        }
        lists.get(level).add(root.val);
        dfs(root.left, lists, level + 1);
        dfs(root.right, lists, level + 1);
    }
}
