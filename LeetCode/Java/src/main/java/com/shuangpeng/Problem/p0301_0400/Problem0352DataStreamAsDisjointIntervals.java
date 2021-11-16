package com.shuangpeng.Problem.p0301_0400;

import java.util.Map;
import java.util.TreeMap;

public class Problem0352DataStreamAsDisjointIntervals {

    class SummaryRanges {

        TreeMap<Integer, Integer> treeMap;

        public SummaryRanges() {
            treeMap = new TreeMap<>();
        }

        public void addNum(int val) {
            Map.Entry<Integer, Integer> entry1 = treeMap.floorEntry(val);
            if (entry1 != null && entry1.getValue() >= val) {
                return;
            }
            int end = entry1 == null ? Integer.MIN_VALUE : entry1.getValue();
            Map.Entry<Integer, Integer> entry2 = treeMap.ceilingEntry(val + 1);
            int start = entry2 == null ? Integer.MAX_VALUE : entry2.getKey();
            if (val == end + 1 && val == start - 1) {
                treeMap.put(entry1.getKey(), entry2.getValue());
                treeMap.remove(start);
            } else if (val == end + 1) {
                treeMap.put(entry1.getKey(), val);
            } else if (val == start - 1) {
                treeMap.put(val, entry2.getValue());
                treeMap.remove(start);
            } else {
                treeMap.put(val, val);
            }
        }

        public int[][] getIntervals() {
            int n = treeMap.keySet().size();
            int[][] ans = new int[n][2];
            int i = 0;
            for (int start : treeMap.keySet()) {
                ans[i][0] = start;
                ans[i][1] = treeMap.get(start);
                ++i;
            }
            return ans;
        }
    }

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
}
