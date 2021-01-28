package com.shuangpeng.offer;

import com.shuangpeng.common.TreeNode;

public class Offer26 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null) {
            return false;
        }
        return checkSubStructure(A, B);
    }

    private boolean checkSubStructure(TreeNode A, TreeNode B) {
        if (A == null) {
            return false;
        }
        if (checkStructure(A, B)) {
            return true;
        }
        return checkSubStructure(A.left, B) || checkSubStructure(A.right, B);
    }

    private boolean checkStructure(TreeNode a, TreeNode b) {
        if (a == null || a.val != b.val) {
            return false;
        }
        if (b.left != null && !checkStructure(a.left, b.left)) {
            return false;
        }
        if (b.right != null && !checkStructure(a.right, b.right)) {
            return false;
        }
        return true;
    }
}
