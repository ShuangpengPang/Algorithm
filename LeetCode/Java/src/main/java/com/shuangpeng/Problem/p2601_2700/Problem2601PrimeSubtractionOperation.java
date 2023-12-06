package com.shuangpeng.Problem.p2601_2700;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2601PrimeSubtractionOperation（质数减法运算）
 * @date 2023/12/6 11:40 PM
 */
public class Problem2601PrimeSubtractionOperation {

    private static final int N = 1000;
    private static final boolean[] visited = new boolean[N + 1];
    private static final List<Integer> primes = new ArrayList<>();
    static {
        for (int i = 2; i <= N; i++) {
            if (!visited[i]) {
                primes.add(i);
            }
            int size = primes.size();
            for (int j = 0; j < size && i * primes.get(j) <= N; j++) {
                visited[i * primes.get(j)] = true;
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
    }

    public boolean primeSubOperation(int[] nums) {
        for (int i = nums.length - 2; i >= 0; i--) {
            int num = nums[i];
            if (num >= nums[i + 1]) {
                int p = getPrime(num - nums[i + 1], num);
                if (p == -1) {
                    return false;
                }
                num -= p;
            }
            nums[i] = num;
        }
        return true;
    }

    private int getPrime(int num, int max) {
        int left = 0, right = primes.size() - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (primes.get(mid) <= num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left < primes.size() && primes.get(left) < max ? primes.get(left) : -1;
    }
}
