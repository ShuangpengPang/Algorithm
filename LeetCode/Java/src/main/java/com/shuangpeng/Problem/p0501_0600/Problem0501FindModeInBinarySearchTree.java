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
        List<Integer> list = new ArrayList<>();
        int prev = Integer.MIN_VALUE, maxFreq = 0, count = 0;
        TreeNode cur = root;
        while (cur != null) {
            int val = cur.val;
            if (cur.left != null) {
                TreeNode node = cur.left;
                while (node.right != null && node.right != cur) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = cur;
                    cur = cur.left;
                } else {
                    node.right = null;
                    if (val != prev) {
                        count = 0;
                    }
                    count++;
                    if (count >= maxFreq) {
                        if (count > maxFreq) {
                            maxFreq = count;
                            list.clear();
                        }
                        list.add(val);
                    }
                    prev = val;
                    cur = cur.right;
                }
            } else {
                if (val != prev) {
                    count = 0;
                }
                count++;
                if (count >= maxFreq) {
                    if (count > maxFreq) {
                        maxFreq = count;
                        list.clear();
                    }
                    list.add(val);
                }
                prev = val;
                cur = cur.right;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}

class Problem0501FindModeInBinarySearchTree0 {

    List<Integer> list;
    int prev, maxFreq, count;

    public int[] findMode(TreeNode root) {
        prev = Integer.MIN_VALUE;
        maxFreq = count = 0;
        list = new ArrayList<>();
        TreeNode cur = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur == null) {
                cur = stack.pop();
                update(cur.val);
                cur = cur.right;
            } else if (cur.left != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                update(cur.val);
                cur = cur.right;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void update(int val) {
        if (val != prev) {
            count = 0;
        }
        count++;
        if (count >= maxFreq) {
            if (count > maxFreq) {
                maxFreq = count;
                list.clear();
            }
            list.add(val);
        }
        prev = val;
    }
}
