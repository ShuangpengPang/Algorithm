package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0354RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] < b[0]) {
                return -1;
            } else if (a[0] > b[0]) {
                return 1;
            } else {
                return b[1] - a[1];
            }
        });
        int n = envelopes.length;
        int[] dp = new int[n];
        int answer = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0 && envelopes[j][1] >= envelopes[i][1]; j--) {
            }
        }
    }
}
