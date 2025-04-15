package com.shuangpeng.Problem.p3401_3500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3412FindMirrorScoreOfAString（计算字符串的镜像分数）
 * @date 2025/4/15 14:13
 */
public class Problem3412FindMirrorScoreOfAString {

    public long calculateScore(String s) {
        List<Integer>[] indices = new List[26];
        Arrays.setAll(indices, i -> new ArrayList<>());
        long ans = 0;
        char[] cs = s.toCharArray();
        for (int i = 0, n = cs.length; i < n; i++) {
            int c1 = cs[i] - 'a', c2 = 25 - c1;
            if (!indices[c2].isEmpty()) {
                ans += i - indices[c2].remove(indices[c2].size() - 1);
            } else {
                indices[c1].add(i);
            }
        }
        return ans;
    }
}
