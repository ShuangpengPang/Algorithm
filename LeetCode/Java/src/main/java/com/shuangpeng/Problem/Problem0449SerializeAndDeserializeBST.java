package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0449SerializeAndDeserializeBST {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    builder.append(',').append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                } else {
                    builder.append(",*");
                }
            }
            return builder.deleteCharAt(0).toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] strings = data.split(",");
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(strings[0]));
            queue.offer(root);
            int i = 0;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                String left = strings[++i];
                if (!left.equals("*")) {
                    node.left = new TreeNode(Integer.parseInt(left));
                    queue.offer(node.left);
                }
                String right = strings[++i];
                if (!right.equals("*")) {
                    node.right = new TreeNode(Integer.parseInt(right));
                    queue.offer(node.right);
                }
            }
            return root;
        }
    }
}
