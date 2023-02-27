package com.shuangpeng.Problem.p0601_0700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0649Dota2Senate（Dota2 参议院）
 * @date 2023/2/27 3:58 PM
 */
public class Problem0649Dota2Senate {

    public String predictPartyVictory(String senate) {
        char[] cs = senate.toCharArray();
        int n = cs.length, c1 = 0, c2 = 0;
        for (char c : cs) {
            if (c == 'R') {
                c1++;
            } else {
                c2++;
            }
        }
        while (c1 > 0 && c2 > 0) {
            for (int i = 0, i1 = 0, i2 = 0; i < n && c1 > 0 && c2 > 0; i++) {
                if (cs[i] == '_') {
                    continue;
                }
                int j = cs[i] == 'R' ? i1 : i2;
                char t = cs[i] == 'R' ? 'D' : 'R';
                j = Math.max(j, i + 1);
                while (cs[j % n] != t) {
                    j++;
                }
                cs[j % n] = '_';
                if (cs[i] == 'R') {
                    c2--;
                    i1 = j;
                } else {
                    c1--;
                    i2 = j;
                }
            }
        }
        return c1 > 0 ? "Radiant" : "Dire";
    }
}
