package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1541MinimumInsertionsToBalanceAParenthesesString（平衡括号字符串的最少插入次数）
 * @date 2025/4/23 12:13
 */
public class Problem1541MinimumInsertionsToBalanceAParenthesesString {

    public int minInsertions(String s) {
        int ans = 0, v = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if ((v & 1) == 1) {
                    v--;
                    ans++;
                }
                v += 2;
            } else {
                v--;
                if (v < 0) {
                    ans++;
                    v = 1;
                }
            }
        }
        return ans + v;
    }
}

class Problem1541MinimumInsertionsToBalanceAParenthesesString0 {
    public int minInsertions(String s) {
        int insertions = 0;
        int leftCount = 0;
        int length = s.length();
        int index = 0;
        while (index < length) {
            char c = s.charAt(index);
            if (c == '(') {
                leftCount++;
                index++;
            } else {
                if (leftCount > 0) {
                    leftCount--;
                } else {
                    insertions++;
                }
                if (index < length - 1 && s.charAt(index + 1) == ')') {
                    index += 2;
                } else {
                    insertions++;
                    index++;
                }
            }
        }
        insertions += leftCount * 2;
        return insertions;
    }
}
