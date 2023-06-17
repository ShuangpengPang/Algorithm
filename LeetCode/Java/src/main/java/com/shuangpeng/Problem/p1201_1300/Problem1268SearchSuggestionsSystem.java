package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1268SearchSuggestionsSystem（搜索推荐系统）
 * @date 2023/6/17 8:54 PM
 */
public class Problem1268SearchSuggestionsSystem {

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        char[] cs = searchWord.toCharArray();
        int n = cs.length;
        List<PriorityQueue<String>> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(new PriorityQueue<>(3, (a, b) -> b.compareTo(a)));
        }
        for (String p : products) {
            for (int i = 0; i < p.length() && i < n && p.charAt(i) == cs[i]; i++) {
                PriorityQueue<String> q = list.get(i);
                if (q.size() < 3) {
                    q.offer(p);
                } else if (q.peek().compareTo(p) > 0) {
                    q.poll();
                    q.offer(p);
                }
            }
        }
        List<List<String>> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<String> t = new ArrayList<>(list.get(i));
            t.sort((a, b) -> a.compareTo(b));
            ans.add(t);
        }
        return ans;
    }
}
