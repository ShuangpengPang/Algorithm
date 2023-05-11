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
        int prev = Integer.MIN_VALUE, maxFreq = 0, freq = 0;
        TreeNode node = root;
        List<Integer> ans = new ArrayList<>();
        while (node != null) {
            if (node.left != null) {
                TreeNode cur = node.left;
                while (cur.right != null && cur.right != node) {
                    cur = cur.right;
                }
                if (cur.right == null) {
                    cur.right = node;
                    node = node.left;
                    continue;
                }
                cur.right = null;
            }
            int val = node.val;
            if (prev == val) {
                freq++;
            } else {
                prev = val;
                freq = 1;
            }
            if (freq >= maxFreq) {
                if (freq > maxFreq) {
                    maxFreq = freq;
                    ans.clear();
                }
                ans.add(val);
            }
            node = node.right;
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Problem0501FindModeInBinarySearchTree0 {

    public int[] findMode(TreeNode root) {
        int prev = Integer.MIN_VALUE, freq = 0, maxFreq = 0;
        List<Integer> ans = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
                continue;
            }
            node = stack.pop();
            int val = node.val;
            if (val == prev) {
                freq++;
            } else {
                prev = val;
                freq = 1;
            }
            if (freq >= maxFreq) {
                if (freq > maxFreq) {
                    maxFreq = freq;
                    ans.clear();
                }
                ans.add(val);
            }
            node = node.right;
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
