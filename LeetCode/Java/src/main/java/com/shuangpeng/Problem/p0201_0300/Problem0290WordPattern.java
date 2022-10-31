package com.shuangpeng.Problem.p0201_0300;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem0290WordPattern（单词规律）
 * @Date 2022/10/31 3:40 PM
 * @Version 1.0
 */
public class Problem0290WordPattern {

    public boolean wordPattern(String pattern, String s) {
        String[] map = new String[26];
        Set<String> set = new HashSet<>();
        int idx = 0, n = s.length(), m = pattern.length();
        for (int l = 0, r = 0; r <= n; r++) {
            if (r == n || s.charAt(r) == ' ') {
                if (idx == m) {
                    return false;
                }
                int j = pattern.charAt(idx++) - 'a';
                String str = s.substring(l, r);
                if (map[j] == null) {
                    map[j] = str;
                    if (!set.add(str)) {
                        return false;
                    }
                } else if (!map[j].equals(str)) {
                    return false;
                }
                l = r + 1;
            }
        }
        return idx == m;
    }
}
