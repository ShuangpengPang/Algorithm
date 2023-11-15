package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2217FindPalindromeWithFixedLength（找到指定长度的回文数）
 * @date 2023/11/14 6:35 PM
 */
public class Problem2217FindPalindromeWithFixedLength {

    private static long[] count = new long[16];
    static {
        count[0] = count[1] = count[2] = 10;
        for (int i = 3; i <= 15; i++) {
            count[i] = count[i - 2] * 10;
        }
    }

    public long[] kthPalindrome(int[] queries, int intLength) {
        int n = queries.length;
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            ans[i] = getLong(queries[i], intLength);
        }
        return ans;
    }

    private long getLong(int q, int intLength) {
        if (intLength <= 2) {
            if (q > 9) {
                return -1;
            }
            return intLength == 1 ? q : q * 11;
        }
        int[] arr = new int[intLength];
        if (q > count[intLength] - count[intLength - 2]) {
            return -1;
        }
        q--;
        arr[0] = arr[intLength - 1] = (int) ((q + count[intLength - 2]) / count[intLength - 2]);
        q = (int) (q % count[intLength - 2]);
        int i = 1, j = intLength - 2, length = intLength - 4;
        while (i + 1 < j && q > 0) {
            arr[i] = arr[j] = (int) (q / count[length]);
            q = (int) (q % count[length]);
            length -= 2;
            i++;
            j--;
        }
        arr[i] = arr[j] = q;
        long ans = 0;
        for (int idx = 0; idx < intLength; idx++) {
            ans = ans * 10 + arr[idx];
        }
        return ans;
    }
}
