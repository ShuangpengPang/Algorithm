package com.shuangpeng.Problem.p0801_0900;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0820ShortEncodingOfWords（单词的压缩编码）
 * @date 2022/11/9 11:36 PM
 */
public class Problem0820ShortEncodingOfWords {

    public int minimumLengthEncoding(String[] words) {
        Set<String> set = new HashSet<>();
        for (String w : words) {
            int m = w.length();
            for (int i = m - 1; i > 0; i--) {
                set.add(w.substring(i));
            }
        }
        int ans = 0;
        for (String w : words) {
            if (set.add(w)) {
                ans += w.length() + 1;
            }
        }
        return ans;
    }
}
