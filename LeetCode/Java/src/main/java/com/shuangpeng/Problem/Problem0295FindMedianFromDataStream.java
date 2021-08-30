package com.shuangpeng.Problem;

import java.util.*;

public class Problem0295FindMedianFromDataStream {

//    class MedianFinder {
//
//        private List<Integer> list = new ArrayList<>();
//
//        /**
//         * initialize your data structure here.
//         */
//        public MedianFinder() {
//        }
//
//        public void addNum(int num) {
//            int left = 0;
//            int right = list.size() - 1;
//            while (left <= right) {
//                int mid = (left + right) >>> 1;
//                int data = list.get(mid);
//                if (data < num) {
//                    left = mid + 1;
//                } else if (data > num) {
//                    right = mid - 1;
//                } else {
//                    left = mid + 1;
//                }
//            }
//            list.add(left, num);
//        }
//
//        public double findMedian() {
//            int length = list.size();
//            int mid = length / 2;
//            if ((length & 1) == 1) {
//                return list.get(mid);
//            } else {
//                return ((double) (list.get(mid) + list.get(mid - 1))) / 2;
//            }
//        }
//    }

    class MedianFinder {

        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        private PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a));

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
        }

        public void addNum(int num) {
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
            if (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            int maxSize = maxHeap.size();
            int minSize = minHeap.size();
            if (maxSize == minSize) {
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            }
            return maxHeap.peek();
        }

        public void addNum0(int num) {
            if (maxHeap.isEmpty()) {
                maxHeap.offer(num);
                return;
            }
            int maxTop = maxHeap.peek();
            int maxSize = maxHeap.size();
            int minSize = minHeap.size();
            if (maxSize > minSize && num < maxTop) {
                maxHeap.poll();
                maxHeap.offer(num);
                minHeap.offer(maxTop);
            } else if (maxSize > minSize && num >= maxTop) {
                minHeap.offer(num);
            } else if (num <= minHeap.peek()) {
                maxHeap.offer(num);
            } else {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            }
        }
    }
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
}

class MedianFinder0 {

    static class Node {
        int left;
        int right;
        Node leftNode;
        Node rightNode;
        int count;

        Node(int left, int right) {
            this.left = left;
            this.right = right;
        }

        int getMid() {
            return left + ((right - left) >> 1);
        }
    }

    public static int find(Node root, int num) {
        if (root == null || num < root.left) {
            return 0;
        }
        if (num >= root.right) {
            return root.count;
        }
        if (num <= root.getMid()) {
            return find(root.leftNode, num);
        }
        return (root.leftNode == null ? 0 : root.leftNode.count) + find(root.rightNode, num);
    }

    public static void update(Node root, int num) {
        if (root.left == root.right) {
            root.count++;
            return;
        }
        int mid = root.getMid();
        if (num <= mid) {
            if (root.leftNode == null) {
                root.leftNode = new Node(root.left, mid);
            }
            update(root.leftNode, num);
        } else {
            if (root.rightNode == null) {
                root.rightNode = new Node(mid + 1, root.right);
            }
            update(root.rightNode, num);
        }
        root.count++;
    }

    Node root;
    final int M = (int) 1e5;

    /**
     * initialize your data structure here.
     */
    public MedianFinder0() {
        root = new Node(-M, M);
    }

    public void addNum(int num) {
        update(root, num);
    }

    public double findMedian() {
        if ((root.count & 1) == 1) {
            return binarySearch(true);
        }
        return (binarySearch(true) + binarySearch(false)) / 2.0;
    }

    private int binarySearch(boolean flag) {
        int target = (root.count + 1) >> 1;
        if (!flag) {
            target++;
        }
        int left = -M, right = M;
        int c = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            c = find(root, mid);
            if (target > c) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

class MedianFinder1 {

    /**
     * initialize your data structure here.
     */
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public MedianFinder1() {
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a));
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || num < maxHeap.peek()) {
            maxHeap.offer(num);
        } else if (minHeap.isEmpty() || num > minHeap.peek()) {
            minHeap.offer(num);
        } else if (maxHeap.size() == minHeap.size()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        } else if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() != minHeap.size()) {
            return maxHeap.peek();
        }
        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }
}

class MedianFinder2 {
    TreeMap<Integer, Integer> nums;
    int n;
    int[] left;
    int[] right;

    public MedianFinder2() {
        nums = new TreeMap<Integer, Integer>();
        n = 0;
        left = new int[2];
        right = new int[2];
    }

    public void addNum(int num) {
        nums.put(num, nums.getOrDefault(num, 0) + 1);
        if (n == 0) {
            left[0] = right[0] = num;
            left[1] = right[1] = 1;
        } else if ((n & 1) != 0) {
            if (num < left[0]) {
                decrease(left);
            } else {
                increase(right);
            }
        } else {
            if (num > left[0] && num < right[0]) {
                increase(left);
                decrease(right);
            } else if (num >= right[0]) {
                increase(left);
            } else {
                decrease(right);
                System.arraycopy(right, 0, left, 0, 2);
            }
        }
        n++;
    }

    public double findMedian() {
        return (left[0] + right[0]) / 2.0;
    }

    private void increase(int[] iterator) {
        iterator[1]++;
        if (iterator[1] > nums.get(iterator[0])) {
            iterator[0] = nums.ceilingKey(iterator[0] + 1);
            iterator[1] = 1;
        }
    }

    private void decrease(int[] iterator) {
        iterator[1]--;
        if (iterator[1] == 0) {
            iterator[0] = nums.floorKey(iterator[0] - 1);
            iterator[1] = nums.get(iterator[0]);
        }
    }
}

class MedianFinder {

    /**
     * initialize your data structure here.
     */
    int[] left, right;
    TreeMap<Integer, Integer> map;

    public MedianFinder() {
        left = new int[2];
        right = new int[2];
        map = new TreeMap<>();
    }

    public void addNum(int num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
        if (left[1] == 0) {
            left[0] = num;
            right[0] = num;
            left[1] = 1;
            right[1] = 1;
        } else if (left[0] == right[0] && left[1] == right[1]) {
            if (num < left[0]) {
                decrease(left);
            } else {
                increase(right);
            }
        } else {
            if (num >= left[0]) {
                increase(left);
            }
            if (left[0] != right[0] || left[1] != right[1]) {
                decrease(right);
            }
        }
    }

    private void decrease(int[] iterator) {
        iterator[1]--;
        if (iterator[1] == 0) {
            iterator[0] = map.floorKey(iterator[0] - 1);
            iterator[1] = map.get(iterator[0]);
        }
    }

    private void increase(int[] iterator) {
        iterator[1]++;
        if (iterator[1] > map.get(iterator[0])) {
            iterator[0] = map.ceilingKey(iterator[0] + 1);
            iterator[1] = 1;
        }
    }

    public double findMedian() {
        return (left[0] + right[0]) / 2.0;
    }
}


