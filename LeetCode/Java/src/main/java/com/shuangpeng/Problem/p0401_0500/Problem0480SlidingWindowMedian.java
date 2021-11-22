package com.shuangpeng.Problem.p0401_0500;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Problem0480SlidingWindowMedian {

    public double[] medianSlidingWindow0(int[] nums, int k) {
        int leftSize = (k + 1) >> 1;
        int rightSize = k - leftSize;
        PriorityQueue<int[]> left = new PriorityQueue<>((a, b) -> {
            if (b[1] != a[1]) {
                return b[1] > a[1] ? 1 : -1;
            }
            return a[0] - b[0];
        });
        PriorityQueue<int[]> right = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return a[1] > b[1] ? 1 : -1;
            }
            return a[0] - b[0];
        });
        int n = nums.length;
        int leftCount = 0, rightCount = 0;
        boolean[] inLeft = new boolean[n];
        double[] ans = new double[n - k + 1];
        boolean isOdd = (k & 1) == 1;
        for (int i = 0; i < n; ++i) {
            int[] pair = new int[]{i, nums[i]};
            if (leftCount < leftSize) {
                left.offer(pair);
                ++leftCount;
                inLeft[i] = true;
            } else if (rightCount < rightSize) {
                left.offer(pair);
                inLeft[i] = true;
                int[] temp = left.poll();
                inLeft[temp[0]] = false;
                right.offer(temp);
                ++rightCount;
            } else {
                while (!left.isEmpty() && left.peek()[0] <= i - k) {
                    left.poll();
                }
                while (!right.isEmpty() && right.peek()[0] <= i - k) {
                    right.poll();
                }
                if (left.isEmpty() || nums[i] < left.peek()[1]) {
                    inLeft[i] = true;
                    left.offer(pair);
                    if (!inLeft[i - k]) {
                        int[] temp = left.poll();
                        inLeft[temp[0]] = false;
                        right.offer(temp);
                    }
                } else {
                    inLeft[i] = false;
                    right.offer(pair);
                    if (inLeft[i - k]) {
                        int[] temp = right.poll();
                        inLeft[temp[0]] = true;
                        left.offer(temp);
                    }
                }
                while (!left.isEmpty() && left.peek()[0] <= i - k) {
                    left.poll();
                }
                while (!right.isEmpty() && right.peek()[0] <= i - k) {
                    right.poll();
                }
            }
            if (i >= k - 1) {
                ans[i - k + 1] = isOdd ? left.peek()[1] : (double) ((long) left.peek()[1] + right.peek()[1]) / 2;
            }
        }
        return ans;
    }

    class DualHeap {
        PriorityQueue<Integer> small;
        PriorityQueue<Integer> large;
        Map<Integer, Integer> delay;
        int k;
        int smallSize;
        int largeSize;

        DualHeap(int k) {
            small = new PriorityQueue<>((a, b) -> b != a ? (b > a ? 1 : -1) : 0);
            large = new PriorityQueue<>(Comparator.comparingInt(a -> a));
            delay = new HashMap<>();
            this.k = k;
            this.smallSize = 0;
            this.largeSize = 0;
        }

        void insert(int num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                ++smallSize;
            } else {
                large.offer(num);
                ++largeSize;
            }
            makeBalance();
        }

        void makeBalance() {
            if (smallSize > largeSize + 1) {
                large.offer(small.poll());
                --smallSize;
                ++largeSize;
                prune(small);
            } else if (largeSize > smallSize) {
                small.offer(large.poll());
                ++smallSize;
                --largeSize;
                prune(large);
            }
        }

        void prune(PriorityQueue<Integer> queue) {
            while (!queue.isEmpty() && delay.containsKey(queue.peek())) {
                int num = queue.poll();
                delay.put(num, delay.get(num) - 1);
                if (delay.get(num) == 0) {
                    delay.remove(num);
                }
            }
        }

        void erase(int num) {
            delay.put(num, delay.getOrDefault(num, 0) + 1);
            if (num <= small.peek()) {
                prune(small);
                --smallSize;
                makeBalance();
            } else {
                prune(large);
                --largeSize;
                makeBalance();
            }
        }

        double getMedian() {
            return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        DualHeap dualHeap = new DualHeap(k);
        int n = nums.length;
        double[] ans = new double[n - k + 1];
        for (int i = 0; i < k; ++i) {
            dualHeap.insert(nums[i]);
        }
        ans[0] = dualHeap.getMedian();
        for (int i = k; i < n; ++i) {
            dualHeap.insert(nums[i]);
            dualHeap.erase(nums[i - k]);
            ans[i - k + 1] = dualHeap.getMedian();
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0480SlidingWindowMedian a = new Problem0480SlidingWindowMedian();
//        int[] nums = {-2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648};
//        double[] ans = a.medianSlidingWindow(nums, 3);
//        int i = 1;
//    }
}
