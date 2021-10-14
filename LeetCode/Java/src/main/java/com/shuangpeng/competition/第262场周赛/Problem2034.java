package com.shuangpeng.competition.第262场周赛;

import java.util.PriorityQueue;
import java.util.TreeMap;

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
