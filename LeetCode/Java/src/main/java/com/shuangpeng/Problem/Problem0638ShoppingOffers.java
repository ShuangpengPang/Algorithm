package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem0638ShoppingOffers {

    public int shoppingOffers0(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int n = price.size();
        int state = 0;
        for (int i = 0; i < n; i++) {
            state = state * 11 + needs.get(i);
        }
        return minCost(price, special, state, new HashMap<>());
    }

    private int minCost(List<Integer> price, List<List<Integer>> special, int state, Map<Integer, Integer> memo) {
        if (state == 0) {
            return 0;
        }
        if (memo.containsKey(state)) {
            return memo.get(state);
        }
        int n = price.size();
        int cost = Integer.MAX_VALUE;
        for (List<Integer> list : special) {
            int newState = getState(list, state);
            if (newState >= 0) {
                cost = Math.min(cost, list.get(list.size() - 1) + minCost(price, special, newState, memo));
            }
        }
        for (int i = 0; i < n; i++) {
            int newState = getState(price, i, state);
            if (newState >= 0) {
                cost = Math.min(cost, price.get(i) + minCost(price, special, newState, memo));
            }
        }
        memo.put(state, cost);
        return cost;
    }

    private int getState(List<Integer> price, int i, int state) {
        int n = price.size();
        int value = (int) Math.pow(11, n - i - 1);
        int need = state / value % 11;
        if (need == 0) {
            return -1;
        }
        return state - value;
    }

    private int getState(List<Integer> list, int state) {
        int n = list.size();
        int newState = 0;
        int base = 1;
        for (int i = n - 2; i >= 0; i--) {
            int need = state % 11;
            int count = list.get(i);
            if (count > need) {
                return -1;
            }
            newState += (need - count) * base;
            state /= 11;
            base *= 11;
        }
        return newState;
    }

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs, new HashMap<>());
    }

    private int dfs(List<Integer> price, List<List<Integer>> special,
                    List<Integer> needs, Map<List<Integer>, Integer> memo) {
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }
        int n = price.size();
        int cost = 0;
        for (int i = 0; i < n; i++) {
            cost += price.get(i) * needs.get(i);
        }
        for (List<Integer> list : special) {
            int i = 0;
            List<Integer> clone = new ArrayList<>(needs);
            for (; i < n; i++) {
                int diff = needs.get(i) - list.get(i);
                if (diff < 0) {
                    break;
                }
                clone.set(i, diff);
            }
            if (i == n) {
                cost = Math.min(cost, list.get(n) + dfs(price, special, clone, memo));
            }
        }
        memo.put(needs, cost);
        return cost;
    }
}
