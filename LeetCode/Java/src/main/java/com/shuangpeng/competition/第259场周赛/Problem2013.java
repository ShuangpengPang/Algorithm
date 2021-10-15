package com.shuangpeng.competition.第259场周赛;

import java.util.HashMap;
import java.util.Map;

public class Problem2013 {

    class DetectSquares {

        // key为x
        Map<Integer, Map<Integer, Integer>> xMap;
        // key为y
        Map<Integer, Map<Integer, Integer>> yMap;

        public DetectSquares() {
            xMap = new HashMap<>();
            yMap = new HashMap<>();
        }

        public void add(int[] point) {
            int x = point[0], y = point[1];
            Map<Integer, Integer> xPair = xMap.getOrDefault(x, new HashMap<>());
            xPair.put(y, xPair.getOrDefault(y, 0) + 1);
            xMap.put(x, xPair);
            Map<Integer, Integer> yPair = yMap.getOrDefault(y, new HashMap<>());
            yPair.put(x, yPair.getOrDefault(x, 0) + 1);
            yMap.put(y, yPair);
        }

        public int count(int[] point) {
            // (x, y) (x0, y) (x, y +- (x0 - x)) (x0, y +- (x0 - x))
            int x = point[0], y = point[1];
            Map<Integer, Integer> xPair = xMap.get(x);
            Map<Integer, Integer> yPair = yMap.get(y);
            if (xPair == null || yPair == null) {
                return 0;
            }
            int ans = 0;
            for (Map.Entry<Integer, Integer> entry : yPair.entrySet()) {
                int x0 = entry.getKey(), c0 = entry.getValue();
                if (x != x0) {
                    ans += getCount(x0, y, c0, x0 - x, xPair);
                    ans += getCount(x0, y, c0, x - x0, xPair);
                }
            }
            return ans;
        }

        private int getCount(int x0, int y, int c0, int length, Map<Integer, Integer> xPair) {
            int c1 = xPair.getOrDefault(y + length, 0);
            int c2 = xMap.getOrDefault(x0, new HashMap<>()).getOrDefault(y + length, 0);
            return c0 * c1 * c2;
        }
    }

/**
 * Your DetectSquares object will be instantiated and called as such:
 * DetectSquares obj = new DetectSquares();
 * obj.add(point);
 * int param_2 = obj.count(point);
 */
}
