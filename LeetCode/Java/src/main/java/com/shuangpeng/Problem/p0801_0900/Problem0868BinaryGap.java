package com.shuangpeng.Problem.p0801_0900;

/**
 * @Description: Problem0868BinaryGap
 * @Date 2022/4/24 11:14 AM
 * @Version 1.0
 */
public class Problem0868BinaryGap {

    public int binaryGap(int n) {
        int ans = 0, preIndex = -1;
        int idx = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                if (preIndex != -1) {
                    ans = Math.max(ans, idx - preIndex);
                }
                preIndex = idx;
            }
            n >>= 1;
            ++idx;
        }
        return ans;
    }
}
