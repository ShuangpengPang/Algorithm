package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0990SatisfiabilityOfEqualityEquations（等式方程的可满足性）
 * @date 2023/4/3 4:26 PM
 */
public class Problem0990SatisfiabilityOfEqualityEquations {

    public boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        Arrays.setAll(parent, i -> i);
        for (String s : equations) {
            if (s.charAt(1) == '=') {
                union(parent, s.charAt(0) - 'a', s.charAt(3) - 'a');
            }
        }
        for (String s : equations) {
            if (s.charAt(1) == '!') {
                if (find(parent, s.charAt(0) - 'a') == find(parent, s.charAt(3) - 'a')) {
                    return false;
                }
            }
        }
        return true;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
        }
    }
}
