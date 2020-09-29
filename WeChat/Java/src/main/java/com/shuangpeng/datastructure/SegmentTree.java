package com.shuangpeng.datastructure;

public class SegmentTree {
    Node root;

    class Node {
        int data;
        int start;
        int end;
        Node left;
        Node right;
    }

    public static void main(String[] args) {
        int[] data = {5,2,6,1,-4,0,9,2};
        SegmentTree segmentTree = new SegmentTree();
        segmentTree.root = segmentTree.buildSegmentTree(data, 0, data.length);
//        segmentTree.initSegmentTreeWithSum(segmentTree.root, data);
//        System.err.println("sum is: " + segmentTree.findSum(segmentTree.root, 6, 9));
        segmentTree.initSegmentTreeWithMax(segmentTree.root, data);
        System.err.println("max is: " + segmentTree.findMax(segmentTree.root, 0, 0));
    }

    public int findSum(Node node, int start, int initEnd) {
        if (node == null) {
            return 0;
        }
        if (start > initEnd) {
            return 0;
        }
        int end = initEnd + 1;
        int s = node.start;
        int e = node.end;

        // 不相交
        if (end <= s) {
            return 0;
        }
        if (start >= e) {
            return 0;
        }

        // 目标区间包含当前Node区间
        if (start <= s && end >= e) {
            return node.data;
        }

        // 相交
        return findSum(node.left, start, initEnd) + findSum(node.right, start, initEnd);

//        // 左部分相交
//        if (start <= s && end < e) {
//            Node left = node.left;
//            int leftEnd = left.end;
//            if (end <= leftEnd) {
//                return findSum(left, start, initEnd);
//            } else {
//                return left.data + findSum(node.right, start, initEnd);
//            }
//        }
//
//        // 当前Node区间包含目标区间
//        if (s < start && end < e) {
//            int leftSum = findSum(node.left, start, initEnd);
//            int rightSum = findSum(node.right, start, initEnd);
//            return leftSum + rightSum;
//        }
    }

    public int findMax(Node node, int start, int initEnd) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }
        if (start > initEnd) {
            return Integer.MIN_VALUE;
        }
        int end = initEnd + 1;
        int s = node.start;
        int e = node.end;
        if (end <= s || start >= e) {
            return Integer.MIN_VALUE;
        }
        if (start <= s && end >= e) {
            return node.data;
        }
        return Math.max(findMax(node.left, start, initEnd), findMax(node.right, start, initEnd));
    }

    public int initSegmentTreeWithMax(Node node, int[] data) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            node.data = data[node.start];
            return node.data;
        }
        node.data = Math.max(initSegmentTreeWithMax(node.left, data), initSegmentTreeWithMax(node.right, data));
        return node.data;
    }

    public int initSegmentTreeWithSum(Node node, int[] data) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            node.data = data[node.start];
            return node.data;
        }
        int sum = 0;
        if (node.left != null) {
            sum += initSegmentTreeWithSum(node.left, data);
        }
        if (node.right != null) {
            sum += initSegmentTreeWithSum(node.right, data);
        }
        node.data = sum;
        return sum;
    }

    public Node buildSegmentTree(int[] data, int start, int end) {
        if (data == null || data.length == 0) {
            return null;
        }
        if (start >= end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        Node left = null;
        Node right = null;
        if (start < mid) {
            left = buildSegmentTree(data, start, mid);
            right = buildSegmentTree(data, mid, end);
        }
        Node node = new Node();
        node.start = start;
        node.end = end;
        node.left = left;
        node.right = right;
        return node;
    }
}
