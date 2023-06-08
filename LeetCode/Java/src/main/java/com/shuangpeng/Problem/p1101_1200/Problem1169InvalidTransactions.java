package com.shuangpeng.Problem.p1101_1200;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1169InvalidTransactions（查询无效交易）
 * @date 2023/6/8 11:08 AM
 */
public class Problem1169InvalidTransactions {

    public List<String> invalidTransactions(String[] transactions) {
        Map<String, Map<Integer, Set<String>>> map = new HashMap<>(transactions.length);
        for (String t : transactions) {
            String[] arr = t.split(",");
            String name = arr[0], city = arr[3];
            int time = Integer.valueOf(arr[1]);
            map.computeIfAbsent(name, k -> new HashMap<>()).computeIfAbsent(time, k -> new HashSet<>()).add(city);
        }
        List<String> ans = new ArrayList<>();
        for (String t : transactions) {
            String[] arr = t.split(",");
            String name = arr[0], city = arr[3];
            int time = Integer.valueOf(arr[1]), amount = Integer.valueOf(arr[2]);
            if (amount > 1000 || check(map.get(name), time, city)) {
                ans.add(t);
            }
        }
        return ans;
    }

    private boolean check(Map<Integer, Set<String>> map, int time, String city) {
        for (Map.Entry<Integer, Set<String>> entry : map.entrySet()) {
            if (Math.abs(time - entry.getKey()) <= 60) {
                for (String c : entry.getValue()) {
                    if (!c.equals(city)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class Problem1169InvalidTransactions0 {

    public List<String> invalidTransactions(String[] transactions) {
        int n = transactions.length;
        Pair<Integer, String>[] info = new Pair[n];
        boolean[] invalid = new boolean[n];
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String t = transactions[i];
            String[] arr = t.split(",");
            String name = arr[0], city = arr[3];
            int time = Integer.valueOf(arr[1]), amount = Integer.valueOf(arr[2]);
            if (amount > 1000) {
                invalid[i] = true;
            }
            List<Integer> list = map.computeIfAbsent(name, k -> new ArrayList<>());
            for (int j : list) {
                if (Math.abs(info[j].getKey() - time) <= 60 && !info[j].getValue().equals(city)) {
                    invalid[j] = invalid[i] = true;
                }
            }
            list.add(i);
            info[i] = new Pair<>(time, city);
        }
        List<String> ans = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            if (invalid[i]) {
                ans.add(transactions[i]);
            }
        }
        return ans;
    }
}
