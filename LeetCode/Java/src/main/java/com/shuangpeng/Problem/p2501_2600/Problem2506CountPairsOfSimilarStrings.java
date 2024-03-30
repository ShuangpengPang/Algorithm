package com.shuangpeng.Problem.p2501_2600;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2506CountPairsOfSimilarStrings（统计相似字符串对的数目）
 * @date 2024/3/30 1:42 PM
 */
public class Problem2506CountPairsOfSimilarStrings {

    public int similarPairs(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (String w : words) {
            int h = 0;
            for (char c : w.toCharArray()) {
                h |= 1 << c - 'a';
            }
            ans += map.merge(h, 1, Integer::sum) - 1;
        }
        return ans;
    }
}
