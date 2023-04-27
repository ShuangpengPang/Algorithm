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

    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
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
}
