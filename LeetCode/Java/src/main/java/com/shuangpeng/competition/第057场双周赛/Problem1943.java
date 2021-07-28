package com.shuangpeng.competition.第057场双周赛;

import javafx.util.Pair;

import java.util.*;

public class Problem1943 {

    public List<List<Long>> splitPainting0(int[][] segments) {
        Arrays.sort(segments, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int n = segments.length;
        int left = 0;
        long sum = 0;
        List<List<Long>> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = segments[i][0];
            int end = segments[i][1];
            int color = segments[i][2];
            while (!queue.isEmpty() && queue.peek()[0] <= start) {
                int[] segment = queue.poll();
                if (left < segment[0]) {
                    answer.add(Arrays.asList((long) left, (long) segment[0], sum));
                    left = segment[0];
                }
                sum -= segment[1];
            }
            if (!queue.isEmpty() && left < start) {
                answer.add(Arrays.asList((long) left, (long) start, sum));
            }
            left = start;
            sum += color;
            queue.offer(new int[]{end, color});
        }
        while (!queue.isEmpty()) {
            int[] segment = queue.poll();
            if (left < segment[0]) {
                answer.add(Arrays.asList((long) left, (long) segment[0], sum));
                left = segment[0];
            }
            sum -= segment[1];
        }
        return answer;
    }

    public List<List<Long>> splitPainting(int[][] segments) {
        Map<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < segments.length; i++) {
            int start = segments[i][0];
            int end = segments[i][1];
            int color = segments[i][2];
            map.put(start, map.getOrDefault(start, 0L) + color);
            map.put(end, map.getOrDefault(end, 0L) - color);
        }
        List<Pair<Integer, Long>> list = new ArrayList<>(map.size());
        for (int key : map.keySet()) {
            list.add(new Pair<>(key, map.get(key)));
        }
        Collections.sort(list, Comparator.comparingInt(a -> a.getKey()));
        long sum = 0;
        int n = list.size();
        List<List<Long>> answer = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int start = list.get(i).getKey();
            int end = list.get(i + 1).getKey();
            sum += list.get(i).getValue();
            if (sum > 0) {
                answer.add(Arrays.asList((long) start, (long) end, sum));
            }
        }
        return answer;
    }
}
