package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1769MinimumNumberOfOperationsToMoveAllBallsToEachBox（移动所有球到每个盒子所需的最小操作数）
 * @date 2022/12/2 10:17 AM
 */
public class Problem1769MinimumNumberOfOperationsToMoveAllBallsToEachBox {

    public int[] minOperations0(String boxes) {
        int n = boxes.length(), sum = 0;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i];
            if (boxes.charAt(i) == '1') {
                preSum[i + 1]++;
                sum += i;
            }
        }
        int[] ans = new int[n];
        ans[0] = sum;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] + preSum[i] - (preSum[n] - preSum[i]);
        }
        return ans;
    }

    public int[] minOperations(String boxes) {
        int n = boxes.length(), cnt = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            if (boxes.charAt(i) == '1') {
                cnt++;
                sum += i;
            }
        }
        int[] ans = new int[n];
        int left = 0;
        for (int i = 0; i < n; i++) {
            ans[i] = i == 0 ? sum : ans[i - 1] + left - cnt;
            if (boxes.charAt(i) == '1') {
                left++;
                cnt--;
            }
        }
        return ans;
    }
}