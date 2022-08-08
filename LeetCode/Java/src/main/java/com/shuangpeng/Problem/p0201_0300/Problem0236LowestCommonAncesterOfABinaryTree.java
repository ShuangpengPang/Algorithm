package com.shuangpeng.Problem.p0201_0300;

import com.shuangpeng.common.TreeNode;

import java.util.*;

/**
 * @Description: 二叉树的最近公共祖先
 * @Date 2022/8/8 5:53 PM
 **/
public class Problem0236LowestCommonAncesterOfABinaryTree {

    public TreeNode lowestCommonAncestor0(TreeNode root, TreeNode p, TreeNode q) {
        Map<String, TreeNode> map = new HashMap<>();
        return findCommonAncestor(root, p, q, map);
    }

    public TreeNode findCommonAncestor(TreeNode root, TreeNode p, TreeNode q, Map<String, TreeNode> map) {
        if (root == p) {
            if (checkNode(root.left, q, map) != null || checkNode(root.right, q, map) != null) {
                return root;
            } else {
                return null;
            }
        }
        if (root == q) {
            if (checkNode(root.left, p, map) != null || checkNode(root.right, p, map) != null) {
                return root;
            } else {
                return null;
            }
        }
        TreeNode pLeft = checkNode(root.left, p, map);
        TreeNode qRight = checkNode(root.right, q, map);
        if ((pLeft == null && qRight == null) || (pLeft != null &&qRight != null)) {
            return root;
        }
        if (pLeft != null) {
            return findCommonAncestor(root.left, p, q, map);
        }
        return findCommonAncestor(root.right, p, q, map);
    }

    public TreeNode checkNode(TreeNode root, TreeNode node, Map<String, TreeNode> map) {
        if (root == null) {
            return null;
        }
        if (root == node) {
            return root;
        }
        String key = "" + root.val + "-" + node.val;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (root.left != null) {
            TreeNode left = checkNode(root.left, node, map);
            if (left != null) {
                map.put(key, left);
                return left;
            }
        }
        TreeNode right = checkNode(root.right, node, map);
        map.put(key, right);
        return right;
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return result;
    }

    TreeNode result;
    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean left = dfs(root.left, p, q);
        boolean right = dfs(root.right, p, q);
        if ((left && right) || ((root == p || root == q) && (left || right))) {
            result = root;
        }
        return left || right || root == p || root == q;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                map.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                map.put(node.right, node);
                queue.offer(node.right);
            }
        }
        Set<TreeNode> visited = new HashSet<>();
        visited.add(p);
        TreeNode parent = map.get(p);
        while (parent != null) {
            visited.add(parent);
            parent = map.get(parent);
        }
        TreeNode current = q;
        while (!visited.contains(current)) {
            current = map.get(current);
        }
        return current;
    }
}

class Problem0236LowestCommonAncesterOfABinaryTree0 {
    TreeNode ans, p, q;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        this.p = p;
        this.q = q;
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null || ans != null) {
            return 0;
        }
        int status = dfs(root.left) | dfs(root.right);
        if (root == p) {
            status |= 1;
        }
        if (root == q) {
            status |= 2;
        }
        if (status == 3 && ans == null) {
            ans = root;
        }
        return status;
    }
}

class Problem0236LowestCommonAncesterOfABinaryTree1 {

    TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ans = null;
        dfs(root, p, q);
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || ans != null) {
            return false;
        }
        boolean left = dfs(root.left, p, q), right = dfs(root.right, p, q);
        if ((left && right) || ((left || right) && (root == p || root == q))) {
            ans = root;
        }
        return left || right || root == p || root == q;
    }
}

class Problem0236LowestCommonAncesterOfABinaryTree2 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        dfs(root, p, list1);
        dfs(root, q, list2);
        TreeNode ans = null;
        int n = Math.min(list1.size(), list2.size());
        for (int i = 0; i < n && list1.get(i) == list2.get(i); i++) {
            ans = list1.get(i);
        }
        return ans;
    }

    private boolean dfs(TreeNode root, TreeNode node, List<TreeNode> list) {
        if (root == null) {
            return false;
        }
        list.add(root);
        if (root == node) {
            return true;
        }
        if (dfs(root.left, node, list) || dfs(root.right, node, list)) {
            return true;
        }
        list.remove(list.size() - 1);
        return false;
    }
}

class Problem0236LowestCommonAncesterOfABinaryTree3 {

    Map<Integer, TreeNode> parent;
    Set<Integer> visited;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        parent = new HashMap<>();
        visited = new HashSet<>();
        dfs(root, null);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        parent.put(root.val, p);
        dfs(root.left, root);
        dfs(root.right, root);
    }
}

class Problem0236LowestCommonAncesterOfABinaryTree4 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dfs(root, p, q);
    }

    private TreeNode dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = dfs(root.left, p, q), right = dfs(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}