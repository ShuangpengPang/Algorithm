package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem145BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        dfs(root, answer);
        return answer;
    }

    private void dfs(TreeNode node, List<Integer> answer) {
        if (node == null) {
            return;
        }
        dfs(node.left, answer);
        dfs(node.right, answer);
        answer.add(node.val);
    }
}
