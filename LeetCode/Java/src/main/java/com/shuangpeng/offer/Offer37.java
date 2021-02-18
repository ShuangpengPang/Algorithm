package com.shuangpeng.offer;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Offer37 {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
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
        public TreeNode deserialize(String data) {
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
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
}
