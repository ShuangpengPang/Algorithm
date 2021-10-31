package com.shuangpeng.Problem.p0201_0300;

import com.shuangpeng.common.TreeNode;

public class Problem0222CountCompleteTreeNodes {

    public int countNodes0(TreeNode root) {
        return getCountNodes(root);
    }

    private int getCountNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);
        if (leftHeight == rightHeight) {
            return (int) (Math.pow(2, leftHeight) - 1);
        }
        return getCountNodes(root.left) + getCountNodes(root.right) + 1;
    }

    private int getLeftHeight(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return getLeftHeight(treeNode.left) + 1;
    }

    private int getRightHeight(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return getRightHeight(treeNode.right) + 1;
    }

    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public boolean exists(TreeNode root, int level, int k) {
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        while (node.left != null) {
            level++;
            node = node.left;
        }
        int low = 1 << level;
        int high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (checkExist(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    private boolean checkExist(TreeNode node, int level, int k) {
        int num = 1 << level;
        while (num > 0 && node != null) {
            if (node == null) {
                return false;
            }
            num >>= 1;
            if ((num & k) > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return true;
    }
}
