package com.shuangpeng.Problem.p1401_1500;

import java.util.*;

public class Problem1418DisplayTableOfFoodOrdersInARestaurant {

    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<Integer> tables = new TreeSet<>(Comparator.comparingInt(a -> a));
        Set<String> foods = new TreeSet<>(String::compareTo);
        for (List<String> order : orders) {
            tables.add(Integer.parseInt(order.get(1)));
            foods.add(order.get(2));
        }
        Map<Integer, Map<String, Integer>> orderMap = new HashMap<>();
        for (List<String> order : orders) {
            int table = Integer.parseInt(order.get(1));
            String food = order.get(2);
            Map<String, Integer> foodMap = orderMap.getOrDefault(table, new HashMap<>());
            foodMap.put(food, foodMap.getOrDefault(food, 0) + 1);
            orderMap.put(table, foodMap);
        }
        List<List<String>> answer = new ArrayList<>(tables.size() + 1);
        List<String> list = new ArrayList<>(foods.size() + 1);
        list.add("Table");
        for (String food : foods) {
            list.add(food);
        }
        answer.add(list);
        for (int table : tables) {
            List<String> orderList = new ArrayList<>(foods.size() + 1);
            orderList.add(Integer.toString(table));
            Map<String, Integer> foodMap = orderMap.get(table);
            for (String food : foods) {
                orderList.add(Integer.toString(foodMap.getOrDefault(food, 0)));
            }
            answer.add(orderList);
        }
        return answer;
    }
}
