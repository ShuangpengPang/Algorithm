package com.shuangpeng.Problem.p0701_0800;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @Description: Problem0732MyCalendarIII（我的日程安排III）
 * @Date 2022/6/6 11:25 AM
 * @Version 1.0
 */
public class Problem0732MyCalendarIII {

    class MyCalendarThree {

        TreeMap<Integer, int[]> map;
        int ans = 0;

        public MyCalendarThree() {
            map = new TreeMap<>();
        }

        public int book(int start, int end) {
            Integer leftKey = map.floorKey(start);
            Integer rightKey = map.lowerKey(end);
            if (rightKey == null) {
                map.put(start, new int[]{end, 1});
                ans = Math.max(ans, 1);
                return ans;
            }
            if (leftKey == null) {
                leftKey = map.ceilingKey(start);
            }
            TreeSet<Integer> set = new TreeSet<>();
            for (Integer key = leftKey; key != null && key <= rightKey; key = map.higherKey(key)) {
                set.add(key);
            }
            for (int key : set) {
                int[] pair = map.get(key);
                int e = pair[0], c = pair[1];
                if (e > start) {
                    if (start < key) {
                        map.put(start, new int[]{key, 1});
                        start = key;
                        ans = Math.max(ans, 1);
                    } else if (key < start) {
                        pair[0] = start;
                        ans = Math.max(ans, c);
                    }
                    if (e <= end) {
                        map.put(start, new int[]{e, c + 1});
                    } else {
                        map.put(start, new int[]{end, c + 1});
                        map.put(end, new int[]{e, c});
                    }
                    ans = Math.max(ans, c + 1);
                    start = e;
                }
            }
            if (start < end) {
                map.put(start, new int[]{end, 1});
                ans = Math.max(ans, 1);
            }
            return ans;
        }
    }

//    public static void main(String[] args) {
//        MyCalendarThree a = new Problem0732MyCalendarIII().new MyCalendarThree();
//        int[][] arrays = {{47,50},{1,10},{27,36},{40,47},{20,27},{15,23},{10,18},{27,36},{17,25},{8,17},{24,33},{23,28},{21,27},{47,50},{14,21},{26,32},{16,21},{2,7},{24,33}};
//        for (int[] pair : arrays) {
//            a.book(pair[0], pair[1]);
//        }
//    }
}
