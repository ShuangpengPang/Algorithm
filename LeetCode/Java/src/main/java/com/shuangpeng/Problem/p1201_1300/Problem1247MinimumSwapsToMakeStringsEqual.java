package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1247MinimumSwapsToMakeStringsEqual（交换字符使得字符串相同）
 * @date 2023/2/25 2:18 PM
 */
public class Problem1247MinimumSwapsToMakeStringsEqual {

    public int minimumSwap(String s1, String s2) {
        int n = s1.length(), N = 26;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < n; i++) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i);
            if (c1 == c2) {
                continue;
            }
            if (c1 == 'x') {
                count1++;
            } else {
                count2++;
            }
        }
        if (((count1 + count2) & 1) == 1) {
            return -1;
        }
        return (count1 >> 1) + (count2 >> 1) + (count1 & 1) + (count2 & 1);
    }
}
