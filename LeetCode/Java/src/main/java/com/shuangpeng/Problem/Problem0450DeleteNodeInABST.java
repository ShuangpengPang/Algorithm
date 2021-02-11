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

    public TreeNode deleteNode1(TreeNode root, int key) {
        TreeNode parent = null;
        TreeNode node = root;
        while (node != null) {
            if (node.val < key) {
                parent = node;
                node = node.right;
            } else if (node.val > key) {
                parent = node;
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
        TreeNode treeNode = deleteNodeRecurse(node);
        if (parent.left == node) {
            parent.left = treeNode;
        } else {
            parent.right = treeNode;
        }
        return root;
    }

    private TreeNode deleteNodeRecurse(TreeNode node) {
        if (node.left == null && node.right == null) {
            return null;
        }
        TreeNode[] nodes;
        if (node.right != null) {
            nodes = successor(node);
        } else {
            nodes = predeccessor(node);
        }
        node.val = nodes[1].val;
        if (nodes[0].left == nodes[1]) {
            nodes[0].left = deleteNodeRecurse(nodes[1]);
        } else {
            nodes[0].right = deleteNodeRecurse(nodes[1]);
        }
        return node;
    }

    private TreeNode[] successor(TreeNode node) {
        TreeNode parent = node;
        TreeNode treeNode = node.right;
        while (treeNode.left != null) {
            parent = treeNode;
            treeNode = treeNode.left;
        }
        return new TreeNode[]{parent, treeNode};
    }

    private TreeNode[] predeccessor(TreeNode node) {
        TreeNode parent = node;
        TreeNode treeNode = node.left;
        while (treeNode.right != null) {
            parent = treeNode;
            treeNode = treeNode.right;
        }
        return new TreeNode[]{parent, treeNode};
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        return recurse(root, key);
    }

    private TreeNode recurse(TreeNode node, int key) {
        if (node == null) {
            return null;
        }
        if (node.val > key) {
            node.left = recurse(node.left, key);
        } else if (node.val < key) {
            node.right = recurse(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.right != null) {
                node.val = getSuccessorValue(node);
                node.right = recurse(node.right, node.val);
            } else {
                node.val = getPredeccessorValue(node);
                node.left = recurse(node.left, node.val);
            }
        }
        return node;
    }

    private int getSuccessorValue(TreeNode node) {
        TreeNode treeNode = node.right;
        while (treeNode.left != null) {
            treeNode = treeNode.left;
        }
        return treeNode.val;
    }

    private int getPredeccessorValue(TreeNode node) {
        TreeNode treeNode = node.left;
        while (treeNode.right != null) {
            treeNode = treeNode.right;
        }
        return treeNode.val;
    }
}
