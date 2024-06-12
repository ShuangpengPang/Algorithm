package com.shuangpeng.Problem.p3101_3200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3170LexicographicallyMinimumStringAfterRemovingStars（删除星号以后字典序最小的字符串）
 * @date 2024/6/13 12:47 AM
 */
public class Problem3170LexicographicallyMinimumStringAfterRemovingStars {

    public String clearStars(String s) {
        char[] cs = s.toCharArray();
        List<Integer>[] indices = new List[26];
        Arrays.setAll(indices, i -> new ArrayList<>());
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '*') {
                for (List<Integer> list : indices) {
                    if (!list.isEmpty()) {
                        cs[list.get(list.size() - 1)] = '#';
                        list.remove(list.size() - 1);
                        break;
                    }
                }
                cs[i] = '#';
            } else {
                indices[cs[i] - 'a'].add(i);
            }
        }
        int last = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] != '#') {
                cs[last++] = cs[i];
            }
        }
        return new String(cs, 0, last);
    }
}
