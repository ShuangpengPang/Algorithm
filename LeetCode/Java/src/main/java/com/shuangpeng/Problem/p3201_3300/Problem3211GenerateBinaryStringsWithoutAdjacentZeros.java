package com.shuangpeng.Problem.p3201_3300;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3211GenerateBinaryStringsWithoutAdjacentZeros（生成不含相邻零的二进制字符串）
 * @date 2024/7/21 9:24 PM
 */
public class Problem3211GenerateBinaryStringsWithoutAdjacentZeros {

    public List<String> validStrings(int n) {
        List<Long> list = new ArrayList<>(1 << n);
        list.add(0L);
        list.add(1L);
        for (int i = 2; i <= n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                long num = list.get(j);
                if ((num & 1) == 1) {
                    list.add(num * 10);
                }
                list.set(j, num * 10 + 1);
            }
        }
        int m = list.size();
        List<String> ans = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            ans.add(String.format("%0" + n + "d", list.get(i)));
        }
        return ans;
    }
}
