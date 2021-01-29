package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Problem0865SmallestSubtreeWithAllTheDeepestNodes {

    private int maxLevel = -1;
    private int maxCount = 0;

    public TreeNode subtreeWithAllDeepest0(TreeNode root) {
        if (root == null) {
            return null;
        }
        Map<TreeNode, int[]> map = new HashMap<>();
        recurse(root, 0, map);
        return getSubtree(root, map);
    }

    private int[] recurse(TreeNode node, int level, Map<TreeNode, int[]> map) {
        if (node.left == null && node.right == null) {
            if (level > maxLevel) {
                maxLevel = level;
                maxCount = 1;
            } else if (level == maxLevel) {
                maxCount++;
            }
            int[] info = new int[]{level, 1};
            map.put(node, info);
            return info;
        }
        int[] leftInfo = new int[]{-1, 0};
        int[] rightInfo = new int[]{-1, 0};
        if (node.left != null) {
            leftInfo = recurse(node.left, level + 1, map);
        }
        if (node.right != null) {
            rightInfo = recurse(node.right, level + 1, map);
        }
        int count = leftInfo[0] > rightInfo[0] ? leftInfo[1] : rightInfo[1];
        if (leftInfo[0] == rightInfo[0]) {
            count = leftInfo[1] + rightInfo[1];
        }
        int depth = Math.max(leftInfo[0], rightInfo[0]);
        int[] info = new int[]{depth, count};
        map.put(node, info);
        return info;
    }

    private TreeNode getSubtree(TreeNode root, Map<TreeNode, int[]> map) {
        if (root.left != null) {
            int[] info = map.get(root.left);
            if (info[0] == maxLevel && info[1] == maxCount) {
                return getSubtree(root.left, map);
            }
        }
        if (root.right != null) {
            int[] info = map.get(root.right);
            if (info[0] == maxLevel && info[1] == maxCount) {
                return getSubtree(root.right, map);
            }
        }
        return root;
    }

    Map<TreeNode, Integer> depth;
    int max_depth;

    public TreeNode subtreeWithAllDeepest1(TreeNode root) {
        depth = new HashMap();
        depth.put(null, -1);
        dfs(root, null);
        max_depth = -1;
        for (Integer d : depth.values())
            max_depth = Math.max(max_depth, d);

        return answer(root);
    }

    public void dfs(TreeNode node, TreeNode parent) {
        if (node != null) {
            depth.put(node, depth.get(parent) + 1);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }

    public TreeNode answer(TreeNode node) {
        if (node == null || depth.get(node) == max_depth)
            return node;
        TreeNode L = answer(node.left),
                R = answer(node.right);
        if (L != null && R != null) return node;
        if (L != null) return L;
        if (R != null) return R;
        return null;
    }


    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        return dfs(root).node;
    }

    private Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(null, 0);
        }
        Result leftInfo = dfs(node.left);
        Result rightInfo = dfs(node.right);
        if (leftInfo.depth == rightInfo.depth) {
            return new Result(node, leftInfo.depth + 1);
        } else if (leftInfo.depth > rightInfo.depth) {
            leftInfo.depth += 1;
            return leftInfo;
        } else {
            rightInfo.depth += 1;
            return rightInfo;
        }
    }

    class Result {
        TreeNode node;
        int depth;

        public Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

//    // Return the result of the subtree at this node.
//    public Result dfs(TreeNode node) {
//        if (node == null) return new Result(null, 0);
//        Result L = dfs(node.left),
//                R = dfs(node.right);
//        if (L.dist > R.dist) return new Result(L.node, L.dist + 1);
//        if (L.dist < R.dist) return new Result(R.node, R.dist + 1);
//        return new Result(node, L.dist + 1);
//    }
//
//    /**
//     * The result of a subtree is:
//     * Result.node: the largest depth node that is equal to or
//     * an ancestor of all the deepest nodes of this subtree.
//     * Result.dist: the number of nodes in the path from the root
//     * of this subtree, to the deepest node in this subtree.
//     */
//    class Result {
//        TreeNode node;
//        int dist;
//
//        Result(TreeNode n, int d) {
//            node = n;
//            dist = d;
//        }
//    }
}
