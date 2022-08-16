package com.shuangpeng.Problem.p1601_1700;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem1656DesignOrderedStream（设计有序流）
 * @Date 2022/8/16 10:39 AM
 * @Version 1.0
 */
public class Problem1656DesignOrderedStream {
}

class OrderedStream {

    String[] strings;
    int idx;
    int n;

    public OrderedStream(int n) {
        strings = new String[n];
        idx = 0;
        this.n = n;
    }

    public List<String> insert(int idKey, String value) {
        strings[idKey - 1] = value;
        List<String> ans = new ArrayList<>();
        while (idx < n && strings[idx] != null) {
            ans.add(strings[idx]);
            idx++;
        }
        return ans;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream obj = new OrderedStream(n);
 * List<String> param_1 = obj.insert(idKey,value);
 */
