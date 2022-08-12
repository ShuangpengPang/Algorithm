package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1406StoneGameIII（石头游戏III）
 * @Date 2022/8/12 2:28 PM
 * @Version 1.0
 */
public class Problem1406StoneGameIII {

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int a = 0, b = 0, c = 0, sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            int v = stoneValue[i] + sum - a;
            if (i + 1 < n) {
                v = Math.max(v, stoneValue[i] + sum - b);
            }
            if (i + 2 < n) {
                v = Math.max(v, stoneValue[i] + sum - c);
            }
            c = b;
            b = a;
            a = v;
            sum += stoneValue[i];
        }
        b = sum - a;
        return a > b ? "Alice" : (a < b ? "Bob" : "Tie");
    }
}
