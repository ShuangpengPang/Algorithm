package com.shuangpeng.Problem.p0901_1000;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0914XOfAKindInADeckOfCards（卡牌分组）
 * @date 2024/1/11 11:59 PM
 */
public class Problem0914XOfAKindInADeckOfCards {

    public boolean hasGroupsSizeX(int[] deck) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int d : deck) {
            freq.merge(d, 1, Integer::sum);
        }
        int g = 0;
        for (int f : freq.values()) {
            g = gcd(f, g);
        }
        return g > 1;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
