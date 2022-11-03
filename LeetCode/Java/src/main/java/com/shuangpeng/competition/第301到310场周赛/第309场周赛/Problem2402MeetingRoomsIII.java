package com.shuangpeng.competition.第301到310场周赛.第309场周赛;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @Description: Problem2402MeetingRoomsIII（会议室III）
 * @Date 2022/11/2 4:38 PM
 * @Version 1.0
 */
public class Problem2402MeetingRoomsIII {
    
    public int mostBooked0(int n, int[][] meetings) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        int[] cnt = new int[n];
        TreeSet<Pair<Integer, Long>> set = new TreeSet<>((a, b) -> !a.getValue().equals(b.getValue()) ? Long.compare(a.getValue(), b.getValue()) : a.getKey() - b.getKey());
        for (int i = 0; i < n; i++) {
            set.add(new Pair<>(i, 0L));
        }
        for (int[] m : meetings) {
            long s = m[0], e = m[1];
            while (set.first().getValue() < s) {
                Pair<Integer, Long> first = set.first();
                set.remove(first);
                set.add(new Pair<>(first.getKey(), s));
            }
            Pair<Integer, Long> p = set.first();
            set.remove(p);
            set.add(new Pair<>(p.getKey(), p.getValue() + e - s));
            cnt[p.getKey()]++;
        }
        int maxCnt = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            if (cnt[i] > maxCnt) {
                maxCnt = cnt[i];
                ans = i;
            }
        }
        return ans;
    }

    public int mostBooked(int n, int[][] meetings) {
        int[] cnt = new int[n];
        long[] time = new long[n];
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        for (int[] m : meetings) {
            int s = m[0], e = m[1];
            long minTime = Long.MAX_VALUE;
            boolean found = false;
            int index = 0;
            for (int i = 0; i < n; i++) {
                if (time[i] <= s) {
                    time[i] = e;
                    cnt[i]++;
                    found = true;
                    break;
                } else if (time[i] < minTime) {
                    minTime = time[i];
                    index = i;
                }
            }
            if (!found) {
                time[index] += e - s;
                cnt[index]++;
            }
        }
        int maxCnt = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            if (cnt[i] > maxCnt) {
                maxCnt = cnt[i];
                ans = i;
            }
        }
        return ans;
    }
}
