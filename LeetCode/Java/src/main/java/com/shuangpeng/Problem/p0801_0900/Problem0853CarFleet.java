package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0853CarFleet（车队）
 * @date 2022/11/16 10:41 PM
 */
public class Problem0853CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparingInt(i -> position[i]));
        long a = 0, b = 1;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int j = ids[i];
            long d = target - position[j], s = speed[j];
            if (d * b - a * s > 0) {
                ans++;
                a = d;
                b = s;
            }
        }
        return ans;
    }
}
