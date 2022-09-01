package com.shuangpeng.competition.第251到260场周赛.第253场周赛;

public class Problem1963 {

    public int minSwaps(String s) {
        int n = s.length();
        int count = 0, left = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '[') {
                left++;
            } else if (left > 0) {
                left--;
            } else {
                count++;
            }
        }
        return (count + 1) >> 1;
    }
}
