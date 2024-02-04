package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1309DecryptStringFromAlphabetToIntegerMapping（解码字母到整数映射）
 * @date 2024/2/4 12:20 AM
 */
public class Problem1309DecryptStringFromAlphabetToIntegerMapping {

    public String freqAlphabets0(String s) {
        char[] cs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int i = cs.length - 1;
        while (i >= 0) {
            char c = cs[i];
            if (c == '#') {
                sb.append((char) ((cs[i - 2] - '0') * 10 + cs[i - 1] - '1' + 'a'));
                i -= 3;
            } else {
                sb.append((char) (cs[i] - '1' + 'a'));
                i--;
            }
        }
        return sb.reverse().toString();
    }

    public String freqAlphabets(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < n) {
            if (i + 2 < n && cs[i + 2] == '#') {
                sb.append((char) ((cs[i] - '0') * 10 + cs[i + 1] - '1' + 'a'));
                i += 3;
            } else {
                sb.append((char) (cs[i] - '1' + 'a'));
                i++;
            }
        }
        return sb.toString();
    }
}
