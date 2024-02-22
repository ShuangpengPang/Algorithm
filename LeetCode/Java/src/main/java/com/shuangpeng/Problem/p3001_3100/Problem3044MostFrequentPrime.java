package com.shuangpeng.Problem.p3001_3100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3044MostFrequentPrime（出现频率最高的质数）
 * @date 2024/2/22 6:05 PM
 */
public class Problem3044MostFrequentPrime {

    private static Set<Integer> primes = new HashSet<>();

    private static int[][] dirs = {{0, 1}, {1, 1}, {1, 0}, {1, -1}};

    private int maxFreq = 0, ans = -1;

    public int mostFrequentPrime(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Map<Integer, Integer> freq = new HashMap<>();
        maxFreq = 0;
        ans = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] d : dirs) {
                    int num1 = mat[i][j], num2 = num1, unit = 10;
                    for (int x = i + d[0], y = j + d[1]; x >= 0 && x < m && y >= 0 && y < n; x += d[0], y += d[1]) {
                        num1 = num1 * 10 + mat[x][y];
                        num2 += mat[x][y] * unit;
                        addPrime(freq, num1);
                        addPrime(freq, num2);
                        unit *= 10;
                    }
                }
            }
        }
        return ans;
    }

    private void addPrime(Map<Integer, Integer> freq, int num) {
        if (primes.contains(num) || check(num)) {
            primes.add(num);
            int f = freq.merge(num, 1, Integer::sum);
            if (f > maxFreq || f == maxFreq && num > ans) {
                maxFreq = f;
                ans = num;
            }
        }
    }

    private boolean check(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
