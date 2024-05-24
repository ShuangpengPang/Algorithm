package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0106CompressString（面试题 01.06 字符串压缩）
 * @date 2024/5/24 4:18 PM
 */
public class Question0106CompressString {

    public String compressString(String S) {
        char[] cs = S.toCharArray();
        int n = cs.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; j < n; i = j) {
            while (j < n && cs[i] == cs[j]) {
                j++;
            }
            sb.append(cs[i]).append(j - i);
        }
        return sb.length() < n ? sb.toString() : S;
    }
}
