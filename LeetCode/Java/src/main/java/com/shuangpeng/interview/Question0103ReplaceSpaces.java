package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0103ReplaceSpaces（面试题 01.03 URL化）
 * @date 2024/5/24 5:32 PM
 */
public class Question0103ReplaceSpaces {

    public String replaceSpaces(String S, int length) {
        char[] cs = S.toCharArray();
        int n = cs.length, space = 0;
        for (int i = 0; i < length; i++) {
            if (cs[i] == ' ') {
                space++;
            }
        }
        int m = length + (space << 1);
        for (int p = m - 1, i = length - 1; i >= 0; i--) {
            if (cs[i] == ' ') {
                cs[p - 2] = '%';
                cs[p - 1] = '2';
                cs[p] = '0';
                p -= 3;
            } else {
                cs[p--] = cs[i];
            }
        }
        return new String(cs, 0, m);
    }
}
