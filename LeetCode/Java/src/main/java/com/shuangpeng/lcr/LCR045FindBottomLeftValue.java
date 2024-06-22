package com.shuangpeng.lcr;

import com.shuangpeng.common.TreeNode;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR045FindBottomLeftValue（找树左下角的值）
 * @date 2024/6/22 11:12 AM
 */
public class LCR045FindBottomLeftValue {

    private int maxLevel = 0, ans = 0;

    public int findBottomLeftValue0(TreeNode root) {
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
