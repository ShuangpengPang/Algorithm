package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1291SequentialDigits（顺次数）
 * @date 2023/7/4 11:36 AM
 */
public class Problem1291SequentialDigits {

    public List<Integer> sequentialDigits0(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int num = 0;
            for (int j = i; j <= 9 && num <= high; j++) {
                num = num * 10 + j;
                if (num >= low) {
                    ans.add(num);
                }
            }
        }
        ans.sort(Comparator.comparingInt(a -> a));
        return ans;
    }

    public List<Integer> sequentialDigits(int low, int high) {
        String s = "123456789";
        int n1 = Integer.toString(low).length(), n2 = Integer.toString(high).length();
        List<Integer> ans = new ArrayList<>();
        for (int len = n1; len <= n2; len++) {
            for (int i = 0; i <= 9 - len; i++) {
                int num = Integer.parseInt(s.substring(i, i + len));
                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }
        return ans;
    }
}
