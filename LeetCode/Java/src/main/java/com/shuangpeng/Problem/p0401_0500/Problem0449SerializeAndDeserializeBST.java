package com.shuangpeng.Problem.p0401_0500;

import com.shuangpeng.common.TreeNode;

import java.util.*;

/***
 * @Description: 序列化和反序列化二叉搜索树（449）
 * @Date 2022/5/11 3:47 PM
 **/
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

class Problem0449SerializeAndDeserializeBST0 {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            return dfs(root).toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            return parse(data.toCharArray(), 0, data.length());
        }

        private StringBuilder dfs(TreeNode root) {
            if (root == null) {
                return new StringBuilder();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(root.val)
                    .append('(').append(dfs(root.left)).append(')')
                    .append('(').append(dfs(root.right)).append(')');
            return sb;
        }

        private TreeNode parse(char[] chars, int s, int e) {
            if (s == e) {
                return null;
            }
            int val = 0;
            int i = s;
            while (chars[i] != '(') {
                val = val * 10 + chars[i] - '0';
                ++i;
            }
            int j = i;
            int count = 1;
            while (count != 0) {
                ++j;
                if (chars[j] == '(') {
                    ++count;
                } else if (chars[j] == ')') {
                    --count;
                }
            }
            TreeNode root = new TreeNode(val);
            root.left = parse(chars, i + 1, j);
            root.right = parse(chars, j + 2, e - 1);
            return root;
        }
    }
}

class Problem0449SerializeAndDeserializeBST1 {

    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                sb.append(',');
                TreeNode node = queue.poll();
                if (node != null) {
                    sb.append(node.val);
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            return sb.substring(1);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            char[] chars = data.toCharArray();
            int n = chars.length;
            int i = 0;
            int val = 0;
            while (chars[i] != ',') {
                val = val * 10 + chars[i] - '0';
                ++i;
            }
            TreeNode root = new TreeNode(val);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                ++i;
                if (chars[i] != ',') {
                    int num = 0;
                    while (chars[i] != ',') {
                        num = num * 10 + chars[i] - '0';
                        ++i;
                    }
                    node.left = new TreeNode(num);
                    queue.offer(node.left);
                }
                ++i;
                if (i < n && chars[i] != ',') {
                    int num = 0;
                    while (i < n && chars[i] != ',') {
                        num = num * 10 + chars[i] - '0';
                        ++i;
                    }
                    node.right = new TreeNode(num);
                    queue.offer(node.right);
                }
            }
            return root;
        }
    }
}

class Problem0449SerializeAndDeserializeBST2 {
    public class Codec {
        public String serialize(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            postOrder(root, list);
            String s = list.toString();
            return s.substring(1, s.length() - 1);
        }

        private void postOrder(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            postOrder(root.left, list);
            postOrder(root.right, list);
            list.add(root.val);
        }

        public TreeNode deserialize(String data) {
            if (data.isEmpty()) {
                return null;
            }
            String[] strs = data.split(", ");
            Deque<Integer> deque = new ArrayDeque<>();
            for (String s : strs) {
                deque.addLast(Integer.parseInt(s));
            }
            return construct(deque, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private TreeNode construct(Deque<Integer> deque, int low, int up) {
            if (deque.isEmpty() || deque.peekLast() < low || deque.peekLast() > up) {
                return null;
            }
            int val = deque.pollLast();
            TreeNode node = new TreeNode(val);
            node.right = construct(deque, val, up);
            node.left = construct(deque, low, val);
            return node;
        }
    }
}

class Problem0449SerializeAndDeserializeBST3 {

    public class Codec {

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            postOrder(root, sb);
            return sb.toString();
        }

        public TreeNode deserialize(String data) {
            return construct(data.toCharArray(), new int[]{data.length() - 1}, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        private void postOrder(TreeNode root, StringBuilder sb) {
            if (root == null) {
                return;
            }
            postOrder(root.left, sb);
            postOrder(root.right, sb);
            sb.append((char) root.val);
        }

        private TreeNode construct(char[] chars, int[] idx, int low, int up) {
            int i = idx[0];
            if (i < 0 || chars[i] < low || chars[i] > up) {
                return null;
            }
            int val = chars[i];
            --idx[0];
            TreeNode node = new TreeNode(val);
            node.right = construct(chars, idx, val, up);
            node.left = construct(chars, idx, low, val);
            return node;
        }
    }
}
