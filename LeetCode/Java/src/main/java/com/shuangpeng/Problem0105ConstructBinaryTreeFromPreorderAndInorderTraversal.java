package com.shuangpeng;

import java.util.HashMap;
import java.util.Map;

public class Problem0105ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode buildTree0(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return partition(preorder, map, 0, preorder.length - 1, 0);
    }

    public TreeNode partition(int[] preorder, Map<Integer, Integer> map, int pStart, int pEnd, int iStart) {
        if (pStart > pEnd) {
            return null;
        }
        if (pStart == pEnd) {
            return new TreeNode(preorder[pStart]);
        }
        int index = map.get(preorder[pStart]);
        // preorder[pStart] = inorder[iStart + i]
        TreeNode root = new TreeNode(preorder[pStart]);
        root.left = partition(preorder, map, pStart + 1, pStart + index - iStart, iStart);
        root.right = partition(preorder, map, pStart + index - iStart + 1, pEnd, index + 1);
        return root;
    }

    public static void main(String[] args) {
//        [3,9,20,15,7]
//        [9,3,15,20,7]

//        [1,2,3]
//        [2,3,1]

        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        Problem0105ConstructBinaryTreeFromPreorderAndInorderTraversal a = new Problem0105ConstructBinaryTreeFromPreorderAndInorderTraversal();
        a.buildTree(preorder, inorder);
    }

    // 迭代
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode head = root;
        int i = 0;
        int j = 0;
        TreeNode[] stack = new TreeNode[preorder.length];
        int count = 0;
        while (i < preorder.length) {
            while (i < preorder.length) {
                stack[count++] = root;
                if (root.val == inorder[j]) {
                    break;
                }
                i++;
                if (i >= preorder.length) {
                    break;
                }
                TreeNode node = new TreeNode(preorder[i]);
                root.left = node;
                root = node;
            }
            if (i >= preorder.length - 1) {
                break;
            }
            while (root.val == inorder[j] && count > 1 && stack[count - 2].val == inorder[j + 1]) {
                count--;
                root = stack[count - 1];
                j++;
            }
            root = stack[--count];
            j++;
            TreeNode right = new TreeNode(preorder[i + 1]);
            root.right = right;
            root = right;
            // root.val != inorder[j]
            i++;
        }
        return head;
    }

    // 迭代（优化）
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode[] stack = new TreeNode[preorder.length];
        int count = 0;
        stack[count++] = root;
        TreeNode top = root;
        int j = 0;
        for (int i = 1; i < preorder.length; i++) {
            if (inorder[j] != top.val) {
                TreeNode node = new TreeNode(preorder[i]);
                top.left = node;
                stack[count++] = node;
                top = node;
            } else {
                TreeNode node = top;
                while (count > 0 && top.val == inorder[j]) {
                    node = stack[--count];
                    if (count > 0) {
                        top = stack[count - 1];
                    }
                    j++;
                }
                TreeNode current = new TreeNode(preorder[i]);
                node.right = current;
                stack[count++] = current;
                top = current;
            }
        }
        return root;
    }
}
