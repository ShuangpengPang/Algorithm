package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2748NumberOfBeautifulPairs（美丽下标对的数目）
 * @date 2024/4/7 12:05 PM
 */
public class Problem2748NumberOfBeautifulPairs {

    public int countBeautifulPairs(int[] nums) {
        int[] cnt = new int[10];
        int ans = 0;
        for (int num : nums) {
            int d = num % 10;
            for (int i = 1; i < 10; i++) {
                if (gcd(d, i) == 1) {
                    ans += cnt[i];
                }
            }
            cnt[getFirstDigit(num)]++;
        }
        return ans;
    }

    private int getFirstDigit(int x) {
        while (x > 9) {
            x /= 10;
        }
        return x;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}

class Problem2748NumberOfBeautifulPairs0 {

    static final int N = 10;
    static final boolean[][] arr = new boolean[N][N];

    static {
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = arr[j][i] = gcd(i, j) == 1;
            }
        }
    }

    public int countBeautifulPairs(int[] nums) {
        int[] cnt = new int[N];
        int ans = 0;
        for (int num : nums) {
            int d = num % 10;
            for (int i = 1; i < N; i++) {
                if (arr[i][d]) {
                    ans += cnt[i];
                }
            }
            cnt[getFirstDigit(num)]++;
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private int getFirstDigit(int x) {
        while (x > 9) {
            x /= 10;
        }
        return x;
    }
}
