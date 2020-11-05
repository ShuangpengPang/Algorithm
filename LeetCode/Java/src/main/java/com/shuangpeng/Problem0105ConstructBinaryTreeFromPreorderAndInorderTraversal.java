package com.shuangpeng;

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

    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        preorder = [3,9,20,15,7]
//        inorder = [9,3,15,20,7]
        return partition(preorder, inorder, 0, preorder.length - 1, 0);
    }

    public TreeNode partition(int[] preorder, int[] inorder, int pStart, int pEnd, int iStart) {
        if (pStart > pEnd) {
            return null;
        }
        if (pStart == pEnd) {
            return new TreeNode(preorder[pStart]);
        }
        int i = 0;
        while (pStart + i <= pEnd && preorder[pStart] != inorder[iStart + i]) {
            i++;
        }
        TreeNode root = new TreeNode(preorder[pStart]);
        root.left = partition(preorder, inorder, pStart + 1, pStart + i, iStart);
        root.right = partition(preorder, inorder, pStart + i + 1, pEnd, iStart + i + 1);
        return root;
    }
}
