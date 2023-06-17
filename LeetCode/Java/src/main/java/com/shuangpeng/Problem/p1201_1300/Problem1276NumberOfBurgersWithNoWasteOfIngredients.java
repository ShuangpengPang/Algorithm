package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1276NumberOfBurgersWithNoWasteOfIngredients（不浪费原料的汉堡制作方案）
 * @date 2023/6/17 8:49 PM
 */
public class Problem1276NumberOfBurgersWithNoWasteOfIngredients {

    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        if ((tomatoSlices & 1) == 1) {
            return new ArrayList<>();
        }
        int a = (tomatoSlices - (cheeseSlices << 1)) >> 1, b = cheeseSlices - a;
        if (a < 0 || b < 0) {
            return new ArrayList<>();
        }
        return Arrays.asList(a, b);
    }
}
