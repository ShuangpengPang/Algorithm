package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem1372LongestZigZagPathInABinaryTree {

    public int longestZigZag0(TreeNode root) {
        // a: 从根结点开始向左
        // b: 从根结点开始向右
        // c: 结果
        // a = lb + 1;
        // b = ra + 1;
        // c = max(a, b, lc, rc)
        if (root == null) {
            return 0;
        }
        return dfs(root)[2] - 1;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0, 0};
        }
        int[] leftArray = dfs(root.left);
        int[] rightArray = dfs(root.right);
        int[] array = new int[3];
        array[0] = leftArray[1] + 1;
        array[1] = rightArray[0] + 1;
        array[2] = Math.max(Math.max(array[0], array[1]), Math.max(leftArray[2], rightArray[2]));
        return array;
    }

    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();
    Queue<TreeNode[]> q = new LinkedList<TreeNode[]>();

    public int longestZigZag1(TreeNode root) {
        dp(root);
        int maxAns = 0;
        for (TreeNode u : f.keySet()) {
            maxAns = Math.max(maxAns, Math.max(f.get(u), g.get(u)));
        }
        return maxAns;
    }

    public void dp(TreeNode o) {
        f.put(o, 0);
        g.put(o, 0);
        q.offer(new TreeNode[]{o, null});
        while (!q.isEmpty()) {
            TreeNode[] y = q.poll();
            TreeNode u = y[0], x = y[1];
            f.put(u, 0);
            g.put(u, 0);
            if (x != null) {
                if (x.left == u) {
                    f.put(u, g.get(x) + 1);
                }
                if (x.right == u) {
                    g.put(u, f.get(x) + 1);
                }
            }
            if (u.left != null) {
                q.offer(new TreeNode[]{u.left, u});
            }
            if (u.right != null) {
                q.offer(new TreeNode[]{u.right, u});
            }
        }
    }

    public int longestZigZag2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Map<TreeNode, Integer> leftMap = new HashMap<>();
        Map<TreeNode, Integer> rightMap = new HashMap<>();
        Deque<TreeNode[]> queue = new LinkedList<>();
        queue.offerLast(new TreeNode[]{root, null});
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode[] pair = queue.pollFirst();
            TreeNode node = pair[0];
            TreeNode parent = pair[1];
            leftMap.put(node, 0);
            rightMap.put(node, 0);
            if (parent != null) {
                if (node == parent.left) {
                    int length = rightMap.get(parent) + 1;
                    leftMap.put(node, length);
                    max = Math.max(max, length);
                } else if (node == parent.right) {
                    int length = leftMap.get(parent) + 1;
                    rightMap.put(node, length);
                    max = Math.max(max, length);
                }
            }
            if (node.left != null) {
                queue.offerLast(new TreeNode[]{node.left, node});
            }
            if (node.right != null) {
                queue.offerLast(new TreeNode[]{node.right, node});
            }
        }
        return max;
    }

    private int maxLength = 0;

    public int longestZigZag(TreeNode root) {
        dfs(root, 0, 0);
        return maxLength;
    }

    private void dfs(TreeNode node, int length, int direction) {
        if (node == null) {
            return;
        }
        maxLength = Math.max(maxLength, length);
        if (node.left != null) {
            if (direction == 0) {
                dfs(node.left, 1, 0);
            } else {
                dfs(node.left, length + 1, 0);
            }
        }
        if (node.right != null) {
            if (direction == 0) {
                dfs(node.right, length + 1, 1);
            } else {
                dfs(node.right, 1, 1);
            }
        }
    }
}
