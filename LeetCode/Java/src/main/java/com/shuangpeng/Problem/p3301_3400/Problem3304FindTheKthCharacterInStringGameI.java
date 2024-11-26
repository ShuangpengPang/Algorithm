package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3304FindTheKthCharacterInStringGameI（找出第K个字符I）
 * @date 2024/11/21 5:13 PM
 */
public class Problem3304FindTheKthCharacterInStringGameI {

    public char kthCharacter0(int k) {
        StringBuilder sb = new StringBuilder("a");
        while (sb.length() < k) {
            int n = sb.length();
            for (int i = 0; i < n && sb.length() < k; i++) {
                sb.append((char) ((sb.charAt(i) - 'a' + 1) % 26 + 'a'));
            }
        }
        return sb.charAt(sb.length() - 1);
    }

    public char kthCharacter(int k) {
        int cnt = 0;
        while (k != 0) {
            int p = (int) (Math.log(k) / Math.log(2));
            int num = (int) Math.pow(2, p);
            int m = k % num;
            cnt += m == 0 ? p : 1;
            k = m;
        }
        return (char) ('a' + cnt % 26);
    }
}

class Problem3304FindTheKthCharacterInStringGameI0 {

    public char kthCharacter(int k) {
        return dfs(k, 1 << Integer.SIZE - Integer.numberOfLeadingZeros(k - 1));
    }

    private char dfs(int k, int num) {
        if (k == 1) {
            return 'a';
        }
        int cnt = 0;
        if (k > num) {
            cnt++;
            k -= num;
        }
        return (char) (cnt + dfs(k, num >> 1));
    }
}

class Problem3304FindTheKthCharacterInStringGameI1 {

    public char kthCharacter(int k) {
        return (char) ('a' + Integer.bitCount(k - 1));
    }
}

class Solution {
    public char kthCharacter(int k) {
        k--;
        char ans = 'a';
        for (int i = 31 - Integer.numberOfLeadingZeros(k); i >= 0; i--) {
            if (k >= (1 << i)) { // k 在右半边
                ans++;
                k -= (1 << i);
            }
        }
        return ans;
    }
}
