package com.shuangpeng.Problem.p0601_0700;

import java.util.List;

/**
 * @Description: Problem0636ExclusiveTimeOfFunctions（函数的独占时间）
 * @Date 2022/8/8 3:05 PM
 * @Version 1.0
 */
public class Problem0636ExclusiveTimeOfFunctions {

    int idx;

    public int[] exclusiveTime(int n, List<String> logs) {
        idx = 0;
        int[] time = new int[n];
        int size = logs.size();
        while (idx < size) {
            calculateTime(time, logs);
        }
        return time;
    }

    private int calculateTime(int[] time, List<String> logs) {
        String[] startLog = logs.get(idx).split(":");
        idx++;
        int t = 0;
        while (!logs.get(idx).contains("end")) {
            t += calculateTime(time, logs);
        }
        String[] endLog = logs.get(idx).split(":");
        idx++;
        int id = Integer.parseInt(startLog[0]);
        int start = Integer.parseInt(startLog[2]);
        int end = Integer.parseInt(endLog[2]);
        int total = end - start + 1;
        time[id] += total - t;
        return total;
    }
}
