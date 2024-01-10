package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2957RemoveAdjacentAlmostEqualCharacters（消除相邻近似相等字符）
 * @date 2024/1/10 10:40 PM
 */
public class Problem2957RemoveAdjacentAlmostEqualCharacters {

    public int removeAlmostEqualCharacters(String word) {
        int i = 0, cnt = 0, n = word.length();
        while (i < n - 1) {
            if (Math.abs(word.charAt(i) - word.charAt(i + 1)) > 1) {
                i++;
            } else {
                i += 2;
                cnt++;
            }
        }
        return cnt;
    }
}
