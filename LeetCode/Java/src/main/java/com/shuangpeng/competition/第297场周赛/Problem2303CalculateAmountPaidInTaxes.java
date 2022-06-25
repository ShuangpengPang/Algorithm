package com.shuangpeng.competition.第297场周赛;

/**
 * @Description: Problem2303CalculateAmountPaidInTaxes（计算应缴税款总额）
 * @Date 2022/6/25 6:09 PM
 * @Version 1.0
 */
public class Problem2303CalculateAmountPaidInTaxes {

    public double calculateTax(int[][] brackets, int income) {
        int n = brackets.length;
        int prev = 0;
        double ans = 0;
        for (int i = 0; i < n && income > prev; i++) {
            ans += (double) (Math.min(income, brackets[i][0]) - prev) * brackets[i][1] / 100;
            prev = brackets[i][0];
        }
        return ans;
    }
}
