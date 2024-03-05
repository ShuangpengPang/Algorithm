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

    public int countLargestGroup0(int n) {
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

    public int countLargestGroup(int n) {
        int[] cnt = new int[37];
        if (n == 10000) {
            cnt[1]++;
        }
        int c = 0;
        r: for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int x = 0; x < 10; x++) {
                    for (int y = 0; y < 10; y++) {
                        cnt[i + j + x + y]++;
                        if (c++ == n) {
                            break r;
                        }
                    }
                }
            }
        }
        int maxCnt = 0, ans = 0;
        for (int i = 1; i < 37; i++) {
            if (cnt[i] > maxCnt) {
                maxCnt = cnt[i];
                ans = 1;
            } else if (cnt[i] == maxCnt) {
                ans++;
            }
        }
        return ans;
    }
}
