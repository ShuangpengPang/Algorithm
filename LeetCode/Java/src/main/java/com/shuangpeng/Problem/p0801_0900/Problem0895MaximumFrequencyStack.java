package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0895MaximumFrequencyStack {
}

class FreqStack0 {

    Map<Integer, List<Integer>> map;
    PriorityQueue<int[]> pq;
    int idx;

    public FreqStack0() {
        map = new HashMap<>();
        pq = new PriorityQueue<>((a, b) -> {
            if (b[1] != a[1]) {
                return b[1] - a[1];
            }
            return b[2] - a[2];
        });
        idx = 0;
    }

    public void push(int val) {
        List<Integer> list = map.computeIfAbsent(val, k -> new ArrayList<>());
        list.add(idx);
        pq.offer(new int[]{val, list.size(), idx});
        ++idx;
    }

    public int pop() {
        while (!map.containsKey(pq.peek()[0]) || map.get(pq.peek()[0]).size() != pq.peek()[1] || map.get(pq.peek()[0]).get(map.get(pq.peek()[0]).size() - 1) != pq.peek()[2]) {
            pq.poll();
        }
        int[] tuple = pq.poll();
        int num = tuple[0];
        List<Integer> list = map.get(num);
        list.remove(list.size() - 1);
        if (list.isEmpty()) {
            map.remove(num);
        } else {
            --tuple[1];
            tuple[2] = list.get(list.size() - 1);
            pq.offer(tuple);
        }
        return num;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */

class FreqStack {

    Map<Integer, Integer> freq;
    Map<Integer, List<Integer>> group;
    int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {
        int f = freq.getOrDefault(val, 0) + 1;
        freq.put(val, f);
        List<Integer> list = group.computeIfAbsent(f, k -> new ArrayList<>());
        list.add(val);
        maxFreq = Math.max(maxFreq, f);
    }

    public int pop() {
        List<Integer> list = group.get(maxFreq);
        int num = list.get(list.size() - 1);
        if (maxFreq == 1) {
            freq.remove(num);
        } else {
            freq.put(num, maxFreq - 1);
        }
        list.remove(list.size() - 1);
        if (list.isEmpty()) {
            group.remove(maxFreq);
            --maxFreq;
        }
        return num;
    }
}
