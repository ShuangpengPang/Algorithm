package com.shuangpeng.Problem.p1301_1400;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem1315SumOfNodesWithEvenValuedGrandparent {

    public int sumEvenGrandparent0(TreeNode root) {
        return dfs(root);
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sum = 0;
        if ((node.val & 1) == 0) {
            if (node.left != null) {
                if (node.left.left != null) {
                    sum += node.left.left.val;
                }
                if (node.left.right != null) {
                    sum += node.left.right.val;
                }
            }
            if (node.right != null) {
                if (node.right.left != null) {
                    sum += node.right.left.val;
                }
                if (node.right.right != null) {
                    sum += node.right.right.val;
                }
            }
        }
        sum += dfs(node.left);
        sum += dfs(node.right);
        return sum;
    }

    public int sumEvenGrandparent1(TreeNode root) {
        return recurse(1, 1, root);
    }

    private int recurse(int grandparentValue, int parentValue, TreeNode node) {
        if (node == null) {
            return 0;
        }
        int sum = 0;
        if ((grandparentValue & 1) == 0) {
            sum += node.val;
        }
        sum += recurse(parentValue, node.val, node.left);
        sum += recurse(parentValue, node.val, node.right);
        return sum;
    }

    public int sumEvenGrandparent2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        if (root.left != null) {
            queue.offer(root.left);
        }
        if (root.right != null) {
            queue.offer(root.right);
        }
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            boolean isEven = (node.val & 1) == 0;
            if (node.left != null && node.left.left != null) {
                queue.offer(node.left.left);
                if (isEven) {
                    sum += node.left.left.val;
                }
            }
            if (node.left != null && node.left.right != null) {
                queue.offer(node.left.right);
                if (isEven) {
                    sum += node.left.right.val;
                }
            }
            if (node.right != null && node.right.left != null) {
                queue.offer(node.right.left);
                if (isEven) {
                    sum += node.right.left.val;
                }
            }
            if (node.right != null && node.right.right != null) {
                queue.offer(node.right.right);
                if (isEven) {
                    sum += node.right.right.val;
                }
            }
        }
        return sum;
    }

    public int sumEvenGrandparent(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if ((node.val & 1) == 0) {
                if (node.left != null) {
                    if (node.left.left != null) {
                        sum += node.left.left.val;
                    }
                    if (node.left.right != null) {
                        sum += node.left.right.val;
                    }
                }
                if (node.right != null) {
                    if (node.right.left != null) {
                        sum += node.right.left.val;
                    }
                    if (node.right.right != null) {
                        sum += node.right.right.val;
                    }
                }
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return sum;
    }
}
