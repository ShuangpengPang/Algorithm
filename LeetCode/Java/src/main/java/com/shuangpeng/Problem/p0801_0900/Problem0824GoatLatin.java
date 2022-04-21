package com.shuangpeng.Problem.p0801_0900;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem0824GoatLatin
 * @Date 2022/4/21 10:13 AM
 * @Version 1.0
 */
public class Problem0824GoatLatin {

    public String toGoatLatin0(String sentence) {
        String[] strs = sentence.split(" ");
        Set<Character> set = new HashSet<Character>() {{
            add('a');
            add('e');
            add('i');
            add('o');
            add('u');
            add('A');
            add('E');
            add('I');
            add('O');
            add('U');
        }};
        int n = strs.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                sb.append(' ');
            }
            char c = strs[i].charAt(0);
            if (set.contains(c)) {
                sb.append(strs[i]).append("ma");
            } else {
                sb.append(strs[i].substring(1)).append(c).append("ma");
            }
            for (int j = 0; j <= i; ++j) {
                sb.append('a');
            }
        }
        return sb.toString();
    }

    public String toGoatLatin(String sentence) {
        StringBuilder sb = new StringBuilder();
        int n = sentence.length();
        int idx = 0;
        String vowels = "aeiouAEIOU";
        for (int i = 0, j = 0; j <= n; ++j) {
            if (j < n && sentence.charAt(j) != ' ') {
                if (i != j || vowels.indexOf(sentence.charAt(i)) != -1) {
                    sb.append(sentence.charAt(j));
                }
            } else {
                ++idx;
                char c = sentence.charAt(i);
                if (vowels.indexOf(c) == -1) {
                    sb.append(c);
                }
                sb.append("ma");
                for (int k = 0; k < idx; ++k) {
                    sb.append('a');
                }
                if (j < n) {
                    sb.append(' ');
                }
                i = j + 1;
            }
        }
        return sb.toString();
    }
}
