package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2965FindMissingAndRepeatedValues（找出缺失和重复的数字）
 * @date 2024/4/15 4:47 PM
 */
public class Problem2965FindMissingAndRepeatedValues {

    public int[] findMissingAndRepeatedValues0(int[][] grid) {
        int n = grid.length, N = n * n;
        boolean[] visited = new boolean[N + 1];
        int a = 0, sum = 0;
        for (int[] g : grid) {
            for (int num : g) {
                if (visited[num]) {
                    a = num;
                } else {
                    visited[num] = true;
                }
                sum += num;
            }
        }
        return new int[]{a, a + ((N + 1) * N >> 1) - sum};
    }

    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length, N = n * n, xor = 0;
        for (int[] r : grid) {
            for (int num : r) {
                xor ^= num;
            }
        }
        xor ^= (N & 1) == 0 ? N : 1;
        int lowBit = xor & -xor,sum1 = 0, sum2 = 0;
        for (int[] r : grid) {
            for (int num : r) {
                if ((num & lowBit) == 0) {
                    sum1 ^= num;
                } else {
                    sum2 ^= num;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if ((i & lowBit) == 0) {
                sum1 ^= i;
            } else {
                sum2 ^= i;
            }
        }
        for (int[] r : grid) {
            for (int num : r) {
                if (num == sum1) {
                    return new int[]{sum1, sum2};
                } else if (num == sum2) {
                    return new int[]{sum2, sum1};
                }
            }
        }
        return new int[2];
    }
}
