package com.shuangpeng.Problem.p0601_0700;

/**
 * @Description: Problem0670MaximumSwap（最大交换）
 * @Date 2022/9/13 11:32 AM
 * @Version 1.0
 */
public class Problem0670MaximumSwap {

    public int maximumSwap0(int num) {
        char[] cs = String.valueOf(num).toCharArray();
        int n = cs.length;
        int idx = -1;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (idx == -1) {
                    if (cs[j] > cs[i]) {
                        idx = j;
                    }
                } else if (cs[j] >= cs[idx]) {
                    idx = j;
                }
            }
            if (idx != -1) {
                char c = cs[idx];
                cs[idx] = cs[i];
                cs[i] = c;
                break;
            }
        }
        return idx == -1 ? num : Integer.valueOf(new String(cs));
    }

    public int maximumSwap(int num) {
        char[] cs = Integer.toString(num).toCharArray();
        int n = cs.length;
        int[] maxArr = new int[n];
        int maxIdx = n - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (cs[i] > cs[maxIdx]) {
                maxIdx = i;
            }
            maxArr[i] = maxIdx;
        }
        for (int i = 0; i < n; i++) {
            int j = maxArr[i];
            if (cs[i] != cs[j]) {
                char c = cs[i];
                cs[i] = cs[j];
                cs[j] = c;
                break;
            }
        }
        return Integer.parseInt(new String(cs));
    }
}
