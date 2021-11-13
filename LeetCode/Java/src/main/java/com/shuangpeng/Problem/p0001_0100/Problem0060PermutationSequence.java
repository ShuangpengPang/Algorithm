package com.shuangpeng.Problem.p0001_0100;

import java.util.Arrays;

public class Problem0060PermutationSequence {

    public String getPermutation0(int n, int k) {
        int[] array = new int[n];
        array[0] = 1;
        for (int i = 1; i < n; ++i) {
            array[i] = i * array[i - 1];
        }
        StringBuilder sb = new StringBuilder();
        dfs(sb, array, 0, n, k);
        return sb.toString();
    }

    private void dfs(StringBuilder sb, int[] array, int mask, int n, int k) {
        if (n == 0) {
            return;
        }
        int index = (k - 1) / array[n - 1] + 1;
        int i = 0, j = -1;
        while (i < index) {
            ++j;
            if (((mask >> j) & 1) == 0) {
                ++i;
            }
        }
        mask |= (1 << j);
        sb.append(j + 1);
        dfs(sb, array, mask, n - 1, k - (index - 1) * array[n - 1]);
    }

    public String getPermutation(int n, int k) {
        int[] factor = new int[n];
        factor[0] = 1;
        for (int i = 1; i < n; ++i) {
            factor[i] = i * factor[i - 1];
        }
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        StringBuilder sb = new StringBuilder();
        --k;
        for (int i = 0; i < n; ++i) {
            int order = k / factor[n - i - 1] + 1;
            for (int j = 1; j <= n; ++j) {
                order -= valid[j];
                if (order == 0) {
                    sb.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= factor[n - i - 1];
        }
        return sb.toString();
    }
}
