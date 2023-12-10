package com.shuangpeng.Problem.p2601_2700;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2671FrequencyTracker（频率跟踪器）
 * @date 2023/12/10 12:01 PM
 */
public class Problem2671FrequencyTracker {
}

class FrequencyTracker {

    int N = (int) 1e5;
    int[] freq;
    Map<Integer, Integer> map;

    public FrequencyTracker() {
        freq = new int[N + 1];
        freq[0] = N;
        map = new HashMap<>();
    }

    public void add(int number) {
        int f = map.merge(number, 1, Integer::sum);
        freq[f - 1]--;
        freq[f]++;
    }

    public void deleteOne(int number) {
        int f = map.getOrDefault(number, 0);
        if (f > 0) {
            map.put(number, f - 1);
            freq[f]--;
            freq[f - 1]++;
        }
    }

    public boolean hasFrequency(int frequency) {
        return freq[frequency] > 0;
    }
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker obj = new FrequencyTracker();
 * obj.add(number);
 * obj.deleteOne(number);
 * boolean param_3 = obj.hasFrequency(frequency);
 */