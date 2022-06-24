package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description: Problem0515FindLargestValueInEachTreeRow（在每个树行中找最大数）
 * @Date 2022/6/24 9:56 AM
 * @Version 1.0
 */
public class Problem0515FindLargestValueInEachTreeRow {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int val = Integer.MIN_VALUE;
            for (int i = q.size() - 1; i >= 0; i--) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
                val = Math.max(val, node.val);
            }
            list.add(val);
        }
        return list;
    }
}

class Problem0515FindLargestValueInEachTreeRow0 {

    List<Integer> ans;

    public List<Integer> largestValues(TreeNode root) {
        ans = new ArrayList<>();
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (ans.size() == depth) {
            ans.add(root.val);
        } else {
            ans.set(depth, Math.max(ans.get(depth), root.val));
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}
