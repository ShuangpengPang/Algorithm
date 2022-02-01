package com.shuangpeng.Problem.p1701_1800;

public class Problem1716CalculateMoneyInLeetcodeBank {

    public int totalMoney0(int n) {
        if (n < 7) {
            return (n + 1) * n >> 1;
        }
        int sum = 28;
        int period = n / 7;
        int mod = n % 7;
        return period * sum + ((period << 1) + mod + 1) * mod / 2 + 7 * period * (period - 1) / 2;
    }

    public int totalMoney1(int n) {
        int ans = 0, last = 0, money = 0;
        for (int i = 1; i <= n; ++i) {
            money = i % 7 == 1 ? ++last : money + 1;
            ans += money;
        }
        return ans;
    }

    public int totalMoney(int n) {
        int a = n / 7, b = n % 7;
        int first = (1 + 7) * 7 >> 1;
        int last = (a + a + 6) * 7 >> 1;
        return ((first + last) * a >> 1) + ((a + 1 + a + b) * b >> 1);
    }
}
