package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1387SortIntegersByThePowerValue（将整数按权重排序）
 * @date 2023/8/14 11:51 AM
 */
public class Problem1387SortIntegersByThePowerValue {

    static int N = 1000;
    static int[] cnt = new int[N + 1];
    static {
        for (int i = 2; i <= N; i++) {
            int j = i, s = 0;
            while (j > N || cnt[j] == 0 && j != 1) {
                s++;
                if ((j & 1) == 0) {
                    j >>= 1;
                } else {
                    j = j * 3 + 1;
                }
            }
            cnt[i] = cnt[j] + s;
        }
    }

    public int getKth(int lo, int hi, int k) {
        Integer[] arr = new Integer[hi - lo + 1];
        Arrays.setAll(arr, i -> i + lo);
        Arrays.sort(arr, (a, b) -> cnt[a] != cnt[b] ? cnt[a] - cnt[b] : a - b);
        return arr[k - 1];
    }
}
