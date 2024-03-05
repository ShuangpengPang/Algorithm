package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1502CanMakeArithmeticProgressionFromSequence（判断能否形成等差数列）
 * @date 2024/3/6 12:11 AM
 */
public class Problem1502CanMakeArithmeticProgressionFromSequence {

    public boolean canMakeArithmeticProgression0(int[] arr) {
        Arrays.sort(arr);
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != arr[i - 1] - arr[i - 2]) {
                return false;
            }
        }
        return true;
    }

    public boolean canMakeArithmeticProgression(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : arr) {
            set.add(num);
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min == max) {
            return true;
        }
        int n = arr.length, d = (max - min) / (n - 1);
        if (set.size() != n || min + d * (n - 1) != max) {
            return false;
        }
        for (int i = min + d; i < max; i += d) {
            if (!set.contains(i)) {
                return false;
            }
        }
        return true;
    }
}
