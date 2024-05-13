package com.shuangpeng.lcr;

import com.shuangpeng.common.ListNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR123BookManagementI（图书整理I）
 * @date 2024/5/13 6:54 PM
 */
public class LCR123BookManagementI {

    public int[] reverseBookList(ListNode head) {
        int cnt = 0;
        for (ListNode node = head; node != null; node = node.next) {
            cnt++;
        }
        int[] ans = new int[cnt];
        int i = cnt - 1;
        for (ListNode node = head; node != null; node = node.next, i--) {
            ans[i] = node.val;
        }
        return ans;
    }
}
