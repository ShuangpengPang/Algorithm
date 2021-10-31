package com.shuangpeng.Problem.p0201_0300;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0297SerializeAndDeserializeBinaryTree {

    public class Codec {

        // 广度优先
        // Encodes a tree to a single string.
        public String serialize0(TreeNode root) {
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
        public TreeNode deserialize0(String data) {
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



        // Encodes a tree to a single string.
        public String serialize1(TreeNode root) {
            return dfsSerialize(root);
        }

        public String dfsSerialize(TreeNode treeNode) {
            if (treeNode == null) {
                return "*";
            }
            return "" + treeNode.val + ',' + dfsSerialize(treeNode.left) + ',' + dfsSerialize(treeNode.right);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize1(String data) {
            String[] strings = data.split(",");
            return dfsDeserialize(strings);
        }

        int i = 0;
        public TreeNode dfsDeserialize(String[] strings) {
            String s = strings[i];
            i++;
            if (s.equals("*")) {
                return null;
            }
            TreeNode treeNode = new TreeNode(Integer.parseInt(s));
            treeNode.left = dfsDeserialize(strings);
            treeNode.right = dfsDeserialize(strings);
            return treeNode;
        }


        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "/";
            }
            return '(' + serialize(root.left) + ')' + root.val + '(' + serialize(root.right) + ')';
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            int[] p = {0};
            return deserializeParenthese(data, p);
        }

        public TreeNode deserializeParenthese(String data, int[] p) {
            if (data.charAt(p[0]) == '/') {
                return null;
            }
            p[0]++;
            TreeNode left = deserializeParenthese(data, p);
            p[0] += 2;
            StringBuilder builder = new StringBuilder();
            while (data.charAt(p[0]) != '(') {
                builder.append(data.charAt(p[0]));
                p[0]++;
            }
            p[0]++;
            TreeNode right = deserializeParenthese(data, p);
            p[0]++;
            TreeNode node = new TreeNode(Integer.parseInt(builder.toString()));
            node.left = left;
            node.right = right;
            return node;
        }
    }


//    public TreeNode deserialize(String data) {
//        return deserializeParenthese(data, 0);
//    }
//
//    int p = 0;
//    public TreeNode deserializeParenthese(String data, int i) {
//        if (data.charAt(i) == '/') {
//            return null;
//        }
//        p++;
//        TreeNode left = deserializeParenthese(data, i + 1);
//        p = p + 2;
//        StringBuilder builder = new StringBuilder();
//        while (data.charAt(p) != '(') {
//            builder.append(data.charAt(p));
//            p++;
//        }
//        TreeNode right = deserializeParenthese(data, p);
//        TreeNode node = new TreeNode(Integer.parseInt(builder.toString()));
//        node.left = left;
//        node.right = right;
//        return node;
//    }
//
//    public static void main(String[] args) {
//        Problem0297SerializeAndDeserializeBinaryTree a = new Problem0297SerializeAndDeserializeBinaryTree();
//        a.deserialize("((/)2(/))1(((/)4(/))3((/)5(/)))");
//    }
}
