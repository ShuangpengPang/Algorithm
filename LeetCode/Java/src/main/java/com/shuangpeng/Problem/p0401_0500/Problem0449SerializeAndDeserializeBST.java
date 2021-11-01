package com.shuangpeng.Problem.p0401_0500;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problem0449SerializeAndDeserializeBST {

    public static class Codec {

//        public static void main(String[] args) {
//            Codec codec = new Codec();
//            TreeNode node = new TreeNode(2);
//            node.left = new TreeNode(1);
//            node.right = new TreeNode(3);
//            String data = codec.serialize(node);
//            TreeNode t = codec.deserialize(data);
//            int i = 1;
//        }

        // Encodes a tree to a single string.
        public String serialize0(TreeNode root) {
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
        public TreeNode deserialize0(String data) {
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

        public String serialize1(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            preorder0(root, builder);
            return builder.deleteCharAt(builder.length() - 1).toString();
        }

        public TreeNode deserialize1(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] nums = data.split(" ");
            List<Integer> list = new LinkedList<>();
            for (int i = 0; i < nums.length; i++) {
                list.add(Integer.parseInt(nums[i]));
            }
            return build0(list, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private TreeNode build0(List<Integer> list, int min, int max) {
            if (list.isEmpty()) {
                return null;
            }
            int value = list.get(0);
            if (value < min || value > max) {
                return null;
            }
            TreeNode node = new TreeNode(value);
            list.remove(0);
            node.left = build(list, min, value);
            node.right = build(list, value, max);
            return node;
        }

        private void preorder0(TreeNode node, StringBuilder builder) {
            if (node == null) {
                return;
            }
            builder.append(node.val).append(' ');
            preorder(node.left, builder);
            preorder(node.right, builder);
        }

        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder builder = new StringBuilder();
            preorder(root, builder);
            return builder.toString();
        }

        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            List<Integer> list = new LinkedList<>();
            int count = data.length() >> 2;
            for (int i = 0; i < count; i++) {
                list.add(stringToInt(data, i << 2));
            }
            return build(list, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private TreeNode build(List<Integer> list, int min, int max) {
            if (list.isEmpty()) {
                return null;
            }
            int value = list.get(0);
            if (value < min || value > max) {
                return null;
            }
            TreeNode node = new TreeNode(value);
            list.remove(0);
            node.left = build(list, min, value);
            node.right = build(list, value, max);
            return node;
        }

        private void preorder(TreeNode node, StringBuilder builder) {
            if (node == null) {
                return;
            }
            builder.append(intToString(node.val));
            preorder(node.left, builder);
            preorder(node.right, builder);
        }

        private String intToString(int x) {
            char[] bytes = new char[4];
            int n = bytes.length;
            for (int i = 0; i < n; i++) {
                bytes[i] = (char) ((x >> ((n - 1 - i) * 8)) & 0xff);
            }
            return new String(bytes);
        }

        private int stringToInt(String data, int start) {
            int x = 0;
            for (int i = start; i < start + 4; i++) {
                x = (x << 8) + data.charAt(i);
            }
            return x;
        }
    }







    // Encodes a tree to a list.
    public void postorder(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(intToString(root.val));
    }

    // Encodes integer to bytes string
    public String intToString(int x) {
        char[] bytes = new char[4];
        for (int i = 3; i > -1; --i) {
            bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
        }
        return new String(bytes);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postorder(root, sb);
        return sb.toString();
    }

    // Decodes list to tree.
    public TreeNode helper(Integer lower, Integer upper, ArrayDeque<Integer> nums) {
        if (nums.isEmpty()) return null;
        int val = nums.getLast();
        if (val < lower || val > upper) return null;

        nums.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = helper(val, upper, nums);
        root.left = helper(lower, val, nums);
        return root;
    }

    // Decodes bytes string to integer
    public int stringToInt(String bytesStr) {
        int result = 0;
        for (char b : bytesStr.toCharArray()) {
            result = (result << 8) + (int) b;
        }
        return result;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        ArrayDeque<Integer> nums = new ArrayDeque<Integer>();
        int n = data.length();
        for (int i = 0; i < (int) (n / 4); ++i) {
            nums.add(stringToInt(data.substring(4 * i, 4 * i + 4)));
        }

        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
    }
}
