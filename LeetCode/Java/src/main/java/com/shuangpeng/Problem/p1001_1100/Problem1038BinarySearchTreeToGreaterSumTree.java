package com.shuangpeng.Problem.p1001_1100;

import com.shuangpeng.common.TreeNode;

public class Problem1038BinarySearchTreeToGreaterSumTree {

    public TreeNode bstToGst0(TreeNode root) {
        dfs(root, sumDfs(root, 0));
        return root;
    }

    private void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        node.val = sum - node.val;
        dfs(node.left, sum);
        dfs(node.right, sum);
    }

    private int sumDfs(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        int leftSum = sumDfs(node.left, sum);
        int value = node.val;
        node.val = sum + leftSum;
        sum += (leftSum + value);
        int rightSum = sumDfs(node.right, sum);
        return leftSum + rightSum + value;
    }

    private int answer = 0;

    public TreeNode bstToGst1(TreeNode root) {
        antiInorder(root);
        return root;
    }

    private void antiInorder(TreeNode node) {
        if (node == null) {
            return;
        }
        antiInorder(node.right);
        answer += node.val;
        node.val = answer;
        antiInorder(node.left);
    }

    public TreeNode bstToGst2(TreeNode root) {
        reverseInorder(root, 0);
        return root;
    }

    private int reverseInorder(TreeNode node, int sum) {
        if (node == null) {
            return 0;
        }
        int rightSum = reverseInorder(node.right, sum);
        int value = node.val;
        sum += (rightSum + value);
        node.val = sum;
        int leftSum = reverseInorder(node.left, sum);
        return leftSum + rightSum + value;
    }

    public TreeNode bstToGst(TreeNode root) {
        if (root == null) {
            return null;
        }
        int sum = 0;
        TreeNode node = root;
        while (node != null) {
            if (node.right != null) {
                TreeNode leftMost = node.right;
                while (leftMost.left != null && leftMost.left != node) {
                    leftMost = leftMost.left;
                }
                if (leftMost.left == null) {
                    leftMost.left = node;
                    node = node.right;
                } else {
                    sum += node.val;
                    node.val = sum;
                    leftMost.left = null;
                    node = node.left;
                }
            } else {
                sum += node.val;
                node.val = sum;
                node = node.left;
            }
        }
        return root;
    }



//    public static void main(String[] args) {
//        System.err.println(MergeString("abcd", "123"));
//    }
//
//    public static String MergeString0(String str1, String str2) {
//        String merge = "";
//        if (str1.length() == str2.length()) {
//            for (int i = 0; i < str1.length(); i++) {
//                merge = merge + str1.charAt(i) + str2.charAt(i);
//            }
//        } else if (str1.length() < str2.length()) {
//            for (int i = 0; i < str1.length(); i++) {
//                merge = merge + str1.charAt(i) + str2.charAt(i);
//            }
//            merge = merge + str2.substring(str1.length(), str2.length());
//        } else if (str1.length() > str2.length()) {
//            for (int i = 0; i < str2.length(); i++) {
//                merge = merge + str1.charAt(i) + str2.charAt(i);
//            }
//            merge = merge + str1.substring(str2.length(), str1.length());
//        }
//        return merge;
//    }
//
//    public static String MergeString(String str1, String str2) {
//        String merge = "";
//        String minStr = "";
//        String cutStr = "";
//        int n1 = str1.length();
//        int n2 = str2.length();
//        if (n1 > n2) {
//            minStr = str2;
//            cutStr = str1.substring(n2);
//        } else {
//            minStr = str1;
//            cutStr = str2.substring(n1);
//        }
//        for (int i = 0; i < minStr.length(); i++) {
//            merge = merge + str1.charAt(i) + str2.charAt(i);
//        }
//        merge += cutStr;
//        return merge;
//    }
}
