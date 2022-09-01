package com.shuangpeng.competition.第261到270场周赛.第261场周赛;

public class Problem2027 {

    // 比赛时写法
    public int minimumMoves(String s) {
        int n = s.length();
        int ans = 0;
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == 'X') {
                ++ans;
                i += 3;
            } else {
                ++i;
            }
        }
        return ans;
    }
}
