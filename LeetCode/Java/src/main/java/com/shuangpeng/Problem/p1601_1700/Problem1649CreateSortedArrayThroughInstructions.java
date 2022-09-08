package com.shuangpeng.Problem.p1601_1700;

/**
 * @Description: Problem1649CreateSortedArrayThroughInstructions（通过指令创建有序数组）
 * @Date 2022/9/8 3:10 PM
 * @Version 1.0
 */
public class Problem1649CreateSortedArrayThroughInstructions {

    public int createSortedArray(int[] instructions) {
        int n = instructions.length, max = 0, M = (int) 1e9 + 7;
         for (int i : instructions) {
             max = Math.max(max, i);
         }
         int[] cnt = new int[max + 1];
         int ans = 0;
         for (int i : instructions) {
             ans = (ans + Math.min(getCount(cnt, i - 1), getCount(cnt, max) - getCount(cnt, i))) % M;
             while (i <= max) {
                 cnt[i]++;
                 i += i & (-i);
             }
         }
         return ans;
    }

    private int getCount(int[] cnt, int x) {
        int ans = 0;
        while (x > 0) {
            ans += cnt[x];
            x -= x & (-x);
        }
        return ans;
    }
}
