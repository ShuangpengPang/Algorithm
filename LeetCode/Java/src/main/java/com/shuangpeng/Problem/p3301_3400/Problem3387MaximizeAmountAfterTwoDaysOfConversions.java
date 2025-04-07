package com.shuangpeng.Problem.p3301_3400;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3387MaximizeAmountAfterTwoDaysOfConversions（两天自由外汇交易后的最大货币数）
 * @date 2025/4/7 14:37
 */
public class Problem3387MaximizeAmountAfterTwoDaysOfConversions {

    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        Map<String, Double> amount = new HashMap<>();
        amount.put(initialCurrency, 1.0);
        Map<String, Map<String, Double>> r1 = getRateMap(pairs1, rates1), r2 = getRateMap(pairs2, rates2);
        conversion(amount, r1);
        conversion(amount, r2);
        return amount.get(initialCurrency);
    }

    private void conversion(Map<String, Double> amount, Map<String, Map<String, Double>> rate) {
        Queue<Pair<String, Double>> q = new ArrayDeque<>();
        for (String c : amount.keySet()) {
            q.offer(new Pair<>(c, amount.get(c)));
        }
        while (!q.isEmpty()) {
            Pair<String, Double> p = q.poll();
            String c = p.getKey();
            double a = p.getValue();
            if (rate.containsKey(c)) {
                Map<String, Double> m = rate.get(c);
                for (String x : m.keySet()) {
                    double y = a * m.get(x);
                    if (y > amount.getOrDefault(x, Double.MIN_VALUE)) {
                        amount.put(x, y);
                        q.offer(new Pair<>(x, y));
                    }
                }
            }
        }
    }

    private Map<String, Map<String, Double>> getRateMap(List<List<String>> pairs, double[] rates) {
        Map<String, Map<String, Double>> rateMap = new HashMap<>();
        int n = rates.length;
        for (int i = 0; i < n; i++) {
            List<String> pair = pairs.get(i);
            String c1 = pair.get(0), c2 = pair.get(1);
            Map<String, Double> m1 = rateMap.getOrDefault(c1, new HashMap<>()), m2 = rateMap.getOrDefault(c2, new HashMap<>());
            m1.put(c2, rates[i]);
            m2.put(c1, 1 / rates[i]);
            rateMap.put(c1, m1);
            rateMap.put(c2, m2);
        }
        return rateMap;
    }

//    public static void main(String[] args) {
//        String initialCurrency = "ACB";
//        String[][] pairs1 = {{"SFE","Z"},{"ACB","SFE"}};
//        double[] rates1 = {9.1,2.4};
//        String[][] pairs2 = {{"SFE","Z"},{"ACB","SFE"}};
//        double[] rates2 = {2.8,5.0};
//        List<List<String>> list1 = new ArrayList<>();
//        for (String[] s : pairs1) {
//            List<String> list = new ArrayList<>();
//            list.add(s[0]);
//            list.add(s[1]);
//            list1.add(list);
//        }
//        List<List<String>> list2 = new ArrayList<>();
//        for (String[] s : pairs2) {
//            List<String> list = new ArrayList<>();
//            list.add(s[0]);
//            list.add(s[1]);
//            list2.add(list);
//        }
//        Problem3387MaximizeAmountAfterTwoDaysOfConversions a = new Problem3387MaximizeAmountAfterTwoDaysOfConversions();
//        a.maxAmount(initialCurrency, list1, rates1, list2, rates2);
//    }
}

class Problem3387MaximizeAmountAfterTwoDaysOfConversions0 {

    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        Map<String, Double> day1Amount = calcAmount(pairs1, rates1, initialCurrency);
        Map<String, Double> day2Amount = calcAmount(pairs2, rates2, initialCurrency);

        double ans = 0;
        for (Map.Entry<String, Double> e : day2Amount.entrySet()) {
            ans = Math.max(ans, day1Amount.getOrDefault(e.getKey(), 0.0) / e.getValue());
        }
        return ans;
    }

    private Map<String, Double> calcAmount(List<List<String>> pairs, double[] rates, String initialCurrency) {
        Map<String, List<Pair<String, Double>>> g = new HashMap<>();
        for (int i = 0; i < pairs.size(); i++) {
            String x = pairs.get(i).get(0);
            String y = pairs.get(i).get(1);
            double r = rates[i];
            g.computeIfAbsent(x, k -> new ArrayList<>()).add(new Pair(y, r));
            g.computeIfAbsent(y, k -> new ArrayList<>()).add(new Pair(x, 1 / r));
        }

        Map<String, Double> amount = new HashMap<>();
        dfs(initialCurrency, 1, g, amount);
        return amount;
    }

    private void dfs(String x, double curAmount, Map<String, List<Pair<String, Double>>> g, Map<String, Double> amount) {
        amount.put(x, curAmount);
        if (!g.containsKey(x)) {
            return;
        }
        for (Pair<String, Double> p : g.get(x)) {
            // 每个节点只需递归一次（重复递归算出来的结果是一样的，因为题目保证汇率没有矛盾）
            if (!amount.containsKey(p.getKey())) {
                dfs(p.getKey(), curAmount * p.getValue(), g, amount);
            }
        }
    }
}
