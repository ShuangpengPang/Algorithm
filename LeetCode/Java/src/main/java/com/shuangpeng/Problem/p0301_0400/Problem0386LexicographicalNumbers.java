package com.shuangpeng.Problem.p0301_0400;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem0386LexicographicalNumbers
 * @Date 2022/4/18 11:56 AM
 * @Version 1.0
 */
public class Problem0386LexicographicalNumbers {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int i = 1;
        do {
            while (i <= n) {
                ans.add(i);
                i *= 10;
            }
            i /= 10;
            ++i;
            while (i % 10 == 0) {
                i /= 10;
            }
        } while (i != 1);
        return ans;
    }
}
