package com.shuangpeng.competition.第290到300场周赛.第297场周赛;

/**
 * @Description: Problem2303CalculateAmountPaidInTaxes（计算应缴税款总额）
 * @Date 2022/6/25 6:09 PM
 * @Version 1.0
 */
public class Problem2303CalculateAmountPaidInTaxes {

    // 比赛时写法
    public double calculateTax0(int[][] brackets, int income) {
        double ans = 0;
        int n = brackets.length;
        int prev = 0;
        for (int[] pair : brackets) {
            int up = pair[0], p = pair[1];
            if (income <= prev) {
                break;
            }
            if (income <= up) {
                ans += (double) (income - prev) * p / 100;
                break;
            } else {
                ans += (double) (up - prev) * p / 100;
            }
            prev = up;
        }
        return ans;
    }

    public double calculateTax1(int[][] brackets, int income) {
        int n = brackets.length;
        int prev = 0;
        double ans = 0;
        for (int i = 0; i < n && income > prev; i++) {
            ans += (double) (Math.min(income, brackets[i][0]) - prev) * brackets[i][1] / 100;
            prev = brackets[i][0];
        }
        return ans;
    }

    public double calculateTax(int[][] brackets, int income) {
        int n = brackets.length;
        int sum = 0;
        int prev = 0;
        for (int i = 0; i < n && income > prev; i++) {
            sum += (Math.min(income, brackets[i][0]) - prev) * brackets[i][1];
            prev = brackets[i][0];
        }
        return sum / 100.0;
    }
}
