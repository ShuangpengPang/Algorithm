package com.shuangpeng.Problem.p2901_3000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2961DoubleModularExponentiation（双模幂运算）
 * @date 2024/1/10 11:54 PM
 */
public class Problem2961DoubleModularExponentiation {

    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, n = variables.length; i < n; i++) {
            int a = variables[i][0], b = variables[i][1], c = variables[i][2], m = variables[i][3];
            if (getModule(getModule(a, b, 10), c, m) == target) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int getModule(int num, int p, int m) {
        int ans = 1, v = num;
        while (p > 0) {
            if ((p & 1) == 1) {
                ans = ans * v % m;
            }
            v = v * v % m;
            p >>= 1;
        }
        return ans;
    }
}
