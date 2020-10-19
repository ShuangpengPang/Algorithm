package com.shuangpeng;

public class Problem0650TwoKeysKeyboard {

    public int minSteps(int n) {
        if (n <= 1) {
            return 0;
        }
        int sum = 0;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                n /= i;
                i = 1;
            }
        }
        if (n > 1) {
            sum += n;
        }
        return sum;
    }

//    public static void main(String[] args) {
//        Problem0650TwoKeysKeyboard a = new Problem0650TwoKeysKeyboard();
//        a.minSteps(12);
//    }
}
