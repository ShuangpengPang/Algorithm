package com.shuangpeng.Problem.p1301_1400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1357ApplyDiscountEveryNOrders（每隔n个顾客打折）
 * @date 2023/8/7 5:25 PM
 */
public class Problem1357ApplyDiscountEveryNOrders {

    class Cashier {

        Map<Integer, Integer> map;
        int n, discount, cnt;

        public Cashier(int n, int discount, int[] products, int[] prices) {
            this.n = n;
            this.discount = discount;
            map = new HashMap<>();
            int m = products.length;
            for (int i = 0; i < m; i++) {
                map.put(products[i], prices[i]);
            }
            cnt = 0;
        }

        public double getBill(int[] product, int[] amount) {
            int m = product.length;
            double ans = 0;
            for (int i = 0; i < m; i++) {
                ans += map.get(product[i]) * amount[i];
            }
            if ((++cnt % n) == 0) {
                ans = ans * (100 -discount) / 100;
            }
            return ans;
        }
    }

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj.getBill(product,amount);
 */
}
