package com.shuangpeng.lcr.p001_100;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR058MyCalendar（我的日程安排表I）
 * @date 2024/6/25 4:39 PM
 */
public class LCR058MyCalendar {
}

class MyCalendar {

    private TreeMap<Integer, Integer> treeMap;

    public MyCalendar() {
        treeMap = new TreeMap<>();
    }

    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> lowerEntry = treeMap.lowerEntry(start);
        if (lowerEntry != null && lowerEntry.getValue() > start) {
            return false;
        }
        Map.Entry<Integer, Integer> ceilingEntry = treeMap.ceilingEntry(start);
        if (ceilingEntry != null && ceilingEntry.getKey() < end) {
            return false;
        }
        treeMap.put(start, end);
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
