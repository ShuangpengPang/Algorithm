package com.shuangpeng.competition.第251到260场周赛.第253场周赛;

import java.util.ArrayList;
import java.util.List;

public class Problem1964 {

    class Node {
        int left;
        int right;
        int count;
        Node leftNode;
        Node rightNode;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
            this.count = 0;
        }

        public int getMid() {
            return left + ((right - left) >> 1);
        }
    }

    private int findNode(Node root, int value, int count) {
        if (root == null) {
            return count;
        }
        if (root.left == root.right) {
            return Math.max(count, root.count);
        }
        int mid = root.getMid();
        if (value <= mid) {
            return findNode(root.leftNode, value, count);
        }
        int left = root.leftNode == null ? 0 : root.leftNode.count;
        return findNode(root.rightNode, value, Math.max(count, left));
    }

    private int updateNode(Node root, int value, int count) {
        if (root.left == root.right) {
            root.count = Math.max(root.count, count) + 1;
            return root.count;
        }
        int mid = root.getMid();
        if (root.leftNode == null) {
            root.leftNode = new Node(root.left, mid);
        }
        if (root.rightNode == null) {
            root.rightNode = new Node(mid + 1, root.right);
        }
        int length = 0;
        if (mid >= value) {
            length = updateNode(root.leftNode, value, count);
        } else {
            length = updateNode(root.rightNode, value, Math.max(count, root.leftNode.count));
        }
        root.count = Math.max(root.count, length);
        return length;
    }

    public int[] longestObstacleCourseAtEachPosition0(int[] obstacles) {
        int n = obstacles.length;
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            minValue = Math.min(minValue, obstacles[i]);
            maxValue = Math.max(maxValue, obstacles[i]);
        }
        Node root = new Node(minValue, maxValue);
        int[] answer = new int[n];
        for (int i = 0; i < n; ++i) {
            int value = obstacles[i];
            int count = findNode(root, value, 0) + 1;
            updateNode(root, value, 0);
            answer[i] = count;
        }
        return answer;
    }

    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        List<Integer> stack = new ArrayList<>();
        int n = obstacles.length;
        int[] answer = new int[n];
        answer[0] = 1;
        stack.add(obstacles[0]);
        for (int i = 1; i < n; ++i) {
            if (obstacles[i] >= stack.get(stack.size() - 1)) {
                stack.add(obstacles[i]);
                answer[i] = stack.size();
            } else {
                int left = 0, right = stack.size() - 1;
                while (left <= right) {
                    int mid = left + ((right - left) >> 1);
                    if (obstacles[i] >= stack.get(mid)) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                answer[i] = left + 1;
                stack.set(left, obstacles[i]);
            }
        }
        return answer;
    }

//    public static void main(String[] args) {
//        Problem1964 a = new Problem1964();
////        int[] nums = {3, 1, 5, 6, 4, 2};
//        int[] nums = {5, 1, 5, 5, 1, 3, 4, 5, 1, 4};
//        a.longestObstacleCourseAtEachPosition(nums);
//    }
}
