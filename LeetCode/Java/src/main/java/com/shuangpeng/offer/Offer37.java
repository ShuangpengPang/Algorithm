package com.shuangpeng.offer;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class Offer37 {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize0(TreeNode root) {
            StringBuilder builder = new StringBuilder();
            builder.append('[');
            Queue<TreeNode> queue = new LinkedList<>();
            if (root != null) {
                queue.offer(root);
                builder.append(root.val);
            }
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                builder.append(',');
                if (node.left != null) {
                    builder.append(node.left.val);
                    queue.offer(node.left);
                } else {
                    builder.append("null");
                }
                builder.append(',');
                if (node.right != null) {
                    builder.append(node.right.val);
                    queue.offer(node.right);
                } else {
                    builder.append("null");
                }
            }
            builder.append(']');
            return builder.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize0(String data) {
            if (data.equals("[]")) {
                return null;
            }
            data = data.replace("[", "").replace("]", "");
            String[] strings = data.split(",");
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(strings[0]));
            queue.offer(root);
            int i = 0;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                String left = strings[++i];
                String right = strings[++i];
                if (left.equals("null")) {
                    node.left = null;
                } else {
                    TreeNode leftNode = new TreeNode(Integer.parseInt(left));
                    node.left = leftNode;
                    queue.offer(leftNode);
                }
                if (right.equals("null")) {
                    node.right = null;
                } else {
                    TreeNode rightNode = new TreeNode(Integer.parseInt(right));
                    node.right = rightNode;
                    queue.offer(rightNode);
                }
            }
            return root;
        }

        // Encodes a tree to a single string.
        public String serialize1(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            builder.append(root.val);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                builder.append(',');
                if (node.left != null) {
                    builder.append(node.left.val);
                    queue.offer(node.left);
                }
                builder.append(',');
                if (node.right != null) {
                    builder.append(node.right.val);
                    queue.offer(node.right);
                }
            }
            return builder.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize1(String data) {
            if (data.isEmpty()) {
                return null;
            }
            int n = data.length();
            int i = data.indexOf(',');
            TreeNode root = new TreeNode(Integer.parseInt(data.substring(0, i)));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                int j = i + 1;
                i = data.indexOf(',', j);
                String left = data.substring(j, i);
                if (!left.isEmpty()) {
                    TreeNode leftNode = new TreeNode(Integer.parseInt(left));
                    node.left = leftNode;
                    queue.offer(leftNode);
                }
                j = i + 1;
                if (j == n) {
                    break;
                }
                i = data.indexOf(',', j);
                String right = data.substring(j, i);
                if (!right.isEmpty()) {
                    TreeNode rightNode = new TreeNode(Integer.parseInt(right));
                    node.right = rightNode;
                    queue.offer(rightNode);
                }
            }
            return root;
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "x";
            }
            return "(" + serialize(root.left) + ")" + root.val + "(" + serialize(root.right) + ")";
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return parse(data, new int[1]);
        }

        private TreeNode parse(String data, int[] pIndex) {
            if (data.charAt(pIndex[0]++) == 'x') {
                return null;
            }
            TreeNode left = parse(data, pIndex);
            pIndex[0]++;
            StringBuilder builder = new StringBuilder();
            while (data.charAt(pIndex[0]) == '-' || Character.isDigit(data.charAt(pIndex[0]))) {
                builder.append(data.charAt(pIndex[0]));
                pIndex[0]++;
            }
            pIndex[0]++;
            TreeNode right = parse(data, pIndex);
            pIndex[0]++;
            TreeNode root = new TreeNode(Integer.parseInt(builder.toString()));
            root.left = left;
            root.right = right;
            return root;
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
}
