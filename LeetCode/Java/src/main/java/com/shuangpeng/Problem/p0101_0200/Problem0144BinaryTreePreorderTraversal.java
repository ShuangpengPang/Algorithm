package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem0144BinaryTreePreorderTraversal {

    public List<Integer> preorderTraversal0(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        dfs(root, answer);
        return answer;
    }

    private void dfs(TreeNode root, List<Integer> answer) {
        if (root == null) {
            return;
        }
        answer.add(root.val);
        dfs(root.left, answer);
        dfs(root.right, answer);
    }

    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offerLast(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            answer.add(node.val);
            if (node.right != null) {
                stack.offerLast(node.right);
            }
            if (node.left != null) {
                stack.offerLast(node.left);
            }
        }
        return answer;
    }

    public List<Integer> preorderTraversal3(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        answer.add(node.val);
        stack.offerLast(node);
        while (!stack.isEmpty()) {
            while (node != null && node.left != null) {
                node = node.left;
                answer.add(node.val);
                stack.offerLast(node);
            }
            node = stack.pollLast().right;
            if (node != null) {
                answer.add(node.val);
                stack.offerLast(node);
            }
        }
        return answer;
    }

    public List<Integer> preorderTraversal4(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        if (root == null) {
            return answer;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                answer.add(node.val);
                stack.offerLast(node);
                node = node.left;
            }
            node = stack.pollLast().right;
        }
        return answer;
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        TreeNode current = root;
        while (current != null) {
            TreeNode node = current.left;
            if (node != null) {
                while (node.right != null && node.right != current) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = current;
                    answer.add(current.val);
                    current = current.left;
                } else {
                    node.right = null;
                    current = current.right;
                }
            } else {
                answer.add(current.val);
                current = current.right;
            }
        }
        return answer;
    }
}
