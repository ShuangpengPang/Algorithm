package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

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

class Problem0572SubtreeOfAnotherTree0 {

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (check(root, subRoot)) {
            return true;
        }
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private boolean check(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        if (root.val != subRoot.val) {
            return false;
        }
        return check(root.left, subRoot.left) && check(root.right, subRoot.right);
    }
}

class Problem0572SubtreeOfAnotherTree1 {

    private static final int N1 = Integer.MAX_VALUE, N2 = N1 - 1;

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        List<Integer> s = new ArrayList<>(), t = new ArrayList<>();
        dfs(root, s, true);
        dfs(subRoot, t, true);
        int n = t.size(), m = s.size();
        int[] next = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            int num = t.get(i);
            while (j > 0 && t.get(j) != num) {
                j = next[j - 1];
            }
            next[i] = j = t.get(j) == num ? j + 1 : j;
        }
        for (int i = 0, j = 0; i < m; i++) {
            int num = s.get(i);
            while (j > 0 && t.get(j) != num) {
                j = next[j - 1];
            }
            if (t.get(j) == num && ++j == n) {
                return true;
            }
        }
        return false;
    }

    private void dfs(TreeNode root, List<Integer> list, boolean isLeft) {
        if (root == null) {
            list.add(isLeft ? N1 : N2);
            return;
        }
        list.add(root.val);
        dfs(root.left, list, true);
        dfs(root.right, list, false);
    }
}
