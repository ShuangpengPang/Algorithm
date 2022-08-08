package com.shuangpeng.Problem.p0601_0700;

import java.util.ArrayDeque;
import java.util.Deque;
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

class Problem0636ExclusiveTimeOfFunctions0 {

    public int[] exclusiveTime0(int n, List<String> logs) {
        int[] ans = new int[n];
        Deque<Integer> startStack = new ArrayDeque<>(), timeStack = new ArrayDeque<>();
        for (String log : logs) {
            String[] arr = log.split(":");
            int time = Integer.parseInt(arr[2]);
            if (arr[1].equals("start")) {
                startStack.push(time);
                timeStack.push(0);
            } else {
                int id = Integer.parseInt(arr[0]);
                int total = time - startStack.pop() + 1;
                int t = timeStack.pop();
                ans[id] += total - t;
                if (!timeStack.isEmpty()) {
                    timeStack.push(timeStack.pop() + total);
                }
            }
        }
        return ans;
    }

    public int[] exclusiveTime1(int n, List<String> logs) {
        int[] ans = new int[n];
        Deque<int[]> stack = new ArrayDeque<>();
        for (String log : logs) {
            String[] arr = log.split(":");
            int id = Integer.parseInt(arr[0]);
            int time = Integer.parseInt(arr[2]);
            if ("start".equals(arr[1])) {
                if (!stack.isEmpty()) {
                    ans[stack.peek()[0]] += time - stack.peek()[1];
                }
                stack.push(new int[]{id, time});
            } else {
                ans[id] += time - stack.pop()[1] + 1;
                if (!stack.isEmpty()) {
                    stack.peek()[1] = time + 1;
                }
            }
        }
        return ans;
    }

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int cur = 0;
        for (String log : logs) {
            String[] ss = log.split(":");
            int id = Integer.parseInt(ss[0]), t = Integer.parseInt(ss[2]);
            if (ss[1].equals("start")) {
                if (!stack.isEmpty()) {
                    ans[stack.peek()] += t - cur;
                }
                cur = t;
                stack.push(id);
            } else {
                ans[stack.pop()] += t - cur + 1;
                cur = t + 1;
            }
        }
        return ans;
    }
}


