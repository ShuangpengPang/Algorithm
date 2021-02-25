package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem1008ConstructBinarySearchTreeFromPreorderTraversal {

    public TreeNode bstFromPreorder0(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return recurse(preorder, 0, preorder.length);
    }

    private TreeNode recurse(int[] preorder, int start, int end) {
        if (start >= end) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[start]);
        int i = start + 1;
        while (i < end) {
            if (preorder[i] > node.val) {
                break;
            }
            i++;
        }
        node.left = recurse(preorder, start + 1, i);
        node.right = recurse(preorder, i, end);
        return node;
    }

    private int preIndex = 0;

    public TreeNode bstFromPreorder1(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        int[] inorder = Arrays.copyOf(preorder, preorder.length);
        Arrays.sort(inorder);
        Map<Integer, Integer> indexMap = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return build(preorder, inorder, 0, inorder.length, indexMap);
    }

    private TreeNode build(int[] preorder, int[] inorder, int s, int e,
                           Map<Integer, Integer> indexMap) {
        if (s >= e) {
            return null;
        }
        int value = preorder[preIndex++];
        TreeNode node = new TreeNode(value);
        int index = indexMap.get(value);
        node.left = build(preorder, inorder, s, index, indexMap);
        node.right = build(preorder, inorder, index + 1, e, indexMap);
        return node;
    }


    private int curIndex = 0;

    public TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return buildRecurse(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode buildRecurse(int[] preorder, int min, int max) {
        if (curIndex >= preorder.length) {
            return null;
        }
        int value = preorder[curIndex];
        if (value < min || value > max) {
            return null;
        }
        curIndex++;
        TreeNode node = new TreeNode(value);
        node.left = buildRecurse(preorder, min, value);
        node.right = buildRecurse(preorder, value, max);
        return node;
    }

    public TreeNode bstFromPreorder3(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        int i = 0;
        TreeNode root = new TreeNode(preorder[i++]);
        stack.addLast(root);
        while (i < preorder.length) {
            TreeNode parent = stack.getLast();
            int value = preorder[i++];
            TreeNode node = new TreeNode(value);
            if (value < parent.val) {
                parent.left = node;
                stack.addLast(node);
            } else {
                TreeNode current = parent;
                while (current.val <= value) {
                    parent = current;
                    stack.removeLast();
                    if (!stack.isEmpty()) {
                        current = stack.getLast();
                    } else {
                        break;
                    }
                }
                parent.right = node;
                stack.addLast(node);
            }
        }
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>(preorder.length);
        TreeNode root = new TreeNode(preorder[0]);
        stack.offerLast(root);
        for (int i = 1; i < preorder.length; i++) {
            TreeNode child = new TreeNode(preorder[i]);
            TreeNode parent = stack.peekLast();
            while (!stack.isEmpty() && stack.peekLast().val < child.val) {
                parent = stack.pollLast();
            }
            if (child.val < parent.val) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            stack.offerLast(child);
        }
        return root;
    }

    class Solution0 {
        public TreeNode bstFromPreorder(int[] preorder) {
            int n = preorder.length;
            if (n == 0) return null;

            TreeNode root = new TreeNode(preorder[0]);
            Deque<TreeNode> deque = new ArrayDeque<TreeNode>();
            deque.push(root);

            for (int i = 1; i < n; i++) {
                // take the last element of the deque as a parent
                // and create a child from the next preorder element
                TreeNode node = deque.peek();
                TreeNode child = new TreeNode(preorder[i]);
                // adjust the parent
                while (!deque.isEmpty() && deque.peek().val < child.val)
                    node = deque.pop();

                // follow BST logic to create a parent-child link
                if (node.val < child.val) node.right = child;
                else node.left = child;
                // add the child into deque
                deque.push(child);
            }
            return root;
        }
    }

    class Solution {
        // start from first preorder element
        int pre_idx = 0;
        int[] preorder;
        HashMap<Integer, Integer> idx_map = new HashMap<Integer, Integer>();

        public TreeNode helper(int in_left, int in_right) {
            // if there is no elements to construct subtrees
            if (in_left == in_right)
                return null;

            // pick up pre_idx element as a root
            int root_val = preorder[pre_idx];
            TreeNode root = new TreeNode(root_val);

            // root splits inorder list
            // into left and right subtrees
            int index = idx_map.get(root_val);

            // recursion
            pre_idx++;
            // build left subtree
            root.left = helper(in_left, index);
            // build right subtree
            root.right = helper(index + 1, in_right);
            return root;
        }

        public TreeNode bstFromPreorder(int[] preorder) {
            this.preorder = preorder;
            int [] inorder = Arrays.copyOf(preorder, preorder.length);
            Arrays.sort(inorder);

            // build a hashmap value -> its index
            int idx = 0;
            for (Integer val : inorder)
                idx_map.put(val, idx++);
            return helper(0, inorder.length);
        }
    }
}
