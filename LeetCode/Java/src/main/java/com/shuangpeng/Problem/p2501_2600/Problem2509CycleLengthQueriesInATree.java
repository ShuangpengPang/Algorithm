package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2509CycleLengthQueriesInATree（查询树中环的长度）
 * @date 2024/1/18 12:53 PM
 */
public class Problem2509CycleLengthQueriesInATree {

    public int[] cycleLengthQueries0(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            List<Integer> list1 = new ArrayList<>(n), list2 = new ArrayList<>(n);
            for (int num = a; num > 0; num >>= 1) {
                list1.add(num);
            }
            for (int num = b; num > 0; num >>= 1) {
                list2.add(num);
            }
            int j = list1.size() - 1, k = list2.size() - 1;
            while (j >= 0 && k >= 0 && list1.get(j).equals(list2.get(k))) {
                j--;
                k--;
            }
            ans[i] = j + k + 3;
        }
        return ans;
    }

    public int[] cycleLengthQueries1(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            ans[i] = 1;
            while (a != b) {
                if (a > b) {
                    a >>= 1;
                } else {
                    b >>= 1;
                }
                ans[i]++;
            }
        }
        return ans;
    }

    public int[] cycleLengthQueries2(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            int j = n, k = n;
            while ((a & (1 << j)) == 0) {
                j--;
            }
            while ((b & (1 << k)) == 0) {
                k--;
            }
            while (j >= 0 && k >= 0 && ((a >> j) & 1) == ((b >> k) & 1)) {
                j--;
                k--;
            }
            ans[i] = j + k + 3;
        }
        return ans;
    }

    public int[] cycleLengthQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            if (a > b) {
                a = a ^ b;
                b = a ^ b;
                a = a ^ b;
            }
            int d = Integer.numberOfLeadingZeros(a) - Integer.numberOfLeadingZeros(b);
            ans[i] = d + (32 - Integer.numberOfLeadingZeros((a ^ (b >> d)))) * 2 + 1;
        }
        return ans;
    }
}
