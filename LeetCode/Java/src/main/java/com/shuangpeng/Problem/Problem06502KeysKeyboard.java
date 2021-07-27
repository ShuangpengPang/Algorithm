package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem06502KeysKeyboard {

    final static int MAX = 1000;
    final static List<Integer> primes = getPrimes(MAX);

    private static List<Integer> getPrimes(int m) {
        List<Integer> list = new ArrayList<>();
        if (m < 2) {
            return list;
        }
        list.add(2);
        if (m == 2) {
            return list;
        }
        for (int i = 3; i <= m; i += 2) {
            int size = list.size();
            boolean isPrime = true;
            for (int j = 0; j < size; j++) {
                int p = list.get(j);
                if (i % p == 0) {
                    isPrime = false;
                    break;
                }
                if (p * p > i) {
                    break;
                }
            }
            if (isPrime) {
                list.add(i);
            }
        }
        return list;
    }

    public int minSteps0(int n) {
        int size = primes.size();
        Map<Integer, Integer> map = new HashMap<>();
        while (n > 1) {
            for (int i = 0; i < size; i++) {
                int p = primes.get(i);
                if (n % p == 0) {
                    map.put(p, map.getOrDefault(p, 0) + 1);
                    n /= p;
                    break;
                }
            }
        }
        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            sum += entry.getKey() * entry.getValue();
        }
        return sum;
    }

    public int minSteps1(int n) {
        int sum = 0;
        while (n > 1) {
            for (int i = 2; i <= n; i++) {
                if (n % i == 0) {
                    sum += i;
                    n /= i;
                    break;
                }
                if (i * i > n) {
                    sum += n;
                    n = 1;
                    break;
                }
            }
        }
        return sum;
    }

    public int minSteps(int n) {
        int sum = 0;
        int d = 2;
        while (n > 1) {
            if (d * d > n) {
                sum += n;
                break;
            }
            while (n % d == 0) {
                sum += d;
                n /= d;
            }
            d++;
        }
        return sum;
    }
}
