package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3233FindTheCountOfNumbersWhichAreNotSpecial（统计不是特殊数字的数字数量）
 * @date 2024/10/9 2:46 PM
 */
public class Problem3233FindTheCountOfNumbersWhichAreNotSpecial {

    private static final int N = 4 * 10000;
    private static int count = 0;
    private static final int[] primes = new int[8 * 1000];
    private static final boolean[] visited = new boolean[N];

    static {
        for (int i = 2; i < N; i++) {
            if (!visited[i]) {
                primes[count++] = i;
            }
            for (int j = 0; j < count && primes[j] <= i && primes[j] * i < N; j++) {
                visited[i * primes[j]] = true;
            }
        }
    }

    public int nonSpecialCount(int l, int r) {
        return r - l + 1 - findIndex(r + 1) + findIndex(l);
    }

    private int findIndex(int n) {
        int left = 0, right = count - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (primes[mid] * primes[mid] < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
