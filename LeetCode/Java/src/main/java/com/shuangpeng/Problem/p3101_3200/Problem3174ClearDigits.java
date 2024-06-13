package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3174ClearDigits（清除数字）
 * @date 2024/6/14 12:09 AM
 */
public class Problem3174ClearDigits {

    public String clearDigits(String s) {
        char[] cs = s.toCharArray();
        int cnt = 0;
        for (char c : cs) {
            if (c >= '0' && c <= '9') {
                cnt = Math.max(0, cnt - 1);
            } else {
                cs[cnt++] = c;
            }
        }
        return new String(cs, 0, cnt);
    }
}
