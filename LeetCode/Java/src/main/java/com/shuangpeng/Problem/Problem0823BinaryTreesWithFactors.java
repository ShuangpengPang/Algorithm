package com.shuangpeng.Problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem0823BinaryTreesWithFactors {

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> dp = new HashMap<>();
        int n = arr.length;
        final int M = (int) 1e9 + 7;
        int count = 0;
        for (int i = 0; i < n; ++i) {
            int c = 1;
            for (int j = 0; j < i; ++j) {
                if (arr[i] % arr[j] == 0) {
                    int k = arr[i] / arr[j];
                    c = (int) (((long) dp.get(arr[j]) * dp.getOrDefault(k, 0) + c) % M);
                }
            }
            count += c;
            if (count >= M) {
                count -= M;
            }
            dp.put(arr[i], c);
        }
        return count;
    }
}
