package com.shuangpeng.lcr.p001_100;

import com.shuangpeng.common.TreeNode;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR045FindBottomLeftValue（找树左下角的值）
 * @date 2024/6/22 11:12 AM
 */
public class LCR045FindBottomLeftValue {

    public int findBottomLeftValue0(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int ans = 0;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            ans = node.val;
            if (node.right != null) {
                q.offer(node.right);
            }
            if (node.left != null) {
                q.offer(node.left);
            }
        }
        return ans;
    }

    private int maxLevel = 0, ans = 0;

    public int findBottomLeftValue1(TreeNode root) {
        ans = maxLevel = 0;
        dfs(root, 1);
        return ans;
    }

    private void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }
        if (level > maxLevel) {
            maxLevel = level;
            ans = root.val;
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    public int findBottomLeftValue(TreeNode root) {
        Deque<Pair<Integer, TreeNode>> stack = new ArrayDeque<>();
        int level = 1, maxLevel = 0, ans = 0;
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                stack.offerLast(new Pair<>(level, node));
                level++;
                node = node.left;
            }
            Pair<Integer, TreeNode> pair = stack.pollLast();
            level = pair.getKey();
            node = pair.getValue();
            if (level > maxLevel) {
                maxLevel = level;
                ans = node.val;
            }
            level++;
            node = node.right;
        }
        return ans;
    }
}
