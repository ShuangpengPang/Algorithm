package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;

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

    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offer(t);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            builder.append(node.val);
            if (node.left == null && node.right == null) {
                builder.append(")");
            } else if (node.left == null) {
                builder.append("()(");
                stack.offerLast(node.right);
            } else if (node.right == null) {
                builder.append("(");
                stack.offerLast(node.left);
            } else {
                builder.append("(");
                stack.offerLast(node.right);
                stack.offerLast(node.left);
            }
        }
        return builder.deleteCharAt(builder.length() - 1).toString();
    }
}
