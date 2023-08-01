package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1338ReduceArraySizeToTheHalf（数组大小减半）
 * @date 2023/8/1 12:15 PM
 */
public class Problem1338ReduceArraySizeToTheHalf {

    public int minSetSize(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.merge(num, 1, Integer::sum);
        }
        List<Integer> list = new ArrayList<>(map.values());
        list.sort(Comparator.reverseOrder());
        int m = (arr.length + 1) >> 1, cnt = 0, n = list.size();
        int i = 0;
        while (i < n && cnt < m) {
            cnt += list.get(i);
            i++;
        }
        return i;
    }
}
