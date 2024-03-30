package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2549CountDistinctNumbersOnBoard（统计桌面上的不同数字）
 * @date 2024/3/30 2:34 PM
 */
public class Problem2549CountDistinctNumbersOnBoard {

    public int distinctIntegers0(int n) {
        int[] nums = new int[n + 1];
        nums[n] = 1;
        for (int k = 0; k < n; k++) {
            for (int x = 1; x <= n; x++) {
                if (nums[x] == 0) {
                    continue;
                }
                for (int i = 1; i <= n; i++) {
                    if (x % i == 1) {
                        nums[i] = 1;
                    }
                }
            }
        }
        return Arrays.stream(nums).sum();
    }

    public int distinctIntegers(int n) {
        return n == 1 ? 1 : n - 1;
    }
}
