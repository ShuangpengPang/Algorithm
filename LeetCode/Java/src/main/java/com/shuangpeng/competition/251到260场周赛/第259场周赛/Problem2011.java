package com.shuangpeng.competition.第259场周赛;

public class Problem2011 {

    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for (String s : operations) {
            if (s.equals("++X") || s.equals("X++")) {
                ++ans;
            } else {
                --ans;
            }
        }
        return ans;
    }
}
