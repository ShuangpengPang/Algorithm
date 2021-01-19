package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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

        private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b -a);
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
