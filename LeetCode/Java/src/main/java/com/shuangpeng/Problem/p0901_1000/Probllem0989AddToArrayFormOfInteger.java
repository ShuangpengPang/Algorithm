package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Probllem0989AddToArrayFormOfInteger（数组形式的整数加法）
 * @date 2024/1/6 7:17 PM
 */
public class Probllem0989AddToArrayFormOfInteger {

    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> ans = new ArrayList<>();
        for (int i = num.length - 1; i >= 0 || k > 0; i--) {
            if (i >= 0) {
                k += num[i];
            }
            ans.add(k % 10);
            k /= 10;
        }
        Collections.reverse(ans);
        return ans;
    }
}
