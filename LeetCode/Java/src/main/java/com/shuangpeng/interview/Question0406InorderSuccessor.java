package com.shuangpeng.interview;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Question0406InorderSuccessor {

    private boolean find = false;
    private TreeNode answer = null;

    public TreeNode inorderSuccessor0(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (p.right != null) {
            TreeNode leftMost = p.right;
            while (leftMost.left != null) {
                leftMost = leftMost.left;
            }
            return leftMost;
        }
        dfs(root, p);
        return answer;
    }

    private void dfs(TreeNode node, TreeNode p) {
        if (node == null || answer != null) {
            return;
        }
        dfs(node.left, p);
        if (node == p) {
            find = true;
        } else if (find && answer == null) {
            answer = node;
            return;
        }
        dfs(node.right, p);
    }

    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (p.right != null) {
            return getLeftMost(p.right);
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != p) {
            stack.offerLast(node);
            if (node.val < p.val) {
                node = node.right;
            } else if (node.val > p.val) {
                node = node.left;
            }
        }
        if (node != p) {
            return null;
        }
        TreeNode current = p;
        while (!stack.isEmpty()) {
            TreeNode parent = stack.removeLast();
            if (current == parent.left) {
                return parent;
            }
            current = parent;
        }
        return null;
    }

    private TreeNode getLeftMost(TreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        TreeNode pre = null;
        while (root.val != p.val) {
            //右边
            if (p.val > root.val) {
                root = root.right;
            }
            //左边
            else {
                pre = root;
                root = root.left;
            }
        }
        //假如没有右子树
        if (root.right == null) {
            return pre;
        } else {
            root = root.right;
            while (root.left != null) {
                root = root.left;
            }
            return root;
        }
    }

    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (p.right != null) {
            TreeNode leftMost = p.right;
            while (leftMost.left != null) {
                leftMost = leftMost.left;
            }
            return leftMost;
        }
        TreeNode previous = null;
        TreeNode node = root;
        while (node != null && node != p) {
            if (node.val < p.val) {
                node = node.right;
            } else {
                previous = node;
                node = node.left;
            }
        }
        if (node != p) {
            return null;
        }
        return previous;
    }

    public TreeNode inorderSuccessor4(TreeNode root, TreeNode p) {
        TreeNode[] ans = new TreeNode[1];
        findSuccessor(root, p, new int[]{0}, ans);
        return ans[0];
    }

    private void findSuccessor(TreeNode root, TreeNode p, int[] flag, TreeNode[] ans) {
        if (root == null || p == null) {
            return;
        }
        findSuccessor(root.left, p, flag, ans);
        if (flag[0] == 2) {
            return;
        }
        if (flag[0] == 1) {
            ans[0] = root;
            flag[0] = 2;
            return;
        }
        if (root == p) {
            flag[0] = 1;
        }
        findSuccessor(root.right, p, flag, ans);
    }

    public TreeNode inorderSuccessor5(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        TreeNode prev = null;
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !deque.isEmpty()) {
            if (node != null) {
                deque.addLast(node);
                node = node.left;
            } else {
                if (prev == p) {
                    return deque.pollLast();
                }
                prev = deque.pollLast();
                node = prev.right;
            }
        }
        return null;
    }

    public TreeNode inorderSuccessor6(TreeNode root, TreeNode p) {
        if (p == null) {
            return null;
        }
        TreeNode successor = null;
        if (p.right != null) {
            successor = p.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }
        TreeNode node = root;
        while (node != p && node != null) {
            if (p.val > node.val) {
                node = node.right;
            } else {
                successor = node;
                node = node.left;
            }
        }
        return node == p ? successor : null;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        }
        TreeNode ans = inorderSuccessor(root.left, p);
        return ans == null ? root : ans;
    }
}
