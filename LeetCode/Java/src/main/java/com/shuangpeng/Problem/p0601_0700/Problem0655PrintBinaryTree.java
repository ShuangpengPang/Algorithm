package com.shuangpeng.Problem.p0601_0700;

import com.shuangpeng.common.TreeNode;

import java.util.*;

/**
 * @Description: 输出二叉树
 * @Date 2022/8/22 11:40 AM
 **/
public class Problem0655PrintBinaryTree {

    public List<List<String>> printTree0(TreeNode root) {
        List<List<String>> answer = new ArrayList<>();
        int width = getWidth(root);
        printDfs(root, 0, answer, 0, width);
        return answer;
    }

    private int getWidth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return (Math.max(getWidth(node.left), getWidth(node.right)) << 1) + 1;
    }

    private void printDfs(TreeNode node, int depth, List<List<String>> answer, int start, int width) {
        if (node == null) {
            return;
        }
        if (depth >= answer.size()) {
            answer.add(new ArrayList<>());
        }
        List<String> list = answer.get(depth);
        int size = list.size();
        for (int i = size; i < start; i++) {
            list.add("");
        }
        int half = width >> 1;
        for (int i = 0; i < half; i++) {
            list.add("");
        }
        list.add(Integer.toString(node.val));
        for (int i = 0; i < half; i++) {
            list.add("");
        }
        printDfs(node.left, depth + 1, answer, start, half);
        printDfs(node.right, depth + 1, answer, start + half + 1, half);
    }

    public List<List<String>> printTree2(TreeNode root) {
        int height = getHeight(root);
        int width = (1 << height) - 1;
        String[][] strings = new String[height][width];
        for (String[] array : strings) {
            Arrays.fill(array, "");
        }
        fill(root, strings, 0, 0, width);
        List<List<String>> answer = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            answer.add(Arrays.asList(strings[i]));
        }
        return answer;
    }

    private void fill(TreeNode node, String[][] strings, int level, int start, int length) {
        if (node == null) {
            return;
        }
        int half = length >> 1;
        strings[level][start + half] = "" + node.val;
        fill(node.left, strings, level + 1, start, half);
        fill(node.right, strings, level + 1, start + half + 1, half);
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    class NodeInfo {
        TreeNode node;
        int level;
        int start;
        int length;

        public NodeInfo(TreeNode node, int level, int start, int length) {
            this.node = node;
            this.level = level;
            this.start = start;
            this.length = length;
        }
    }

    public List<List<String>> printTree(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        int height = getHeightBFS(root);
        int width = (1 << height) - 1;
        String[][] strings = new String[height][width];
        for (String[] a : strings) {
            Arrays.fill(a, "");
        }
        NodeInfo nodeInfo = new NodeInfo(root, 0, 0, width);
        Queue<NodeInfo> queue = new LinkedList<>();
        queue.offer(nodeInfo);
        while (!queue.isEmpty()) {
            NodeInfo info = queue.poll();
            TreeNode node = info.node;
            int level = info.level;
            int start = info.start;
            int length = info.length;
            int half = length >> 1;
            strings[level][start + half] = "" + node.val;
            if (node.left != null) {
                queue.offer(new NodeInfo(node.left, level + 1, start, half));
            }
            if (node.right != null) {
                queue.offer(new NodeInfo(node.right, level + 1, start + half + 1, half));
            }
        }
        List<List<String>> answer = new ArrayList<>(height);
        for (String[] a : strings) {
            answer.add(Arrays.asList(a));
        }
        return answer;
    }

    private int getHeightBFS(TreeNode node) {
        if (node == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int height = 0;
        while (!queue.isEmpty()) {
            height++;
            for (int i = queue.size(); i > 0; i--) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
        }
        return height;
    }
}

class Problem0655PrintBinaryTree0 {

    int m, n;

    public List<List<String>> printTree(TreeNode root) {
        m = getHeight(root);
        n = (1 << m) -1;
        List<List<String>> ans = new ArrayList<>(m);
        dfs(root, ans, 0, (n - 1) >> 1);
        return ans;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    private void dfs(TreeNode node, List<List<String>> ans, int level, int c) {
        if (node == null) {
            return;
        }
        if (level == ans.size()) {
            List<String> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                list.add("");
            }
            ans.add(list);
        }
        ans.get(level).set(c, "" + node.val);
        dfs(node.left, ans, level + 1, c - (1 << (m - level - 2)));
        dfs(node.right, ans, level + 1, c + (1 << (m - level - 2)));
    }
}

class Problem0655PrintBinaryTree1 {

    class Node {
        TreeNode node;
        int c, l;
        Node(TreeNode node, int c, int l) {
            this.node = node;
            this.c = c;
            this.l = l;
        }
    }

    public List<List<String>> printTree(TreeNode root) {
        int m = getHeight(root), n = (1 << m) - 1;
        Node node = new Node(root, (n - 1) >> 1, 1);
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        List<List<String>> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add("");
            }
            for (int i = q.size() - 1; i >= 0; i--) {
                Node parent = q.poll();
                list.set(parent.c, "" + parent.node.val);
                TreeNode left = parent.node.left, right = parent.node.right;
                int cnt = (1 << (m - parent.l - 1));
                if (left != null) {
                    q.offer(new Node(left, parent.c - cnt, parent.l + 1));
                }
                if (right != null) {
                    q.offer(new Node(right, parent.c + cnt, parent.l + 1));
                }
            }
            ans.add(list);
        }
        return ans;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}

