package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayList;
import java.util.List;

public class Problem1447SimplifiedFractions {

    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j < i; ++j) {
                if (gcd(i, j) == 1) {
                    ans.add(j + "/" + i);
                }
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
