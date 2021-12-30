package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0846HandOfStraights {

    public boolean isNStraightHand0(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        for (int i = 0; i < n; i += groupSize) {
            Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
            Map.Entry<Integer, Integer> prev = null;
            List<Integer> removeList = new ArrayList<>();
            for (int j = 0; j < groupSize; ++j) {
                if (!iterator.hasNext()) {
                    return false;
                }
                Map.Entry<Integer, Integer> curr = iterator.next();
                if (prev != null && curr.getKey() != prev.getKey() + 1) {
                    return false;
                }
                curr.setValue(curr.getValue() - 1);
                if (curr.getValue() == 0) {
                    removeList.add(curr.getKey());
                }
                prev = curr;
            }
            for (int key : removeList) {
                map.remove(key);
            }
        }
        return true;
    }

    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        for (int h : hand) {
            if (!map.containsKey(h)) {
                continue;
            }
            for (int j = 0; j < groupSize; ++j) {
                int num = h + j;
                int count = map.getOrDefault(num, 0);
                if (count == 0) {
                    return false;
                }
                if (count == 1) {
                    map.remove(num);
                } else {
                    map.put(num, count - 1);
                }
            }
        }
        return true;
    }
}
