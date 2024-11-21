package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3304FindTheKthCharacterInStringGameI（找出第K个字符I）
 * @date 2024/11/21 5:13 PM
 */
public class Problem3304FindTheKthCharacterInStringGameI {

    public char kthCharacter(int k) {
        StringBuilder sb = new StringBuilder("a");
        while (sb.length() < k) {
            int n = sb.length();
            for (int i = 0; i < n && sb.length() < k; i++) {
                sb.append((char) ((sb.charAt(i) - 'a' + 1) % 26 + 'a'));
            }
        }
        return sb.charAt(sb.length() - 1);
    }
}
