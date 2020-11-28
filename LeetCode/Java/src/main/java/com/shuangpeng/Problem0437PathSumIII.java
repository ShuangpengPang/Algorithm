package com.shuangpeng;

public class Problem0437PathSumIII {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val){
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSequence(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    public int pathSequence(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        if (root.val == sum) {
            return 1 + pathSequence(root.left, 0) + pathSequence(root.right, 0);
        }
        return pathSequence(root.left, sum - root.val) + pathSequence(root.right, sum - root.val);
    }
}
