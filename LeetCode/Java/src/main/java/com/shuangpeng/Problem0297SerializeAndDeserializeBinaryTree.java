package com.shuangpeng;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0297SerializeAndDeserializeBinaryTree {

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

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    builder.append("* ");
                    continue;
                }
                builder.append("" + node.val + ' ');
                queue.offer(node.left);
                queue.offer(node.right);
            }
            return builder.toString().trim();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] strings = data.split(" ");
            String s = strings[0];
            if (s.equals("*")) {
                return null;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(s));
            queue.offer(root);
            int i = 0;
            while (!queue.isEmpty()) {
                TreeNode treeNode = queue.poll();
                String left = strings[i + 1];
                String right = strings[i + 2];
                if (left.equals("*")) {
                    treeNode.left = null;
                } else {
                    TreeNode node = new TreeNode(Integer.parseInt(left));
                    treeNode.left = node;
                    queue.offer(node);
                }
                if (right.equals("*")) {
                    treeNode.right = null;
                } else {
                    TreeNode node = new TreeNode(Integer.parseInt(right));
                    treeNode.right = node;
                    queue.offer(node);
                }
                i += 2;
            }
            return root;
        }
    }
}
