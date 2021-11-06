package com.shuangpeng.Problem.p0701_0800;

public class Problem0788RotatedDigits {

    public int rotatedDigits0(int n) {
        int[] map = {0, 1, 2, 5, 5, 5, 6, 8, 8, 9};
        int i = 0;
        boolean flag = false;
        int count = 0;
        while (i <= n) {
            if (flag == true) {
                count++;
            }
            int t = i;
            int j = 1;
            while (t % 10 == 9) {
                j *= 10;
                t /= 10;
            }
            int a = i / j % 10 + 1;
            a = map[a];
            i = i - i % (j * 10) + a * j;
            t = i;
            flag = false;
            while (t > 0) {
                int m = t % 10;
                if (m == 2 || m == 5 || m == 6 || m == 9) {
                    flag = true;
                    break;
                }
                t /= 10;
            }
        }
        return count;
    }

    public int rotatedDigits(int N) {
        char[] chars = String.valueOf(N).toCharArray();
        int k = chars.length;
        int[][][] dp = new int[k + 1][2][2];
        dp[k][0][1] = dp[k][1][1] = 1;
        for (int i = k - 1; i >= 0; --i) {
            for (int eqf = 0; eqf <= 1; ++eqf) {
                for (int invoke = 0; invoke <= 1; ++invoke) {
                    int count = 0;
                    for (char c = '0'; c <= (eqf == 1 ? chars[i] : '9'); ++c) {
                        if (c == '3' || c == '4' || c == '7') {
                            continue;
                        }
                        boolean flag = c == '2' || c == '5' || c == '6' || c == '9';
                        count += dp[i + 1][c == chars[i] ? eqf : 0][flag ? 1 : invoke];
                    }
                    dp[i][eqf][invoke] = count;
                }
            }
        }
        return dp[0][1][0];
    }

//    public static void main(String[] args) {
//        Problem0788RotatedDigits a = new Problem0788RotatedDigits();
//        a.rotatedDigits(10);
//    }
}
