package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0501FindModeInBinarySearchTree（二叉搜索树中的众数）
 * @date 2023/4/23 6:48 PM
 */
public class Problem0501FindModeInBinarySearchTree {

    List<Integer> ans;
    int maxFreq;
    int count;
    int prev;

    public int[] findMode0(TreeNode root) {
        ans = new ArrayList<>();
        maxFreq = count = 0;
        prev = Integer.MIN_VALUE;
        dfs(root);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        int val = root.val;
        if (val != prev) {
            count = 0;
        }
        count++;
        if (count >= maxFreq) {
            if (count > maxFreq) {
                maxFreq = count;
                ans.clear();
            }
            ans.add(val);
        }
        prev = val;
        dfs(root.right);
    }

    public int[] findMode(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        int count = 0, maxCount = 0, pre = Integer.MIN_VALUE;
        TreeNode node = root;
        while (node != null) {
            TreeNode cur = node.left;
            if (cur != null) {
                while (cur.right != null && cur.right != node) {
                    cur = cur.right;
                }
                if (cur.right == null) {
                    cur.right = node;
                    node = node.left;
                    continue;
                } else {
                    cur.right = null;
                }
            }
            count = node.val == pre ? count + 1 : 1;
            pre = node.val;
            if (count >= maxCount) {
                if (count > maxCount) {
                    maxCount = count;
                    ans.clear();
                }
                ans.add(node.val);
            }
            node = node.right;
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Problem0501FindModeInBinarySearchTree0 {

    public int[] findMode(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        int count = 0, maxCount = 0, pre = Integer.MIN_VALUE;
        Deque<TreeNode> q = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !q.isEmpty()) {
            if (node != null) {
                q.push(node);
                node = node.left;
            } else {
                node = q.pop();
                count = node.val == pre ? count + 1 : 1;
                pre = node.val;
                if (count >= maxCount) {
                    if (count > maxCount) {
                        maxCount = count;
                        ans.clear();
                    }
                    ans.add(node.val);
                }
                node = node.right;
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
