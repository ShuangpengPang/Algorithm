package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem0094BinaryTreeInorderTraversal {

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

    // 栈
    public List<Integer> traversalStack0(TreeNode root) {
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

    // 栈（优化）
    public List<Integer> traversalStack1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode[] stack = new TreeNode[100];
        List<Integer> result = new ArrayList<>();
        int count = 0;
        while (count > 0 || root != null) {
            while (root != null) {
                stack[count++] = root;
                root = root.left;
            }
            root = stack[--count];
            result.add(root.val);
            root = root.right;
        }
        return result;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
//        return traversal(root, new ArrayList<>());
        return traversalStack(root);
    }

    // morris算法
    public List<Integer> traversalStack(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        while (root != null) {
            if (root.left == null) {
                result.add(root.val);
                root = root.right;
            } else {
                TreeNode current = root.left;
                while (current.right != null && current.right != root) {
                    current = current.right;
                }
                if (current.right == null) {
                    current.right = root;
                    root = root.left;
                } else {
                    result.add(root.val);
                    root = root.right;
                }
            }
        }
        return result;
    }

}
