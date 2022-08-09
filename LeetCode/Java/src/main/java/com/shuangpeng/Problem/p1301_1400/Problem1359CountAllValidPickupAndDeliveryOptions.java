package com.shuangpeng.Problem.p1301_1400;

/**
 * @Description: Problem1359CountAllValidPickupAndDeliveryOptions（有效的快递序列数目）
 * @Date 2022/8/9 2:34 PM
 * @Version 1.0
 */
public class Problem1359CountAllValidPickupAndDeliveryOptions {

    public int countOrders(int n) {
        int ans = 1, M = (int) 1e9 + 7;
        for (int i = 2; i <= n; i++) {
            ans = (int) ((long) ans * i * (2 * i - 1) % M);
        }
        return ans;
    }
}
