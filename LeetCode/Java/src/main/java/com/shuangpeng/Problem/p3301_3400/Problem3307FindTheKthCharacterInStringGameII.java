package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3307FindTheKthCharacterInStringGameII（找出第K个字符II）
 * @date 2024/11/26 5:01 PM
 */
public class Problem3307FindTheKthCharacterInStringGameII {

    public char kthCharacter0(long k, int[] operations) {
        return dfs(k, Long.SIZE - Long.numberOfLeadingZeros(k - 1), operations);
    }

    private char dfs(long k, int n, int[] operations) {
        if (n == 0) {
            return 'a';
        }
        long half = 1L << n - 1;
        int cnt = 0;
        if (k > half) {
            k -= half;
            cnt += operations[n - 1];
        }
        return (char) ('a' + (cnt + dfs(k, n - 1, operations) - 'a') % 26);
    }

    public char kthCharacter1(long k, int[] operations) {
        int c = 0;
        for (int i = Long.SIZE - Long.numberOfLeadingZeros(k - 1); i > 0; i--) {
            long num = 1L << i - 1;
            if (k > num) {
                k -= num;
                c += operations[i - 1];
            }
        }
        return (char) ('a' + (c % 26));
    }

    public char kthCharacter(long k, int[] operations) {
        k--;
        int cnt = 0;
        for (int i = 0; k != 0; k >>= 1, i++) {
            if ((k & 1) == 1) {
                cnt += operations[i];
            }
        }
        return (char) ('a' + cnt % 26);
    }
}
