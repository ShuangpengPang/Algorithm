package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR191StatisticalResult（LCR 191. 按规则计算统计结果）
 * @date 2024/8/19 4:13 PM
 */
public class LCR191StatisticalResult {

    public int[] statisticalResult0(int[] arrayA) {
        int product = 1, zeros = 0;
        for (int num : arrayA) {
            if (num == 0) {
                zeros++;
            } else {
                product *= num;
            }
        }
        int n = arrayA.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (zeros == 0) {
                ans[i] = product / arrayA[i];
            } else if (zeros == 1) {
                ans[i] = arrayA[i] == 0 ? product : 0;
            } else {
                ans[i] = 0;
            }
        }
        return ans;
    }

    public int[] statisticalResult(int[] arrayA) {
        int n = arrayA.length;
        int[] ans = new int[n];
        if (n == 0) {
            return ans;
        }
        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * arrayA[i - 1];
        }
        int p = 1;
        for (int i = n - 2; i >= 0; i--) {
            p *= arrayA[i + 1];
            ans[i] *= p;
        }
        return ans;
    }
}
