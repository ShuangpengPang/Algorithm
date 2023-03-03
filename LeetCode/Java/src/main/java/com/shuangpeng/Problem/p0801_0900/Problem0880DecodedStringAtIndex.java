package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0880DecodedStringAtIndex（索引处的解码字符串）
 * @date 2023/3/3 2:22 PM
 */
public class Problem0880DecodedStringAtIndex {

    public String decodeAtIndex(String s, int k) {
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
}
