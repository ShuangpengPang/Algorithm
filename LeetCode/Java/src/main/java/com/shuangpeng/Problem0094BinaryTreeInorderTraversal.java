package com.shuangpeng;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem0094BinaryTreeInorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 递归
    public List<Integer> traversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return list;
        }
        if (node.left != null) {
            traversal(node.left, list);
        }
        list.add(node.val);
        if (node.right != null) {
            traversal(node.right, list);
        }
        return list;
    }

//    public static void main(String[] args) {
//        Problem0094BinaryTreeInorderTraversal a = new Problem0094BinaryTreeInorderTraversal();
//        Integer[] array = {1,null,2,3};
//        TreeNode root = a.createTree(array);
//        List<Integer> list = a.traversalStack(root);
//        int i = 1;
//    }

    public TreeNode createTree(Integer[] array) {
        if (array == null) {
            return null;
        }
        if (array.length == 0) {
            return new TreeNode();
        }
        TreeNode root = new TreeNode(array[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 1;
        while (count < array.length) {
            TreeNode node = queue.poll();
            if (node != null) {
                if (array[count] == null) {
                    node.left = null;
                } else {
                    node.left = new TreeNode(array[count]);
                }
                queue.offer(node.left);
                count++;
                if (count >= array.length) {
                    break;
                }
                if (array[count] == null) {
                    node.right = null;
                } else {
                    node.right = new TreeNode(array[count]);
                }
                queue.offer(node.right);
                count++;
            }
        }
        return root;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
//        return traversal(root, new ArrayList<>());
        return traversalStack(root);
    }

    // 栈
    public List<Integer> traversalStack(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode[] stack = new TreeNode[100];
        List<Integer> result = new ArrayList<>();
        int count = 0;
        stack[count++] = root;
        boolean isDown = true;
        while (count > 0) {
            TreeNode top = stack[count - 1];
            if (isDown && top.left == null && top.right == null) {
                result.add(top.val);
                if (count == 1) {
                    break;
                }
                TreeNode parent = stack[count - 2];
                count--;
                if (parent.left == top) {
                    result.add(parent.val);
                    if (parent.right != null) {
                        stack[count++] = parent.right;
                    } else {
                        isDown = false;
                    }
                } else {
                    isDown = false;
                }
            } else if (isDown && top.left != null) {
                stack[count++] = top.left;
            } else if (isDown && top.right != null) {
                result.add(top.val);
                stack[count++] = top.right;
            } else if (!isDown) {
                if (count == 1) {
                    break;
                }
                TreeNode parent = stack[count - 2];
                count--;
                if (parent.left == top) {
                    result.add(parent.val);
                    if (parent.right != null) {
                        stack[count++] = parent.right;
                        isDown = true;
                    }
                }
            }
        }
        return result;
    }

}
