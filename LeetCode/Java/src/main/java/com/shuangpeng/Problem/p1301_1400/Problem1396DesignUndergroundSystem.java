package com.shuangpeng.Problem.p1301_1400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1396DesignUndergroundSystem（设计地铁系统）
 * @date 2023/8/18 6:41 PM
 */
public class Problem1396DesignUndergroundSystem {

    class UndergroundSystem {

        Map<Integer, Integer> timeMap;
        Map<Integer, String> stationMap;
        Map<String, int[]> avgMap;

        public UndergroundSystem() {
            timeMap = new HashMap<>();
            stationMap = new HashMap<>();
            avgMap = new HashMap<>();
        }

        public void checkIn(int id, String stationName, int t) {
            timeMap.put(id, t);
            stationMap.put(id, stationName);
        }

        public void checkOut(int id, String stationName, int t) {
            int time = t - timeMap.get(id);
            String station = stationMap.get(id) + '_' + stationName;
            int[] arr = avgMap.computeIfAbsent(station, k -> new int[2]);
            arr[0] += time;
            arr[1]++;
        }

        public double getAverageTime(String startStation, String endStation) {
            int[] arr = avgMap.get(startStation + '_' + endStation);
            return (double) arr[0] / arr[1];
        }
    }

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
}
