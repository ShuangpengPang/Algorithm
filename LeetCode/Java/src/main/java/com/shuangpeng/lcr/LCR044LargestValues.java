package com.shuangpeng.lcr;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR044LargestValues（在每个树行中找最大值）
 * @date 2024/6/21 7:38 PM
 */
public class LCR044LargestValues {

    public List<Integer> largestValues0(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int max = Integer.MIN_VALUE;
            for (int i = q.size() - 1; i >= 0; i--) {
                TreeNode node = q.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            ans.add(max);
        }
        return ans;
    }

    public List<Integer> largestValues(TreeNode root) {
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
        } else {
            list.set(level, Math.max(list.get(level), root.val));
        }
        dfs(root.left, list, level + 1);
        dfs(root.right, list, level + 1);
    }
}
