package com.shuangpeng.competition.第251到260场周赛.第252场周赛;

public class Problem1952 {

    public boolean isThree0(int n) {
        if (n == 1) {
            return false;
        }
        int count = 2;
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                count++;
            }
            if (count > 3) {
                break;
            }
        }
        return count == 3;
    }

    public boolean isThree(int n) {
        int count = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                if (i != n / i) {
                    count += 2;
                } else {
                    count++;
                }
                if (count > 3) {
                    return false;
                }
            }
        }
        return count == 3;
    }
}
