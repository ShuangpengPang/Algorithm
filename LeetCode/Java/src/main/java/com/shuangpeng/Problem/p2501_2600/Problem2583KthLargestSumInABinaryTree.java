package com.shuangpeng.Problem.p2501_2600;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2583KthLargestSumInABinaryTree（二叉树中的第K大层和）
 * @date 2023/12/4 10:41 PM
 */
public class Problem2583KthLargestSumInABinaryTree {

    public long kthLargestLevelSum0(TreeNode root, int k) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        List<Long> list = new ArrayList<>();
        while (!q.isEmpty()) {
            long sum = 0;
            for (int i = q.size() - 1; i >= 0; i--) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            list.add(sum);
        }
        if (list.size() < k) {
            return -1;
        }
        return getKth(list, 0, list.size() - 1, k);
    }

    private long getKth(List<Long> list, int start, int end, int k) {
        if (start == end) {
            return list.get(start);
        }
        long pivot = list.get(end);
        int p = start;
        for (int i = start; i < end; i++) {
            if (list.get(i) > pivot) {
                swap(list, p, i);
                p++;
            }
        }
        swap(list, p, end);
        if (k <= p) {
            return getKth(list, start, p - 1, k);
        }
        if (k > p + 1) {
            return getKth(list, p + 1, end, k);
        }
        return pivot;
    }

    private void swap(List<Long> list, int i, int j) {
        if (i != j) {
            long tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
        }
    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        PriorityQueue<Long> pq = new PriorityQueue<>(k + 1);
        while (!q.isEmpty()) {
            long sum = 0;
            for (int i = q.size() - 1; i >= 0; i--) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            pq.offer(sum);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        if (pq.size() < k) {
            return -1;
        }
        return pq.poll();
    }
}

class Problem2583KthLargestSumInABinaryTree0 {

    public long kthLargestLevelSum(TreeNode root, int k) {
        List<Long> list = new ArrayList<>();
        dfs(root, list, 0);
        if (list.size() < k) {
            return -1;
        }
        PriorityQueue<Long> q = new PriorityQueue<>(k + 1);
        for (long num : list) {
            q.offer(num);
            if (q.size() > k) {
                q.poll();
            }
        }
        return q.poll();
    }

    private void dfs(TreeNode root, List<Long> list, int level) {
        if (root == null) {
            return;
        }
        if (list.size() == level) {
            list.add((long) root.val);
        } else {
            list.set(level, list.get(level) + root.val);
        }
        dfs(root.left, list, level + 1);
        dfs(root.right, list, level + 1);
    }
}
