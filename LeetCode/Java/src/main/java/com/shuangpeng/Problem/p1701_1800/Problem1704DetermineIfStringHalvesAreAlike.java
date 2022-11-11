package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1704DetermineIfStringHalvesAreAlike（判断字符串的两半是否相似）
 * @date 2022/11/11 10:00 AM
 */
public class Problem1704DetermineIfStringHalvesAreAlike {

    public boolean halvesAreAlike(String s) {
        int n = s.length(), h = n >> 1;
        int cnt1 = 0, cnt2 = 0;
        String str = "aeiouAEIOU";
        for (int i = 0; i < h; i++) {
            if (str.indexOf(s.charAt(i)) != -1) {
                cnt1++;
            }
            if (str.indexOf(s.charAt(h + i)) != -1) {
                cnt2++;
            }
        }
        return cnt1 == cnt2;
    }
}
