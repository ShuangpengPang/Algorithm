package com.shuangpeng.interview;

import com.shuangpeng.common.TreeNode;

public class Question0410CheckSubTree {

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        return dfs(t1, t2);
    }

    private boolean dfs(TreeNode t1, TreeNode t2) {
        if (isEqual(t1, t2)) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return dfs(t1.left, t2) || dfs(t1.right, t2);
    }

    private boolean isEqual(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        return t1.val == t2.val && isEqual(t1.left, t2.left) && isEqual(t1.right, t2.right);
    }
}
