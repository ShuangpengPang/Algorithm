package com.shuangpeng.Problem.p1001_1100;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Description: Problem1028RecoverATreeFromPreorderTraversal（从先序遍历还原二叉树）
 * @Date 2022/5/13 5:48 PM
 * @Version 1.0
 */
public class Problem1028RecoverATreeFromPreorderTraversal {

    public TreeNode recoverFromPreorder0(String traversal) {
        char[] chars = traversal.toCharArray();
        int n = chars.length;
        int[] first = parseValue(chars, 0);
        TreeNode root = new TreeNode(first[0]);
        int idx = first[1];
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offerLast(root);
        int prevDash = 0;
        while (idx < n) {
            int prev = idx;
            while (chars[idx] == '-') {
                ++idx;
            }
            int[] pair = parseValue(chars, idx);
            TreeNode node = new TreeNode(pair[0]);
            int dash = idx - prev;
            if (dash > prevDash) {
                deque.peekLast().left = node;
                deque.offerLast(node);
            } else {
                int count = prevDash - dash + 1;
                while (count > 0) {
                    deque.pollLast();
                    --count;
                }
                deque.peekLast().right = node;
                deque.offerLast(node);
            }
            prevDash = dash;
            idx = pair[1];
        }
        return root;
    }

    private int[] parseValue(char[] chars, int i) {
        int value = 0;
        int n = chars.length;
        while (i < n && chars[i] != '-') {
            value = value * 10 + chars[i] - '0';
            ++i;
        }
        return new int[]{value, i};
    }

    public TreeNode recoverFromPreorder(String traversal) {
        int n = traversal.length();
        Deque<TreeNode> deque = new ArrayDeque<>();
        int pos = 0, level = 0;
        while (pos < n) {
            level = 0;
            while (traversal.charAt(pos) == '-') {
                ++pos;
                ++level;
            }
            int value = 0;
            while (pos < n && traversal.charAt(pos) != '-') {
                value = value * 10 + traversal.charAt(pos) - '0';
                ++pos;
            }
            TreeNode node = new TreeNode(value);
            if (level == deque.size()) {
                if (!deque.isEmpty()) {
                    deque.peekLast().left = node;
                }
            } else {
                while (level != deque.size()) {
                    deque.pollLast();
                }
                deque.peekLast().right = node;
            }
            deque.offerLast(node);
        }
        return deque.peekFirst();
    }
}
