package com.shuangpeng.Problem.p0401_0500;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem0401BinaryWatch {

    public List<String> readBinaryWatch0(int turnedOn) {
        List<String> answer = new ArrayList<>();
        if (turnedOn < 0 || turnedOn >= 9) {
            return answer;
        }
        int[] hours = {1, 2, 4, 8};
        int[] minutes = {1, 2, 4, 8, 16, 32};
        int s = turnedOn - Math.min(5, turnedOn), e = Math.min(3, turnedOn);
        for (int i = s; i <= e; i++) {
            Set<Integer> hourSet = new HashSet<>();
            Set<Integer> minuteSet = new HashSet<>();
            backtrack(hours, 0, i, hourSet, 0, 11);
            backtrack(minutes, 0, turnedOn - i, minuteSet, 0, 59);
            for (int hour : hourSet) {
                for (int minute : minuteSet) {
                    answer.add(hour + ":" + (minute < 10 ? "0" + minute : minute));
                }
            }
        }
        return answer;
    }

    private void backtrack(int[] nums, int i, int c, Set<Integer> result, int sum, int max) {
        if (c == 0) {
            if (sum <= max) {
                result.add(sum);
            }
            return;
        }
        if (i >= nums.length) {
            return;
        }
        backtrack(nums, i + 1, c, result, sum, max);
        backtrack(nums, i + 1, c - 1, result, sum + nums[i], max);
    }

    public List<String> readBinaryWatch(int turnedOn) {
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    answer.add(i + ":" + (j < 10 ? "0" + j : j));
                }
            }
        }
        return answer;
    }
}
