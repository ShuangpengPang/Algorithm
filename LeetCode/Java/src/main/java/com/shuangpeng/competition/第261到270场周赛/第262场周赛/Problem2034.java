package com.shuangpeng.competition.第261到270场周赛.第262场周赛;

import java.util.*;

public class Problem2034 {

    class StockPrice {

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        PriorityQueue<int[]> minQueue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> maxQueue = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        public StockPrice() {
        }

        public void update(int timestamp, int price) {
            treeMap.put(timestamp, price);
            int[] pair = {timestamp, price};
            minQueue.add(pair);
            maxQueue.add(pair);
        }

        public int current() {
            return treeMap.lastEntry().getValue();
        }

        public int maximum() {
            while (maxQueue.peek()[1] != treeMap.get(maxQueue.peek()[0])) {
                maxQueue.poll();
            }
            return maxQueue.peek()[1];
        }

        public int minimum() {
            while (minQueue.peek()[1] != treeMap.get(minQueue.peek()[0])) {
                minQueue.poll();
            }
            return minQueue.peek()[1];
        }
    }

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
}

class Solution {

    class StockPrice {
        Map<Integer, Integer> map;
        PriorityQueue<int[]> maxQueue;
        PriorityQueue<int[]> minQueue;
        int maxTimestamp;

        public StockPrice() {
            map = new HashMap<>();
            maxQueue = new PriorityQueue<>((a, b) -> b[1] - a[1]);
            minQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            maxTimestamp = 0;
        }

        public void update(int timestamp, int price) {
            map.put(timestamp, price);
            int[] stock = {timestamp, price};
            maxQueue.offer(stock);
            minQueue.offer(stock);
            maxTimestamp = Math.max(maxTimestamp, timestamp);
        }

        public int current() {
            return map.get(maxTimestamp);
        }

        public int maximum() {
            while (map.get(maxQueue.peek()[0]) != maxQueue.peek()[1]) {
                maxQueue.poll();
            }
            return map.get(maxQueue.peek()[0]);
        }

        public int minimum() {
            while (map.get(minQueue.peek()[0]) != minQueue.peek()[1]) {
                minQueue.poll();
            }
            return map.get(minQueue.peek()[0]);
        }
    }
}
