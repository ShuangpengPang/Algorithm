package com.shuangpeng.Problem.p0901_1000;

import com.shuangpeng.common.TreeNode;

public class Problem0988SmallestStringStartingFromLeaf {

//    public static void main(String[] args) {
//        TreeNode root = new TreeNode(0);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);
//        root.right.left = new TreeNode(3);
//        root.right.right = new TreeNode(4);
//        Problem0988SmallestStringStartingFromLeaf a = new Problem0988SmallestStringStartingFromLeaf();
//        System.err.println(a.smallestFromLeaf(root));
//    }

    private String answer = String.valueOf((char) ('a' + 27));

    public String smallestFromLeaf(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root, new StringBuilder());
        return answer;
    }

    private void dfs(TreeNode node, StringBuilder builder) {
        if (node == null) {
            return;
        }
        builder.insert(0, (char) ('a' + node.val));
        if (node.left == null && node.right == null) {
            int n1 = builder.length();
            int n2 = answer.length();
            int i = 0;
            while (i < n1 && i < n2) {
                if (builder.charAt(i) < answer.charAt(i)) {
                    answer = builder.toString();
                    break;
                } else if (builder.charAt(i) > answer.charAt(i)) {
                    break;
                }
                i++;
            }
            if (i >= n1) {
                answer = builder.toString();
            }
        } else {
            dfs(node.left, builder);
            dfs(node.right, builder);
        }
        builder.deleteCharAt(0);
    }
}
