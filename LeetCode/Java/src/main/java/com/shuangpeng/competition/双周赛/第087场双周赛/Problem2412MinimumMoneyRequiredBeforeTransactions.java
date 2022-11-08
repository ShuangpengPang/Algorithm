package com.shuangpeng.competition.双周赛.第087场双周赛;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2412MinimumMoneyRequiredBeforeTransactions（完成所有交易的初始最少钱数）
 * @date 2022/11/8 11:36 AM
 */
public class Problem2412MinimumMoneyRequiredBeforeTransactions {

    public long minimumMoney(int[][] transactions) {
        int n = transactions.length;
        long sum = 0L;
        int m1 = 0, m2 = 0;
        for (int[] t : transactions) {
            if (t[0] > t[1]) {
                sum += t[0] - t[1];
                m2 = Math.max(m2, t[1]);
            } else {
                m1 = Math.max(m1, t[0]);
            }
        }
        return sum + Math.max(m1, m2);
    }

//    public static void main(String[] args) {
//        Problem2412MinimumMoneyRequiredBeforeTransactions a = new Problem2412MinimumMoneyRequiredBeforeTransactions();
//        int[][] nums = {{2, 1}, {5, 0}, {4, 2}};
//        a.minimumMoney(nums);
//    }
}