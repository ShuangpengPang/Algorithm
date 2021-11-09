package com.shuangpeng.Problem.p0901_1000;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem0981TimeBasedKeyValueStore {

    class TimeMap {
        Map<String, List<Pair<Integer, String>>> map;

        /**
         * Initialize your data structure here.
         */
        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            List<Pair<Integer, String>> list = map.getOrDefault(key, new ArrayList<>());
            Pair<Integer, String> pair = new Pair<>(timestamp, value);
            list.add(pair);
            map.put(key, list);
        }

        public String get(String key, int timestamp) {
            List<Pair<Integer, String>> list = map.getOrDefault(key, null);
            if (list == null) {
                return "";
            }
            int n = list.size();
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int time = list.get(mid).getKey();
                if (time <= timestamp) {
                    left = mid + 1;
                } else if (time > timestamp) {
                    right = mid - 1;
                }
            }
            if (left > 0) {
                return list.get(left - 1).getValue();
            }
            return "";
        }
    }

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
}
