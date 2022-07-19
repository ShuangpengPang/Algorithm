package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: Problem0731MyCalendarII（我的日程安排表II）
 * @Date 2022/7/19 10:37 AM
 * @Version 1.0
 */
public class Problem0731MyCalendarII {
}

class MyCalendarTwo {

    TreeMap<Integer, int[]> map;

    public MyCalendarTwo() {
        map = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, int[]> startEntry = map.floorEntry(start);
        if (startEntry == null) {
            startEntry = map.higherEntry(start);
        }
        if (startEntry == null || startEntry.getKey() >= end) {
            map.put(start, new int[]{end, 1});
            return true;
        }
        Map.Entry<Integer, int[]> endEntry = map.lowerEntry(end);
        if (endEntry.getValue()[0] <= start) {
            map.put(start, new int[]{end, 1});
            return true;
        }
        List<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, int[]> entry = startEntry; entry != null && entry.getKey() <= endEntry.getKey(); entry = map.higherEntry(entry.getKey())) {
            keys.add(entry.getKey());
        }
        for (int key : keys) {
            int[] value = map.get(key);
            int e = value[0], c = value[1];
            if (e <= start) {
                continue;
            }
            if (c == 2) {
                return false;
            }
        }
        for (int key : keys) {
            int[] value = map.get(key);
            int e = value[0], c = value[1];
            if (e <= start) {
                continue;
            }
            map.remove(key);
            if (key < start) {
                map.put(key, new int[]{start, c});
            } else if (start < key) {
                map.put(start, new int[]{key, 1});
                start = key;
            }
            int e1 = Math.min(e, end);
            map.put(start, new int[]{e1, c + 1});
            start = e1;
            if (key == endEntry.getKey()) {
                if (start < end) {
                    map.put(start, new int[]{end, 1});
                } else if (start < e) {
                    map.put(start, new int[]{e, c});
                }
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        MyCalendarTwo a = new MyCalendarTwo();
//        int[][] books = {{47,50},{1,10},{27,36},{40,47},{20,27},{15,23},{10,18},{27,36},{17,25}};
//        for (int[] book : books) {
//            a.book(book[0], book[1]);
//        }
//    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
