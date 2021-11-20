package com.shuangpeng.Problem.p0501_0600;

import java.util.HashSet;
import java.util.Set;

public class Problem0575DistributeCandies {

    public int distributeCandies(int[] candyType) {
        int n = candyType.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n && set.size() < (n >> 1); ++i) {
            set.add(candyType[i]);
        }
        return set.size();
    }
}
