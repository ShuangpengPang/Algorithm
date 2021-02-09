package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem1261FindElementsInAContaminatedBinaryTree {

    class FindElements {

        private TreeNode root;

        public FindElements(TreeNode root) {
            this.root = root;
            dfs(null, root);
        }

        private void dfs(TreeNode parent, TreeNode node) {
            if (node == null) {
                return;
            }
            if (parent == null) {
                node.val = 0;
            } else if (parent.left == node) {
                node.val = 2 * parent.val + 1;
            } else {
                node.val = 2 * parent.val + 2;
            }
            dfs(node, node.left);
            dfs(node, node.right);
        }

        public boolean find(int target) {
            if (this.root == null || target < 0) {
                return false;
            }
            target++;
            List<Integer> list = new ArrayList<>();
            while (target > 0) {
                list.add(0, target & 1);
                target >>= 1;
            }
            if (!list.isEmpty()) {
                list.remove(0);
            }
            TreeNode node = this.root;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int value = list.get(i);
                if (value == 0 && node.left != null) {
                    node = node.left;
                } else if (value == 1 && node.right != null) {
                    node = node.right;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
}
