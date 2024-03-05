package com.shuangpeng.Problem.p1301_1400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1399CountLargestGroup（统计最大组的数目）
 * @date 2024/3/5 2:34 PM
 */
public class Problem1399CountLargestGroup {

    public int countLargestGroup(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxCnt = 0, ans = 0;
        for (int i = 1; i <= n; i++) {
            int cnt = map.merge(getSum(i), 1, Integer::sum);
            if (cnt > maxCnt) {
                maxCnt = cnt;
                ans = 1;
            } else if (cnt == maxCnt) {
                ans++;
            }
        }
        return ans;
    }

    private int getSum(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}
