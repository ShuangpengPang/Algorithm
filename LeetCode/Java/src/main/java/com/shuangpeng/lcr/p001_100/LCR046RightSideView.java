package com.shuangpeng.lcr.p001_100;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR046RightSideView（二叉树的右视图）
 * @date 2024/6/22 10:27 AM
 */
public class LCR046RightSideView {

    public List<Integer> rightSideView0(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int val = 0;
            for (int i = q.size() - 1; i >= 0; i--) {
                TreeNode node = q.poll();
                val = node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ans.add(val);
        }
        return ans;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans, 0);
        return ans;
    }

    private void dfs(TreeNode root, List<Integer> list, int level) {
        if (root == null) {
            return;
        }
        if (list.size() == level) {
            list.add(root.val);
        }
        dfs(root.right, list, level + 1);
        dfs(root.left, list, level + 1);
    }
}
