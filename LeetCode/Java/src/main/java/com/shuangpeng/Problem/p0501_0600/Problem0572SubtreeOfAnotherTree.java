package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0572SubtreeOfAnotherTree（另一棵树的子树）
 * @date 2024/1/22 10:03 AM
 */
public class Problem0572SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        char[] s = serialize(root).toString().toCharArray(), t = serialize(subRoot).toString().toCharArray();
        int m = s.length, n = t.length;
        int[] next = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && t[i] != t[j]) {
                j = next[j - 1];
            }
            next[i] = j = t[i] == t[j] ? j + 1 : j;
        }
        for (int i = 0, j = 0; i < m; i++) {
            while (j > 0 && s[i] != t[j]) {
                j = next[j - 1];
            }
            if (s[i] == t[j]) {
                if (++j == n) {
                    return true;
                }
            }
        }
        return false;
    }

    private StringBuilder serialize(TreeNode root) {
        if (root == null) {
            return new StringBuilder("");
        }
        StringBuilder left = serialize(root.left), right = serialize(root.right);
        StringBuilder sb = new StringBuilder(left.length() + right.length() + 9);
        sb.append('(').append(left).append(root.val).append(right).append(')');
        return sb;
    }
}
