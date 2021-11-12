package com.shuangpeng.Problem.p1301_1400;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Problem1339MaximumProductOfSplittedBinaryTree {

    public int maxProduct0(TreeNode root) {
        List<Long> sumList = new ArrayList<>();
        long sum = dfs(root, sumList);
        long max = 0;
        for (long s : sumList) {
//            double product = s * (sum - s);
//            if (product > max) {
//                max = product;
//            }
            max = Math.max(max, s * (sum - s));
        }
        return (int) (max % (Math.pow(10, 9) + 7));
//        return (int)(max % (int)(1e9 + 7));
    }

    private long dfs(TreeNode node, List<Long> sumList) {
        if (node == null) {
            return 0;
        }
        long leftSum = dfs(node.left, sumList);
        long rightSum = dfs(node.right, sumList);
        if (node.left != null) {
            sumList.add(leftSum);
        }
        if (node.right != null) {
            sumList.add(rightSum);
        }
        return leftSum + rightSum + node.val;
    }

    double ans = Double.MIN_VALUE;
    double allSum;
    double nodeSum;
    public int maxProduct1(TreeNode root) {
        allSum = sum(root);
        dfs(root);
        return (int)(ans % (int)(1e9 + 7));
    }

    public double sum(TreeNode node){
        if(node == null) return 0;
        return node.val + sum(node.left) + sum(node.right);
    }

    public double dfs(TreeNode node){
        if(node == null)    return 0 ;
        nodeSum = node.val + dfs(node.left) + dfs(node.right);
        ans = Math.max(ans, (allSum - nodeSum) * nodeSum);
        return nodeSum;
    }

    private long split = 0;
    private long half = 0;

    public int maxProduct(TreeNode root) {
        long sum = getSum(root);
        half = sum >> 1;
        getSplit(root);
        return (int) (split * (sum - split) % (1e9 + 7));
    }

    private long getSum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.val + getSum(node.left) + getSum(node.right);
    }

    private long getSplit(TreeNode node) {
        if (node == null) {
            return 0;
        }
        long sum = node.val + getSplit(node.left) + getSplit(node.right);
        if (Math.abs(half - sum) < Math.abs(half - split)) {
            split = sum;
        }
        return sum;
    }
}
