package com.shuangpeng.Problem.p0301_0400;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem0386LexicographicalNumbers
 * @Date 2022/4/18 11:56 AM
 * @Version 1.0
 */
public class Problem0386LexicographicalNumbers {

    public List<Integer> lexicalOrder0(int n) {
        List<Integer> ans = new ArrayList<>();
        int i = 1;
        do {
            while (i <= n) {
                ans.add(i);
                i *= 10;
            }
            i /= 10;
            ++i;
            while (i % 10 == 0) {
                i /= 10;
            }
        } while (i != 1);
        return ans;
    }

    public List<Integer> lexicalOrder1(int n) {
        List<Integer> ans = new ArrayList<>();
        int num = 1;
        for (int i = 0; i < n;) {
            if (num <= n) {
                ans.add(num);
                num *= 10;
                ++i;
            } else {
                while (num > n || num % 10 == 9) {
                    num /= 10;
                }
                ++num;
            }
        }
        return ans;
    }

    public List<Integer> lexicalOrder2(int n) {
        List<Integer> ans = new ArrayList<>(n);
        int num = 1;
        for (int i = 0; i < n; ++i) {
            ans.add(num);
            if (num * 10 <= n) {
                num *= 10;
            } else {
                while (num + 1 > n || num % 10 == 9) {
                    num /= 10;
                }
                ++num;
            }
        }
        return ans;
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>(n);
        for (int i = 1; i < 10; ++i) {
            dfs(i, n, ans);
        }
        return ans;
    }

    private void dfs(int num, int n, List<Integer> ans) {
        if (num > n) {
            return;
        }
        ans.add(num);
        for (int i = 0; i < 10; ++i) {
            dfs(num * 10 + i, n, ans);
        }
    }
}
