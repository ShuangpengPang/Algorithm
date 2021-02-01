package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class Problem0606ConstructStringFromBinaryTree {

    public String tree2str0(TreeNode t) {
        if (t == null) {
            return "";
        }
        return preOrder(t);
    }

    private String preOrder(TreeNode node) {
        if (node == null) {
            return "()";
        }
        String left = preOrder(node.left);
        String right = preOrder(node.right);
        if (right.equals("()") && left.equals("()")) {
            return "" + node.val;
        }
        if (right.equals("()")) {
            return "" + node.val + "(" + left + ")";
        }
        if (left.equals("()")) {
            return "" + node.val + "()(" + right + ")";
        }
        return "" + node.val + "(" + left + ")(" + right + ")";
    }

    public String tree2str1(TreeNode t) {
        return dfs(t);
    }

    private String dfs(TreeNode node) {
        if (node == null) {
            return "";
        }
        if (node.left == null && node.right == null) {
            return node.val + "";
        }
        if (node.right == null) {
            return node.val + "(" + dfs(node.left) + ")";
        }
        return node.val + "(" + dfs(node.left) + ")(" + dfs(node.right) + ")";
    }

    public String tree2str2(TreeNode t) {
        if (t == null) {
            return "";
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        stack.offerLast(t);
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            TreeNode node = stack.peekLast();
            if (!visited.contains(node)) {
                builder.append("(" + node.val);
                visited.add(node);
                if (node.left == null && node.right != null) {
                    builder.append("()");
                }
                if (node.right != null) {
                    stack.offerLast(node.right);
                }
                if (node.left != null) {
                    stack.offerLast(node.left);
                }
            } else {
                stack.pollLast();
                builder.append(')');
            }
        }
        return builder.substring(1, builder.length() - 1);
    }

    public String tree2str(TreeNode t) {
        if (t == null)
            return "";
        Stack<TreeNode> stack = new Stack<>();
        stack.push(t);
        Set<TreeNode> visited = new HashSet<>();
        StringBuilder s = new StringBuilder();
        while (!stack.isEmpty()) {
            t = stack.peek();
            if (visited.contains(t)) {
                stack.pop();
                s.append(")");
            } else {
                visited.add(t);
                s.append("(" + t.val);
                if (t.left == null && t.right != null)
                    s.append("()");
                if (t.right != null)
                    stack.push(t.right);
                if (t.left != null)
                    stack.push(t.left);
            }
        }
        return s.substring(1, s.length() - 1);
    }
}
