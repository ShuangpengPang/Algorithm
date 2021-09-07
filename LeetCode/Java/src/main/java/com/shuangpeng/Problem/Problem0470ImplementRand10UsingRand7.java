package com.shuangpeng.Problem;

public class Problem0470ImplementRand10UsingRand7 {

    public int rand7() {
        return 0;
    }

    public int rand10_0() {
        int row = 0, col = 0;
        do {
            row = rand7() - 1;
            col = rand7();
        } while (row * 7 + col > 40);
        int m = (row * 7 + col) % 10;
        return m == 0 ? 10 : m;
    }

    public int rand10() {
        int row = 0, col = 0, ans = 0;
        while (true) {
            row = rand7() - 1;
            col = rand7();
            ans = row * 7 + col;
            if (ans <= 40) {
                break;
            }
            row = ans - 41;
            col = rand7();
            ans = row * 7 + col;
            if (ans <= 60) {
                break;
            }
            row = ans - 61;
            col = rand7();
            ans = row * 7 + col;
            if (ans <= 20) {
                break;
            }
        }
        return 1 + (ans - 1) % 10;
    }
}
