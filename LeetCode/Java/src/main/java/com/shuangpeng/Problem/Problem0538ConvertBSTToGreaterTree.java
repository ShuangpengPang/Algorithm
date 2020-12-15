package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Problem0538ConvertBSTToGreaterTree {

    public TreeNode convertBST0(TreeNode root) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        dfs(root, sumMap);
        calculate(root, 0, 0, true, sumMap);
        return root;
    }

    public int dfs(TreeNode root, Map<Integer, Integer> sumMap) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, sumMap);
        int right = dfs(root.right, sumMap);
        sumMap.put(root.val, root.val + left + right);
        return root.val + left + right;
    }

    public void calculate(TreeNode root, int parentOrigin, int parentResult, boolean isLeft,
                          Map<Integer, Integer> sumMap) {
        if (root == null) {
            return;
        }
        int origin = root.val;
        if (isLeft) {
            int right = root.right == null ? 0 : sumMap.get(root.right.val);
            root.val = parentResult + origin + right;
        } else {
            int left = root.left == null ? 0 : sumMap.get(root.left.val);
            root.val = parentResult - parentOrigin - left;
        }
        calculate(root.left, origin, root.val, true, sumMap);
        calculate(root.right, origin, root.val, false, sumMap);
    }

    public TreeNode convertBST1(TreeNode root) {
        sum = 0;
        reverseInorder(root);
        return root;
    }

    int sum = 0;
    public void reverseInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        reverseInorder(root.right);
        sum += root.val;
        root.val = sum;
        reverseInorder(root.left);
    }


    // morris
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode node = root;
        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode current = node.right;
                while (current.left != null && current.left != node) {
                    current = current.left;
                }
                if (current.left == null) {
                    current.left = node;
                    node = node.right;
                } else {
                    current.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }
        return root;
    }
}
