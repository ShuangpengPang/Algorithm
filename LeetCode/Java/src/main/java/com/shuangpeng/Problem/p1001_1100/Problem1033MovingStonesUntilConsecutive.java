package com.shuangpeng.Problem.p1001_1100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1033MovingStonesUntilConsecutive（移动石子直到连续）
 * @date 2023/4/30 4:18 PM
 */
public class Problem1033MovingStonesUntilConsecutive {

    public int[] numMovesStones0(int a, int b, int c) {
        int[] arr = {a, b, c};
        Arrays.sort(arr);
        int min = 2;
        if (arr[2] - arr[0] == 2) {
            min = 0;
        } else if (arr[1] - arr[0] <= 2 || arr[2] - arr[1] <= 2) {
            min = 1;
        }
        return new int[]{min, arr[2] - arr[0] - 2};
    }

    public int[] numMovesStones(int a, int b, int c) {
        int x = Math.min(a, Math.min(b, c));
        int z = Math.max(a, Math.max(b, c));
        int y = a + b + c - x - z;
        int max = z - x - 2, min = max;
        min = Math.min(min, Math.min(y - x, z - y) <= 2 ? 1 : 2);
        return new int[]{min, max};
    }
}
