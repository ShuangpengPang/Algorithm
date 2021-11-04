package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem0637AverageOfLevelsInBinaryTree {

    public List<Double> averageOfLevels(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        dfs(root, 0, lists);
        int size = lists.size();
        List<Double> answer = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            List<Integer> list = lists.get(i);
            long sum = 0;
            for (int data : list) {
                sum += data;
            }
            answer.add(list.size() == 0 ? 0 : (double) sum / list.size());
        }
        return answer;
    }

    private void dfs(TreeNode node, int depth, List<List<Integer>> lists) {
        if (node == null) {
            return;
        }
        if (depth >= lists.size()) {
            lists.add(new ArrayList<>());
        }
        lists.get(depth).add(node.val);
        dfs(node.left, depth + 1, lists);
        dfs(node.right, depth + 1, lists);
    }
}
