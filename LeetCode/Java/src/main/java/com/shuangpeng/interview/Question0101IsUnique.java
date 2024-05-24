package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0101IsUnique（面试题 01.01 判定字符是否唯一）
 * @date 2024/5/24 6:59 PM
 */
public class Question0101IsUnique {

    public boolean isUnique(String astr) {
        if (astr.length() > 26) {
            return false;
        }
        int mask = 0;
        char[] cs = astr.toCharArray();
        for (char c : cs) {
            int i = c - 'a';
            if (((mask >> i) & 1) == 1) {
                return false;
            }
            mask |= 1 << i;
        }
        return true;
    }
}
