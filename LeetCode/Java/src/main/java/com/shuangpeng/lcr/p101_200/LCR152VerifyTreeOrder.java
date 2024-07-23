package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR152VerifyTreeOrder（LCR 152. 验证二叉搜索树的后序遍历序列）
 * @date 2024/7/23 6:42 PM
 */
public class LCR152VerifyTreeOrder {

    public boolean verifyTreeOrder(int[] postorder) {
        return dfs(postorder, 0, postorder.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean dfs(int[] postorder, int start, int end, int min, int max) {
        if (start > end) {
            return true;
        }
        int num = postorder[end];
        if (num < min || num > max) {
            return false;
        }
        int i = end - 1;
        while (i >= start && postorder[i] > num) {
            if (postorder[i] > max) {
                return false;
            }
            i--;
        }
        return dfs(postorder, start, i, min, num) && dfs(postorder, i + 1, end - 1, num, max);
    }
}
