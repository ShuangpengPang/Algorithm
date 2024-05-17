package com.shuangpeng.lcr;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR159InventoryManagement（库存管理III）
 * @date 2024/5/17 2:11 PM
 */
public class LCR159InventoryManagement {

    public int[] inventoryManagement(int[] stock, int cnt) {
        Arrays.sort(stock);
        int[] ans = new int[cnt];
        System.arraycopy(stock, 0, ans, 0, cnt);
        return ans;
    }
}
