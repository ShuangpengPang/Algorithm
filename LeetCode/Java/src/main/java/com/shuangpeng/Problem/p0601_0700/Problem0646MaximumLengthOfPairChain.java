package com.shuangpeng.Problem.p0601_0700;

import java.util.Arrays;
import java.util.Comparator;

public class Problem0646MaximumLengthOfPairChain {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int n = pairs.length;
        int end = pairs[0][1], length = 1;
        for (int i = 1; i < n; i++) {
            if (pairs[i][0] > end) {
                length++;
                end = pairs[i][1];
            }
        }
        return length;
    }
}
