package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2706BuyTwoChocolates（购买两块巧克力）
 * @date 2023/8/7 10:42 AM
 */
public class Problem2706BuyTwoChocolates {

    public int buyChoco(int[] prices, int money) {
        int N = Integer.MAX_VALUE / 3, first = N, second = N;
        for (int p : prices) {
            if (p < first) {
                second = first;
                first = p;
            } else if (p < second) {
                second = p;
            }
        }
        int ans = money - first - second;
        return ans < 0 ? money : ans;
    }
}
