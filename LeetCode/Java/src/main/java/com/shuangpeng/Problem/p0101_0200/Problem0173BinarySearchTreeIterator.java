package com.shuangpeng.Problem.p0101_0200;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem0173BinarySearchTreeIterator {

    class BSTIterator0 {

        List<Integer> list;
        int index = 0;

        public BSTIterator0(TreeNode root) {
            list = new ArrayList<>();
            inorder(root);
        }

        private void inorder(TreeNode node) {
            if (node == null) {
                return;
            }
            inorder(node.left);
            list.add(node.val);
            inorder(node.right);
        }

        public int next() {
            return list.get(index++);
        }

        public boolean hasNext() {
            return index < list.size();
        }
    }

    class BSTIterator {

        Deque<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            stack = new LinkedList<>();
            pushLeft(root);
        }

        private void pushLeft(TreeNode node) {
            while (node != null) {
                stack.offerLast(node);
                node = node.left;
            }
        }

        public int next() {
            TreeNode node = stack.pollLast();
            pushLeft(node.right);
            return node.val;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }


//    class BSTIterator {
//
//        Stack<TreeNode> stack;
//
//        public BSTIterator(TreeNode root) {
//
//            // Stack for the recursion simulation
//            this.stack = new Stack<TreeNode>();
//
//            // Remember that the algorithm starts with a call to the helper function
//            // with the root node as the input
//            this._leftmostInorder(root);
//        }
//
//        private void _leftmostInorder(TreeNode root) {
//
//            // For a given node, add all the elements in the leftmost branch of the tree
//            // under it to the stack.
//            while (root != null) {
//                this.stack.push(root);
//                root = root.left;
//            }
//        }
//
//        /**
//         * @return the next smallest number
//         */
//        public int next() {
//            // Node at the top of the stack is the next smallest element
//            TreeNode topmostNode = this.stack.pop();
//
//            // Need to maintain the invariant. If the node has a right child, call the
//            // helper function for the right child
//            if (topmostNode.right != null) {
//                this._leftmostInorder(topmostNode.right);
//            }
//
//            return topmostNode.val;
//        }
//
//        /**
//         * @return whether we have a next smallest number
//         */
//        public boolean hasNext() {
//            return this.stack.size() > 0;
//        }
//    }
}
