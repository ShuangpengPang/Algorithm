package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR158InventoryManagement（库存管理II）
 * @date 2024/5/16 3:27 PM
 */
public class LCR158InventoryManagement {

    public int inventoryManagement(int[] stock) {
        int ans = 0, cnt = 0;
        for (int n = stock.length, i = 0; i < n && cnt <= n - i; i++) {
            if (cnt == 0) {
                ans = stock[i];
                cnt = 1;
            } else if (stock[i] == ans) {
                cnt++;
            } else {
                cnt--;
            }
        }
        return ans;
    }
}
