package com.shuangpeng.Problem.p2101_2200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2197ReplaceNonCoprimeNumbersInArray（替换数组中的非互质数）
 * @date 2022/11/30 3:25 PM
 */
public class Problem2197ReplaceNonCoprimeNumbersInArray {

    public List<Integer> replaceNonCoprimes(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int num : nums) {
            int g = num;
            while (!ans.isEmpty() && g > 1) {
                int n = ans.size() - 1;
                g = gcd(num, ans.get(n));
                if (g > 1) {
                    num = num / g * ans.get(n);
                    ans.remove(n);
                }
            }
            ans.add(num);
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
