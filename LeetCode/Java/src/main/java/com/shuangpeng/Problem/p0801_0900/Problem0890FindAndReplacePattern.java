package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem0890FindAndReplacePattern（查找和替换模式）
 * @Date 2022/6/12 10:09 AM
 * @Version 1.0
 */
public class Problem0890FindAndReplacePattern {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int N = 26;
        List<String> ans = new ArrayList<>();
        int n = pattern.length();
        for (String w : words) {
            int[] m1 = new int[N];
            int[] m2 = new int[N];
            Arrays.fill(m1, -1);
            Arrays.fill(m2, -1);
            boolean valid = true;
            for (int i = 0; i < n; ++i) {
                int cp = pattern.charAt(i) - 'a';
                int cw = w.charAt(i) - 'a';
                if (m1[cp] == -1 && m2[cw] == -1) {
                    m1[cp] = cw;
                    m2[cw] = cp;
                } else if (m1[cp] != cw || m2[cw] != cp) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans.add(w);
            }
        }
        return ans;
    }
}
