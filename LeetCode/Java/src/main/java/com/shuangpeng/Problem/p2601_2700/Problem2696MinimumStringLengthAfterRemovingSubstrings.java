package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2696MinimumStringLengthAfterRemovingSubstrings（删除子串后的最小字符串长度）
 * @date 2023/8/15 12:08 PM
 */
public class Problem2696MinimumStringLengthAfterRemovingSubstrings {

    public int minLength(String s) {
        StringBuilder sb = new StringBuilder();
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c == 'B' || c == 'D') && len > 0 && sb.charAt(len - 1) == (char) (c - 1)) {
                sb.setLength(--len);
            } else {
                sb.append(c);
                len++;
            }
        }
        return len;
    }
}
