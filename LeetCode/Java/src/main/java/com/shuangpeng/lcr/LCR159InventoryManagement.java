package com.shuangpeng.lcr;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR159InventoryManagement（库存管理III）
 * @date 2024/5/17 2:11 PM
 */
public class LCR159InventoryManagement {

    public int[] inventoryManagement0(int[] stock, int cnt) {
        Arrays.sort(stock);
        int[] ans = new int[cnt];
        System.arraycopy(stock, 0, ans, 0, cnt);
        return ans;
    }

    private int[] heap;
    private int n;

    public int[] inventoryManagement(int[] stock, int cnt) {
        this.n = cnt;
        heap = new int[cnt];
        if (cnt == 0) {
            return heap;
        }
        System.arraycopy(stock, 0, heap, 0, cnt);
        buildHeap();
        for (int i = cnt; i < stock.length; i++) {
            if (stock[i] < heap[0]) {
                heap[0] = stock[i];
                sink(0);
            }
        }
        return heap;
    }

    private void buildHeap() {
        for (int i = (n >> 1) - 1; i >= 0; i--) {
            sink(i);
        }
    }

    private void sink(int i) {
        int num = heap[i];
        while ((i << 1) + 1 < n) {
            int left = (i << 1) + 1, right = left + 1;
            int j = right >= n || heap[left] >= heap[right] ? left : right;
            if (num >= heap[j]) {
                break;
            }
            heap[i] = heap[j];
            i = j;
        }
        heap[i] = num;
    }
}
