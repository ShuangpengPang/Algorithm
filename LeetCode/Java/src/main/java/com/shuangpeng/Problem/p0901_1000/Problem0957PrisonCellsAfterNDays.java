package com.shuangpeng.Problem.p0901_1000;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0957PrisonCellsAfterNDays（N天后的牢房）
 * @date 2023/3/27 7:11 PM
 */
public class Problem0957PrisonCellsAfterNDays {

    public int[] prisonAfterNDays(int[] cells, int n) {
        int m = cells.length, h = 0;
        for (int c : cells) {
            h = h << 1 | c;
        }
        Map<Integer, Integer> memo = new HashMap<>();
        int[] hash = new int[1 << m];
        int d = 0, mask = (1 << 7) - 2;
        while (d <= n && !memo.containsKey(h)) {
            memo.put(h, d);
            hash[d++] = h;
            h = ~((h >> 1) ^ (h << 1)) & mask;
        }
        if (d > n) {
            return toArray(hash[d - 1]);
        }
        int p = memo.get(h), k = d - p;
        return toArray(hash[p + (n - p) % k]);
    }

    private int[] toArray(int h) {
        int n = 8;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[n - i - 1] = h & 1;
            h >>= 1;
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0957PrisonCellsAfterNDays a = new Problem0957PrisonCellsAfterNDays();
//        int[] cells = {0, 1, 0, 1, 1, 0, 0, 1};
//        int[] arr = a.prisonAfterNDays(cells, 7);
//        int i = 0;
//    }
}
