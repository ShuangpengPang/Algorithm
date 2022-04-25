package com.shuangpeng.Problem.p0901_1000;

import java.util.*;

/**
 * @Description: Problem0936StampingTheSequence
 * @Date 2022/4/22 12:02 PM
 * @Version 1.0
 */
public class Problem0936StampingTheSequence {

    class Node {
        Set<Integer> todo;

        Node() {
            todo = new HashSet<>();
        }
    }

    public int[] movesToStamp0(String stamp, String target) {
        int m = stamp.length(), n = target.length();
        Node[] nodes = new Node[n - m + 1];
        boolean[] done = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        Queue<Integer> queue = new LinkedList<>();
        int count = n;
        for (int i = 0; i <= n - m; ++i) {
            nodes[i] = new Node();
            for (int j = i; j < i + m; ++j) {
                if (stamp.charAt(j - i) != target.charAt(j)) {
                    nodes[i].todo.add(j);
                }
            }
            if (nodes[i].todo.isEmpty()) {
                stack.addLast(i);
                for (int j = i; j < i + m; ++j) {
                    if (!done[j]) {
                        done[j] = true;
                        --count;
                        queue.add(j);
                    }
                }
            }
        }
        while (count > 0 && !queue.isEmpty()) {
            int i = queue.poll();
            for (int j = Math.max(0, i - m + 1); j <= Math.min(i, n - m); ++j) {
                if (nodes[j].todo.contains(i)) {
                    nodes[j].todo.remove(i);
                    if (nodes[j].todo.isEmpty()) {
                        stack.add(j);
                        for (int k = j; k < Math.min(n, j + m); ++k) {
                            if (!done[k]) {
                                --count;
                                done[k] = true;
                                queue.add(k);
                            }
                        }
                    }
                }
            }
        }
        if (count > 0) {
            return new int[0];
        }
        int len = stack.size();
        int[] ans = new int[len];
        for (int i = 0; i < len; ++i) {
            ans[i] = stack.pollLast();
        }
        return ans;
    }

    public int[] movesToStamp(String stamp, String target) {
        int m = stamp.length(), n = target.length();
        List<Integer> indices = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        char[] chars = target.toCharArray();
        for (int i = 0; i <= n - m; ++i) {
            int j = 0;
            while (j < m && (stamp.charAt(j) == chars[i + j] || chars[i + j] == '?')) {
                ++j;
            }
            if (j < m && j > 0 && chars[i + j - 1] != '?') {
                stack.addLast((i << 16) | (i + j));
            } else if (j == m) {
                indices.add(i);
                setQuestionMark(chars, i, i + j - 1);
                int start = i;
                while (!stack.isEmpty()) {
                    int value = stack.pollLast();
                    int idx1 = (value >> 16) & 0xffff, idx2 = value & 0xffff;
                    if (start <= idx2) {
                        indices.add(idx1);
                        setQuestionMark(chars, idx1, start - 1);
                        start = idx1;
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            if (chars[i] != '?') {
                return new int[0];
            }
        }
        int size = indices.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; ++i) {
            ans[i] = indices.get(size - i - 1);
        }
        return ans;
    }

    private void setQuestionMark(char[] chars, int s, int e) {
        for (int i = s; i <= e; ++i) {
            chars[i] = '?';
        }
    }
}
