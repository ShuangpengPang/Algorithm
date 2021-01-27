package com.shuangpeng.offer;

import com.shuangpeng.common.TreeNode;

public class Offer54 {

    private int count = 0;
    private int answer = 0;
    private boolean find = false;

    public int kthLargest(TreeNode root, int k) {
        if (k <= 0) {
            return 0;
        }
        dfs(root, k);
        return answer;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null || find) {
            return;
        }
        dfs(root.right, k);
        count++;
        if (k == count) {
            answer = root.val;
            find = true;
        }
        dfs(root.left, k);
    }
}
