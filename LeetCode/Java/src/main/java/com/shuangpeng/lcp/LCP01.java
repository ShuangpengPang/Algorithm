package com.shuangpeng.lcp;

public class LCP01 {

    public int game(int[] guess, int[] answer) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            count += guess[i] == answer[i] ? 1 : 0;
        }
        return count;
    }
}
