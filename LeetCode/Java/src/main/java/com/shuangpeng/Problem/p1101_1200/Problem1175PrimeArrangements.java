package com.shuangpeng.Problem.p1101_1200;

/**
 * @Description: Problem1175PrimeArrangements（质数排列）
 * @Date 2022/6/30 10:59 AM
 * @Version 1.0
 */
public class Problem1175PrimeArrangements {

    static int M = (int) 1e9 + 7;
    static int N = 101;
    static int[] arr = new int[N];
    static {
        arr[0] = 1;
        for (int i = 1; i < N; i++) {
            arr[i] = (int) ((long) arr[i - 1] * i % M);
        }
    }

    public int numPrimeArrangements(int n) {
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                cnt++;
            }
        }
        return (int) ((long) arr[n - cnt] * arr[cnt] % M);
    }

    private boolean isPrime(int n) {
        boolean isValid = true;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
