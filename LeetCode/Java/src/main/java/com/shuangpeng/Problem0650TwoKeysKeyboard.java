package com.shuangpeng;

public class Problem0650TwoKeysKeyboard {

    public int minSteps(int n) {
        if (n <= 1) {
            return 0;
        }
        int[] primeFactor = new int[10];
        int[] stack = new int[10];
        stack[0] = n;
        int factors = 0;
        int count = 1;
        while (count > 0) {
            int d = stack[--count];
            boolean isPrime = true;
            for (int i = 2; i * i <= d; i++) {
                if (d % i == 0) {
                    stack[count++] = i;
                    stack[count++] = d / i;
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeFactor[factors++] = d;
            }
        }
        int sum = 0;
        for (int i = 0; i < factors; i++) {
            sum += primeFactor[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Problem0650TwoKeysKeyboard a = new Problem0650TwoKeysKeyboard();
        a.minSteps(3);
    }
}
