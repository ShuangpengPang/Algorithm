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

    public List<String> findAndReplacePattern0(String[] words, String pattern) {
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

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int N = 26;
        int[] map = new int[N];
        Arrays.fill(map, -1);
        int n = pattern.length();
        int[] indices = new int[n];
        for (int i = 0; i < n; ++i) {
            int j = pattern.charAt(i) - 'a';
            if (map[j] == -1) {
                map[j] = i;
            }
            indices[i] = map[j];
        }
        List<String> ans = new ArrayList<>();
        for (String w : words) {
            int[] m = new int[N];
            Arrays.fill(m, -1);
            boolean valid = true;
            for (int i = 0; i < n; ++i) {
                int j = w.charAt(i) - 'a';
                if (m[j] == -1) {
                    m[j] = i;
                }
                if (m[j] != indices[i]) {
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
