package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0925LongPressedName（长按键入）
 * @date 2024/1/11 9:59 AM
 */
public class Problem0925LongPressedName {

    public boolean isLongPressedName0(String name, String typed) {
        int n1 = name.length(), n2 = typed.length();
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            char c = name.charAt(i), c1 = typed.charAt(j);
            if (c != c1) {
                return false;
            }
            int x = i + 1, y = j + 1;
            while (x < n1 && name.charAt(x) == c) {
                x++;
            }
            while (y < n2 && typed.charAt(y) == c) {
                y++;
            }
            if (x - i > y - j) {
                return false;
            }
            i = x;
            j = y;
        }
        return i == n1 && j == n2;
    }

    public boolean isLongPressedName(String name, String typed) {
        int n1 = name.length(), n2 = typed.length();
        int i = 0;
        for (int j = 0; j < n2; j++) {
            if (i < n1 && name.charAt(i) == typed.charAt(j)) {
                i++;
            } else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1)) {
                return false;
            }
        }
        return i == n1;
    }
}
