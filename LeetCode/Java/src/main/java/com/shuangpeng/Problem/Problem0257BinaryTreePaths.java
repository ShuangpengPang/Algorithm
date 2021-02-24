package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem0257BinaryTreePaths {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<>();
        dfs(root, new StringBuilder(), answer);
        return answer;
    }

    private void dfs(TreeNode node, StringBuilder builder, List<String> answer) {
        if (node == null) {
            return;
        }
        int length = builder.length();
        builder.append(node.val);
        if (node.left == null && node.right == null) {
            answer.add(builder.toString());
        } else {
            builder.append("->");
            dfs(node.left, builder, answer);
            dfs(node.right, builder, answer);
        }
        builder.delete(length, builder.length());
    }

//    private void dfs(TreeNode node, StringBuilder builder, List<String> answer) {
//        if (node == null) {
//            return;
//        }
//        String string = "->" + node.val;
//        builder.append(string);
//        if (node.left == null && node.right == null) {
//            answer.add(builder.toString().substring(2));
//        } else {
//            dfs(node.left, builder, answer);
//            dfs(node.right, builder, answer);
//        }
//        builder.delete(builder.length() - string.length(), builder.length());
//    }
}
