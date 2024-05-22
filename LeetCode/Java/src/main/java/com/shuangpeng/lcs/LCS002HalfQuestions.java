package com.shuangpeng.lcs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCS002HalfQuestions（完成一半题目）
 * @date 2024/5/22 11:02 AM
 */
public class LCS002HalfQuestions {

    public int halfQuestions0(int[] questions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int q : questions) {
            map.merge(q, 1, Integer::sum);
        }
        int n = map.size(), index = 0;
        int[] cnt = new int[n];
        for (int c : map.values()) {
            cnt[index++] = c;
        }
        Arrays.sort(cnt);
        int m = questions.length >> 1, types = 0;
        int p = n - 1;
        while (m > 0) {
            types++;
            m -= cnt[p--];
        }
        return types;
    }

    public int halfQuestions(int[] questions) {
        int max = 0;
        for (int q : questions) {
            max = Math.max(max, q);
        }
        int[] cnt = new int[max + 1];
        for (int q : questions) {
            cnt[q]++;
        }
        Arrays.sort(cnt);
        int index = max, n = questions.length >> 1, types = 0;
        while (n > 0) {
            types++;
            n -= cnt[index--];
        }
        return types;
    }
}
