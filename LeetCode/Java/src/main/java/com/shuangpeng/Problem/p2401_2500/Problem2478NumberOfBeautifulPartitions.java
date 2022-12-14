package com.shuangpeng.Problem.p2401_2500;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2478NumberOfBeautifulPartitions（完美分割的方案数）
 * @date 2022/12/14 3:45 PM
 */
public class Problem2478NumberOfBeautifulPartitions {

    public int beautifulPartitions(String s, int k, int minLength) {
        int n = s.length(), M = (int) 1e9 + 7;
        String prime = "2357";
        if (prime.indexOf(s.charAt(0)) == -1 || prime.indexOf(s.charAt(n - 1)) != -1) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            if (prime.indexOf(s.charAt(i)) == -1 && prime.indexOf(s.charAt(i + 1)) != -1) {
                list.add(i);
            }
        }
        list.add(n - 1);
        int m = list.size();
        int[] dp = new int[m], tmp = new int[m];
        for (int i = 0; i < m; i++) {
            if (list.get(i) + 1 >= minLength) {
                dp[i] = 1;
            }
        }
        for (int i = 2; i <= k; i++) {
            for (int j = 0, p = 0, sum = 0; j < m; j++) {
                while (list.get(p) <= list.get(j) - minLength) {
                    sum = (sum + dp[p]) % M;
                    p++;
                }
                tmp[j] = sum;
            }
            int[] t = dp;
            dp = tmp;
            tmp = t;
        }
        return dp[m - 1];
    }
}
