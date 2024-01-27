package com.shuangpeng.Problem.p1101_1200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1128NumberOfEquivalentDominoPairs（等价多米诺骨牌对的数量）
 * @date 2024/1/27 7:51 PM
 */
public class Problem1128NumberOfEquivalentDominoPairs {

    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        for (int[] d : dominoes) {
            int x = d[0], y = d[1];
            if (x > y) {
                x = x ^ y;
                y = x ^ y;
                x = x ^ y;
            }
            ans += count.merge(x * 10 + y, 1, Integer::sum) - 1;
        }
        return ans;
    }
}
