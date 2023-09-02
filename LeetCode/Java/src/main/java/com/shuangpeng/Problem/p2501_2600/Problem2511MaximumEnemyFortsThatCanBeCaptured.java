package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2511MaximumEnemyFortsThatCanBeCaptured（最多可以摧毁的敌人城堡数目）
 * @date 2023/9/2 9:10 PM
 */
public class Problem2511MaximumEnemyFortsThatCanBeCaptured {

    public int captureForts(int[] forts) {
        int n = forts.length, ans = 0;
        for (int p = n, i = 0; i < n; i++) {
            if (forts[i] != 0) {
                if (p < n && forts[p] != forts[i]) {
                    ans = Math.max(ans, i - p - 1);
                }
                p = i;
            }
        }
        return ans;
    }
}
