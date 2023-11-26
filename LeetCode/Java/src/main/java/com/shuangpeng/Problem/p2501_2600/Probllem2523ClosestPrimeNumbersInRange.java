package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Probllem2523ClosestPrimeNumbersInRange（范围内最接近的两个质数）
 * @date 2023/11/26 6:08 PM
 */
public class Probllem2523ClosestPrimeNumbersInRange {

    private static final int N = (int) 1e6;
    private static boolean[] visited = new boolean[N + 1];
    private static List<Integer> primes = new ArrayList<>();
    static {
        for (int i = 2; i <= N; i++) {
            if (!visited[i]) {
                primes.add(i);
            }
            for (int p : primes) {
                if ((long) i * p > N) {
                    break;
                }
                visited[i * p] = true;
                if (i % p == 0) {
                    break;
                }
            }
        }
    }

    public int[] closestPrimes(int left, int right) {
        int index = getIndex(left), diff = N, n = primes.size();
        int[] ans = new int[]{-1, -1};
        for (int i = index + 1; i < n && primes.get(i) <= right; i++) {
            if (primes.get(i) - primes.get(i - 1) < diff) {
                diff = primes.get(i) - primes.get(i - 1);
                ans[0] = primes.get(i - 1);
                ans[1] = primes.get(i);
            }
        }
        return ans;
    }

    private int getIndex(int num) {
        int left = 0, right = primes.size() - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (primes.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
