package com.shuangpeng.lcr;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR135CountNumbers（报数）
 * @date 2024/5/14 12:22 PM
 */
public class LCR135CountNumbers {

    public int[] countNumbers0(int cnt) {
        int N = (int) Math.pow(10, cnt) - 1;
        int[] ans = new int[N];
        Arrays.setAll(ans, i -> i + 1);
        return ans;
    }

    public int[] countNumbers(int cnt) {
        int N = (int) Math.pow(10, cnt) - 1;
        int[] ans = new int[N];
        for (int i = 0; i < N; i++) {
            ans[i] = i + 1;
        }
        return ans;
    }
}
