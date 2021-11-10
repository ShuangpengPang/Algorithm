package com.shuangpeng.Problem.p1001_1100;

import com.shuangpeng.common.TreeNode;

public class Problem1022SumOfRootToLeafBinaryNumbers {

    private int answer = 0;

    public int sumRootToLeaf(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 0);
        return answer;
    }

    private void dfs(TreeNode root, int sum) {
        sum = (sum << 1) + root.val;
        if (root.left == null && root.right == null) {
            answer += sum;
            return;
        }
        if (root.left != null) {
            dfs(root.left, sum);
        }
        if (root.right != null) {
            dfs(root.right, sum);
        }
    }
}
