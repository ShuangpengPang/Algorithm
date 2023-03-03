package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0880DecodedStringAtIndex（索引处的解码字符串）
 * @date 2023/3/3 2:22 PM
 */
public class Problem0880DecodedStringAtIndex {

    public String decodeAtIndex0(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        char last = 'a';
        while (k > 0) {
            long count = 0;
            for (int i = 0; i < n; i++) {
                if (cs[i] >= 'a' && cs[i] <= 'z') {
                    last = cs[i];
                    if (++count == k) {
                        return String.valueOf(last);
                    }
                } else {
                    long cnt = count * (cs[i] - '0');
                    if (k <= cnt) {
                        k %= count;
                        break;
                    } else {
                        count = cnt;
                    }
                }
            }
        }
        return String.valueOf(last);
    }

    // 逆向工作
    public String decodeAtIndex(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        long size = 0L;
        for (char c : cs) {
            if (c >= 'a' && c <= 'z') {
                size++;
            } else {
                size *= c - '0';
            }
        }
        for (int i = n - 1; i>= 0; i--) {
            char c = cs[i];
            k %= size;
            if (k == 0 && c >= 'a' && c <= 'z') {
                return String.valueOf(c);
            }
            if (c >= 'a' && c <= 'z') {
                size--;
            } else {
                size /= c - '0';
            }
        }
        return null;
    }
}
