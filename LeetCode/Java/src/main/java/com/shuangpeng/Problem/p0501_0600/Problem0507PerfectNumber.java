package com.shuangpeng.Problem.p0501_0600;

import java.util.ArrayList;
import java.util.List;

public class Problem0507PerfectNumber {

    public boolean checkPerfectNumber0(int num) {
        List<Integer> list = new ArrayList<>();
        int temp = num;
        for (int i = 2; i * i <= temp; ++i) {
            while (temp % i == 0) {
                list.add(i);
                temp /= i;
            }
        }
        if (temp > 1) {
            list.add(temp);
        }
        List<Integer> ans = new ArrayList<>();
        dfs(list, 0, 1, ans);
        int sum = 0;
        int n = ans.size();
        for (int i = 0; i < n; ++i) {
            sum += ans.get(i);
        }
        return sum == num << 1;
    }

    private void dfs(List<Integer> list, int start, int factor, List<Integer> ans) {
        int n = list.size();
        if (start == n) {
            ans.add(factor);
            return;
        }
        dfs(list, start + 1, factor * list.get(start), ans);
        int i = start;
        while (i < n && list.get(i) == list.get(start)) {
            ++i;
        }
        dfs(list, i, factor, ans);
    }

    public boolean checkPerfectNumber(int num) {
        int sum = 0;
        int s = (int) Math.sqrt(num + 1);
        for (int i = 2; i <= s; ++i) {
            if (num % i == 0) {
                int j = num / i;
                sum += i;
                if (j > i) {
                    sum += j;
                }
            }
        }
        return num == 1 ? false : sum + 1 == num;
    }
}
