package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem0113PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> answer = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), answer);
        return answer;
    }

    private void dfs(TreeNode node, int remain, List<Integer> list, List<List<Integer>> answer) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (node.val == remain) {
                List<Integer> copy = new ArrayList<>(list);
                copy.add(node.val);
                answer.add(copy);
            }
            return;
        }
        list.add(node.val);
        dfs(node.left, remain - node.val, list, answer);
        dfs(node.right, remain - node.val, list, answer);
        list.remove(list.size() - 1);
    }
}
