package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem0095UniqueBinaryTreesII {

    public List<TreeNode> generateTrees0(int n) {
        return build(1, n);
    }

    private List<TreeNode> build(int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new ArrayList<TreeNode>() {{
                add(new TreeNode(start));
            }};
        }
        List<TreeNode> answer = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = build(start, i - 1);
            List<TreeNode> right = build(i + 1, end);
            if (left == null) {
                for (TreeNode rightNode : right) {
                    TreeNode node = new TreeNode(i);
                    node.right = rightNode;
                    answer.add(node);
                }
            } else if (right == null) {
                for (TreeNode leftNode : left) {
                    TreeNode node = new TreeNode(i);
                    node.left = leftNode;
                    answer.add(node);
                }
            } else {
                for (TreeNode leftNode : left) {
                    for (TreeNode rightNode : right) {
                        TreeNode node = new TreeNode(i);
                        node.left = leftNode;
                        node.right = rightNode;
                        answer.add(node);
                    }
                }
            }
        }
        return answer;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return buildTrees(1, n);
    }

    private List<TreeNode> buildTrees(int start, int end) {
        List<TreeNode> answer = new ArrayList<>();
        if (start > end) {
            answer.add(null);
            return answer;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = buildTrees(start, i - 1);
            List<TreeNode> rightList = buildTrees(i + 1, end);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    answer.add(node);
                }
            }
        }
        return answer;
    }
}
