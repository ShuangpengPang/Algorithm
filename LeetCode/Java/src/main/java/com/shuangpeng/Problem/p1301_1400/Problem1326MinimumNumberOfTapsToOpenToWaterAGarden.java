package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem1326MinimumNumberOfTapsToOpenToWaterAGarden（灌溉花园的最少水龙头数目）
 * @Date 2022/8/2 6:13 PM
 * @Version 1.0
 */
public class Problem1326MinimumNumberOfTapsToOpenToWaterAGarden {

    public int minTaps(int n, int[] ranges) {
        List<Integer> stack = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            int s = i - ranges[i], e = i + ranges[i];
            if (stack.isEmpty()) {
                if (s <= 0) {
                    stack.add(e);
                }
            } else if (e > stack.get(stack.size() - 1) && s <= stack.get(stack.size() - 1)) {
                while ((stack.size() > 1 && stack.get(stack.size() - 2) >= s) || (stack.size() == 1 && s <= 0)) {
                    stack.remove(stack.size() - 1);
                }
                stack.add(e);
            }
        }
        if (stack.isEmpty()) {
            return -1;
        }
        while (stack.size() > 1 && stack.get(stack.size() - 2) >= n) {
            stack.remove(stack.size() - 1);
        }
        return stack.get(stack.size() - 1) >= n ? stack.size() : -1;
    }
}
