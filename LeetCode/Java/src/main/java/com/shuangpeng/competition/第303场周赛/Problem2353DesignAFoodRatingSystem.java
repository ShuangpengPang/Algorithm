package com.shuangpeng.competition.第303场周赛;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Description: Problem2353DesignAFoodRatingSystem（设计食物评分系统）
 * @Date 2022/7/31 6:21 PM
 * @Version 1.0
 */
public class Problem2353DesignAFoodRatingSystem {

    class FoodRatings {

        class Info {
            String food;
            int rating;

            Info(String food, int rating) {
                this.food = food;
                this.rating = rating;
            }
        }

        Map<String, Integer> ratingMap;
        Map<String, TreeSet<Info>> cuisinesMap;
        Map<String, String> foodMap;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            ratingMap = new HashMap<>();
            cuisinesMap = new HashMap<>();
            foodMap = new HashMap<>();
            int n = foods.length;
            for (int i = 0; i < n; i++) {
                String f = foods[i];
                String c = cuisines[i];
                int r = ratings[i];
                ratingMap.put(f, r);
                cuisinesMap.putIfAbsent(c, new TreeSet<Info>((a, b) -> {
                    if (a.rating != b.rating) {
                        return b.rating - a.rating;
                    }
                    return a.food.compareTo(b.food);
                }));
                cuisinesMap.get(c).add(new Info(f, r));
                foodMap.put(f, c);
            }
        }

        public void changeRating(String food, int newRating) {
            ratingMap.put(food, newRating);
            String c = foodMap.get(food);
            cuisinesMap.get(c).add(new Info(food, newRating));
        }

        public String highestRated(String cuisine) {
            TreeSet<Info> set = cuisinesMap.get(cuisine);
            while (set.first().rating != ratingMap.get(set.first().food)) {
                set.remove(set.first());
            }
            return set.first().food;
        }
    }

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */
}
