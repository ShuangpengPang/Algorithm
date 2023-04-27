package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1237FindPositiveIntegerSolutionForAGivenEquation（找出给定方程的正整数解）
 * @date 2023/4/27 4:36 PM
 */

interface CustomFunction {
    // Returns some positive integer f(x, y) for two positive integers x and y based on a formula.
    int f(int x, int y);
}

public class Problem1237FindPositiveIntegerSolutionForAGivenEquation {

    public List<List<Integer>> findSolution0(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int x = 1; x <= 1000; x++) {
            for (int y = 1; y <= 1000; y++) {
                int value = customfunction.f(x, y);
                if (value == z) {
                    ans.add(Arrays.asList(x, y));
                }
                if (value >= z) {
                    break;
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> findSolution1(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int x = 1, y = 1000; x <= 1000 && y > 0; x++) {
            int left = 1, right = y;
            while (left <= right) {
                int mid = left + (right - left >> 1);
                int value = customfunction.f(x, mid);
                if (value < z) {
                    left = mid + 1;
                } else if (value > z) {
                    right = mid - 1;
                } else {
                    left = mid;
                    ans.add(Arrays.asList(x, left));
                    break;
                }
            }
            y = left - 1;
        }
        return ans;
    }

    public List<List<Integer>> findSolution2(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int x = 1, y = 1000; x <= 1000 && y > 0;) {
            int value = customfunction.f(x, y);
            if (value > z) {
                y--;
            } else if (value < z) {
                x++;
            } else {
                ans.add(Arrays.asList(x, y));
                x++;
                y--;
            }
        }
        return ans;
    }

    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int x = 1, y = 1000; x <= 1000 && y > 0; x++) {
            while (y > 0 && customfunction.f(x, y) > z) {
                y--;
            }
            if (y > 0 && customfunction.f(x, y) == z) {
                ans.add(Arrays.asList(x, y));
            }
        }
        return ans;
    }
}
