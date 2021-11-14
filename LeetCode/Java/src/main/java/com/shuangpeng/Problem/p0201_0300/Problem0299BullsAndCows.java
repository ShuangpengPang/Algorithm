package com.shuangpeng.Problem.p0201_0300;

public class Problem0299BullsAndCows {

    public String getHint(String secret, String guess) {
        int n = secret.length();
        int bulls = 0, cows = 0;
        int[] counts = new int[10];
        for (int i = 0; i < n; ++i) {
            int d1 = secret.charAt(i) - '0';
            int d2 = guess.charAt(i) - '0';
            if (d1 == d2) {
                ++bulls;
            } else {
                if (counts[d1] < 0) {
                    ++cows;
                }
                if (counts[d2] > 0) {
                    ++cows;
                }
                ++counts[d1];
                --counts[d2];
            }
        }
        return bulls + "A" + cows + "B";
    }
}
