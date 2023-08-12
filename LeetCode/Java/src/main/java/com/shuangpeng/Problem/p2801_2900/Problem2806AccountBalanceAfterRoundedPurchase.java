package com.shuangpeng.Problem.p2801_2900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2806AccountBalanceAfterRoundedPurchase（取整购买后的账户余额）
 * @date 2023/8/12 11:36 AM
 */
public class Problem2806AccountBalanceAfterRoundedPurchase {

    public int accountBalanceAfterPurchase0(int purchaseAmount) {
        int m = purchaseAmount % 10;
        return 100 - (purchaseAmount - m + (m < 5 ? 0 : 10));
    }

    public int accountBalanceAfterPurchase(int purchaseAmount) {
        return 100 - (purchaseAmount + 5) / 10 * 10;
    }
}
