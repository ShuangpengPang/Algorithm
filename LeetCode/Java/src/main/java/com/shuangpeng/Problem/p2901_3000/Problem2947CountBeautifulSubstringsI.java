package com.shuangpeng.Problem.p2901_3000;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2947CountBeautifulSubstringsI（统计美丽子字符串I）
 * @date 2024/1/10 12:00 AM
 */
public class Problem2947CountBeautifulSubstringsI {

    private static final Set<Character> set = new HashSet<>();
    static {
        for (char c : new char[]{'a', 'e', 'i', 'o', 'u'}) {
            set.add(c);
        }
    }

    public int beautifulSubstrings(String s, int k) {
        char[] cs = s.toCharArray();
        int ans = 0;
        for (int i = 0, n = cs.length; i < n; i++) {
            int cnt = 0;
            for (int j = i + 1; j < n; j += 2) {
                if (set.contains(cs[j - 1])) {
                    cnt++;
                }
                if (set.contains(cs[j])) {
                    cnt++;
                }
                if (cnt == j - i + 1 >> 1 && cnt * cnt % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
