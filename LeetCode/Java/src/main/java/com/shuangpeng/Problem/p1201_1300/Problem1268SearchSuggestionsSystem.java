package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1268SearchSuggestionsSystem（搜索推荐系统）
 * @date 2023/6/17 8:54 PM
 */
public class Problem1268SearchSuggestionsSystem {

    public List<List<String>> suggestedProducts0(String[] products, String searchWord) {
        char[] cs = searchWord.toCharArray();
        int n = cs.length;
        List<PriorityQueue<String>> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(new PriorityQueue<>(3, Comparator.reverseOrder()));
        }
        for (String p : products) {
            for (int i = 0; i < p.length() && i < n && p.charAt(i) == cs[i]; i++) {
                PriorityQueue<String> q = list.get(i);
                q.offer(p);
                if (q.size() > 3) {
                    q.poll();
                }
            }
        }
        List<List<String>> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            List<String> t = new ArrayList<>(list.get(i));
            t.sort(String::compareTo);
            ans.add(t);
        }
        return ans;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        int left = 0, right = products.length - 1, n = searchWord.length();
        List<List<String>> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            char c = searchWord.charAt(i);
            while (left <= right && (products[left].length() <= i || products[left].charAt(i) != c)) {
                left++;
            }
            while (left <= right && (products[right].length() <= i || products[right].charAt(i) != c)) {
                right--;
            }
            List<String> list = new ArrayList<>();
            for (int j = left; j <= right && j < left + 3; j++) {
                list.add(products[j]);
            }
            ans.add(list);
        }
        return ans;
    }
}
