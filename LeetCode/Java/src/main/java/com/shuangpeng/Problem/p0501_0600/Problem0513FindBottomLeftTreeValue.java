package com.shuangpeng.Problem.p0501_0600;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/***
 * @Description: 找树左下角的值
 * @Date 2022/6/22 11:11 AM
 **/
public class Problem0513FindBottomLeftTreeValue {

    private int answer = 0;
    private int maxDepth = 0;

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 1);
        return answer;
    }

    private void dfs(TreeNode node, int depth) {
        if (node != null) {
            if (node.left == null && node.right == null && depth > maxDepth) {
                answer = node.val;
                maxDepth = depth;
                return;
            }
            dfs(node.left, depth + 1);
            dfs(node.right, depth + 1);
        }
    }
}

class Problem0513FindBottomLeftTreeValue0 {

    private int ans;
    private int depth;

    public int findBottomLeftValue0(TreeNode root) {
        depth = -1;
        dfs(root, 0);
        return ans;
    }

    private void dfs(TreeNode root, int d) {
        if (root.left == null && d > depth) {
            ans = root.val;
            depth = d;
        }
        if (root.left != null) {
            dfs(root.left, d + 1);
        }
        if (root.right != null) {
            dfs(root.right, d + 1);
        }
    }

    public int findBottomLeftValue1(TreeNode root) {
        int ans = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            ans = q.peek().val;
            for (int i = q.size() - 1; i >= 0; --i) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }
        return ans;
    }

    public int findBottomLeftValue2(TreeNode root) {
        int depth = -1;
        Deque<TreeNode> stack = new ArrayDeque<>();
        int d = 0;
        int ans = 0;
        TreeNode node = root;
        TreeNode last = null;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                if (d > depth) {
                    depth = d;
                    ans = node.val;
                }
                stack.push(node);
                ++d;
                node = node.left;
            }
            node = stack.peek().right;
            if (node == null || node == last) {
                last = stack.pop();
                --d;
                node = null;
            }
        }
        return ans;
    }

    public int findBottomLeftValue(TreeNode root) {
        int ans = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.right != null) {
                q.offer(node.right);
            }
            if (node.left != null ) {
                q.offer(node.left);
            }
            ans = node.val;
        }
        return ans;
    }
}
