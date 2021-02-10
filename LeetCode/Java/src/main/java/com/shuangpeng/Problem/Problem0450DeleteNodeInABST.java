package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem0450DeleteNodeInABST {

    public TreeNode deleteNode0(TreeNode root, int key) {
        TreeNode parent = null;
        TreeNode node = root;
        while (node != null) {
            if (node.val > key) {
                parent = node;
                node = node.left;
            } else if (node.val < key) {
                parent = node;
                node = node.right;
            } else {
                break;
            }
        }
        if (node == null) {
            return root;
        }
        if (node.left == null) {
            if (parent == null) {
                return node.right;
            }
            if (parent.left == node) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        } else {
            if (node.right == null) {
                if (parent == null) {
                    return node.left;
                }
                if (parent.left == node) {
                    parent.left = node.left;
                } else {
                    parent.right = node.left;
                }
                return root;
            }
            TreeNode[] nodes = getSuccessor(node);
            if (node == nodes[0]) {
                nodes[1].left = node.left;
                if (parent == null) {
                    return nodes[1];
                }
                if (parent.left == node) {
                    parent.left = nodes[1];
                } else {
                    parent.right = nodes[1];
                }
            } else {
                nodes[0].left = nodes[1].right;
                nodes[1].left = node.left;
                nodes[1].right = node.right;
                if (parent == null) {
                    return nodes[1];
                }
                if (parent.left == node) {
                    parent.left = nodes[1];
                } else {
                    parent.right = nodes[1];
                }
            }
        }
        return root;
    }

    private TreeNode[] getSuccessor(TreeNode node) {
        TreeNode parent = node;
        TreeNode treeNode = node.right;
        while (treeNode.left != null) {
            parent = treeNode;
            treeNode = treeNode.left;
        }
        return new TreeNode[]{parent, treeNode};
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode node = root;
        while (node != null) {
            if (node.val < key) {
                node = node.right;
            } else if (node.val > key) {
                node = node.left;
            } else {
                break;
            }
        }
        if (node == null) {
            return root;
        }
        if (node == root) {
            return deleteNodeRecurse(node);
        }
        deleteNodeRecurse(node);
        return root;
    }

    private TreeNode deleteNodeRecurse(TreeNode node) {
        if (node.left == null && node.right == null) {
            node = null;
            return null;
        } else if (node.right != null) {
            TreeNode treeNode = successor(node);
            node.val = treeNode.val;
            return deleteNodeRecurse(treeNode);
        } else {
            TreeNode treeNode = predeccessor(node);
            node.val = treeNode.val;
            return deleteNodeRecurse(treeNode);
        }
    }

    private TreeNode successor(TreeNode node) {
        TreeNode treeNode = node.right;
        while (treeNode.left != null) {
            treeNode = treeNode.left;
        }
        return treeNode;
    }

    private TreeNode predeccessor(TreeNode node) {
        TreeNode treeNode = node.left;
        while (treeNode.right != null) {
            treeNode = treeNode.right;
        }
        return treeNode;
    }
}
