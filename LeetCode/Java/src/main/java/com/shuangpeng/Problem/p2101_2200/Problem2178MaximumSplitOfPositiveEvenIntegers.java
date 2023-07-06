package com.shuangpeng.Problem.p2101_2200;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2178MaximumSplitOfPositiveEvenIntegers（拆分成最多数目的正偶数之和）
 * @date 2023/7/6 12:07 PM
 */
public class Problem2178MaximumSplitOfPositiveEvenIntegers {

    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> ans = new ArrayList<>();
        if ((finalSum & 1) == 1) {
            return ans;
        }
        for (long i = 2; i << 1 < finalSum; i += 2) {
            ans.add(i);
            finalSum -= i;
        }
        ans.add(finalSum);
        return ans;
    }
}
