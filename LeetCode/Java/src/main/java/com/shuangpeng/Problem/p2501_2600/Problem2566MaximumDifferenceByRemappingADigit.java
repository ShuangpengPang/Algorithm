package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2566MaximumDifferenceByRemappingADigit（替换一个数字后的最大差值）
 * @date 2024/4/4 8:54 PM
 */
public class Problem2566MaximumDifferenceByRemappingADigit {

    public int minMaxDifference(int num) {
        List<Integer> list = new ArrayList<>();
        int x = num;
        while (x != 0) {
            list.add(x % 10);
            x /= 10;
        }
        return getMax(list, num) - getMin(list);
    }

    private int getMax(List<Integer> list, int num) {
        int n = list.size(), i = n - 1;
        while (i >= 0 && list.get(i) == 9) {
            i--;
        }
        if (i < 0) {
            return num;
        }
        int m = list.get(i), value = 0;
        i = n - 1;
        while (i >= 0) {
            value = value * 10 + (list.get(i) == m ? 9 : list.get(i));
            i--;
        }
        return value;
    }

    private int getMin(List<Integer> list) {
        int value = 0, n = list.size();
        for (int i = n - 2; i >= 0; i--) {
            value = value * 10 + (list.get(i) == list.get(n - 1) ? 0 : list.get(i));
        }
        return value;
    }
}
