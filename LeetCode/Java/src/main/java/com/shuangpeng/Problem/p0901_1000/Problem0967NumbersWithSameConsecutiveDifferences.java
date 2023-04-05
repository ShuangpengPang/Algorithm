package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0967NumbersWithSameConsecutiveDifferences（连续差相同的数字）
 * @date 2023/4/5 4:40 PM
 */
public class Problem0967NumbersWithSameConsecutiveDifferences {

    List<Integer> ans;
    int n, k;

    public int[] numsSameConsecDiff0(int n, int k) {
        ans = new ArrayList<>();
        this.n = n;
        this.k = k;
        dfs(0, 0);
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private void dfs(int num, int index) {
        if (index == n) {
            ans.add(num);
            return;
        }
        if (index == 0) {
            for (int i = 1; i < 10; i++) {
                dfs(i, 1);
            }
            return;
        }
        int d = num % 10;
        num *= 10;
        if (d - k >= 0) {
            dfs(num + d - k, index + 1);
        }
        if (k != 0 && d + k < 10) {
            dfs(num + d + k, index + 1);
        }
    }

    public int[] numsSameConsecDiff(int n, int k) {
        if (k == 0) {
            int num = ((int) Math.pow(10, n) - 1) / 9;
            int[] ans = new int[9];
            for (int i = 0; i < 9; i++) {
                ans[i] = (i + 1) * num;
            }
            return ans;
        }
        int N = 1 << (n - 1);
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < N; j++) {
                int num = i, last = i % 10;
                boolean valid = true;
                for (int b = n - 2; b >= 0; b--) {
                    last += ((((j >> b) & 1) << 1) - 1) * k;
                    if (last < 0 || last > 9) {
                        valid = false;
                        break;
                    }
                    num = num * 10 + last;
                }
                if (valid) {
                    ans.add(num);
                }
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
