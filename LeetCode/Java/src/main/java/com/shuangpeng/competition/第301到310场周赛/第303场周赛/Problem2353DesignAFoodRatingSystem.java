package com.shuangpeng.competition.第301到310场周赛.第303场周赛;

import javafx.util.Pair;

import java.util.*;

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

class Problem2353DesignAFoodRatingSystem0 {

    class FoodRatings {

        class Info implements Comparable<Info> {
            String food;
            int rating;

            Info(String food, int rating) {
                this.food = food;
                this.rating = rating;
            }

            @Override
            public int compareTo(Info info) {
                if (this.rating != info.rating) {
                    return info.rating - this.rating;
                }
                return this.food.compareTo(info.food);
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
                cuisinesMap.putIfAbsent(c, new TreeSet<Info>());
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
}

class Problem2353DesignAFoodRatingSystem1 {

    class FoodRatings {

        Map<String, Pair<String, Integer>> fs ;
        Map<String, TreeSet<Pair<String, Integer>>> cs;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            fs = new HashMap<>();
            cs = new HashMap<>();
            int n = foods.length;
            for (int i = 0; i < n; i++) {
                String f = foods[i];
                String c = cuisines[i];
                int r = ratings[i];
                fs.put(f, new Pair<>(c, r));
                cs.computeIfAbsent(c, k -> new TreeSet<>((a, b) -> !Objects.equals(a.getValue(), b.getValue()) ? b.getValue() - a.getValue(): a.getKey().compareTo(b.getKey()))).add(new Pair<>(f, r));
            }
        }

        public void changeRating(String food, int newRating) {
            Pair<String, Integer> old = fs.get(food);
            String c = old.getKey();
            int r = old.getValue();
            cs.get(c).remove(new Pair<>(food, r));
            cs.get(c).add(new Pair<>(food, newRating));
            fs.put(food, new Pair<>(c, newRating));
        }

        public String highestRated(String cuisine) {
            return cs.get(cuisine).first().getKey();
        }
    }
}

class Problem2353DesignAFoodRatingSystem2 {

    class FoodRatings {

        class Item {
            String s;
            int i;

            Item(String s, int i) {
                this.s = s;
                this.i = i;
            }

            @Override
            public boolean equals(Object o) {
                Item other = (Item) o;
                return s.equals(other.s) && i == other.i;
            }

            @Override
            public int hashCode() {
                return Objects.hash(s, i);
            }
        }

        Map<String, Item> fs ;
        Map<String, TreeSet<Item>> cs;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            fs = new HashMap<>();
            cs = new HashMap<>();
            int n = foods.length;
            for (int i = 0; i < n; i++) {
                String f = foods[i];
                String c = cuisines[i];
                int r = ratings[i];
                fs.put(f, new Item(c, r));
                cs.computeIfAbsent(c, k -> new TreeSet<>((a, b) -> a.i != b.i ? b.i - a.i : a.s.compareTo(b.s))).add(new Item(f, r));
            }
        }

        public void changeRating(String food, int newRating) {
            Item old = fs.get(food);
            String c = old.s;
            int r = old.i;
            cs.get(c).remove(new Item(food, r));
            cs.get(c).add(new Item(food, newRating));
            old.i = newRating;
        }

        public String highestRated(String cuisine) {
            return cs.get(cuisine).first().s;
        }
    }
}

class Problem2353DesignAFoodRatingSystem3 {

    class FoodRatings {

        class Item {
            String c;
            String f;
            int i;
            boolean invalid;

            Item(String f, String c, int i) {
                this.f = f;
                this.c = c;
                this.i = i;
                this.invalid = false;
            }
        }

        Map<String, Item> fs ;
        Map<String, PriorityQueue<Item>> cs;

        public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
            fs = new HashMap<>();
            cs = new HashMap<>();
            int n = foods.length;
            for (int i = 0; i < n; i++) {
                String f = foods[i];
                String c = cuisines[i];
                int r = ratings[i];
                Item item = new Item(f, c, r);
                fs.put(f, item);
                cs.computeIfAbsent(c, k -> new PriorityQueue<>((a, b) -> a.i != b.i ? b.i - a.i : a.f.compareTo(b.f))).add(item);
            }
        }

        public void changeRating(String food, int newRating) {
            Item old = fs.get(food);
            String c = old.c;
            old.invalid = true;
            Item item = new Item(food, c, newRating);
            fs.put(food, item);
            cs.get(c).add(item);
        }

        public String highestRated(String cuisine) {
            PriorityQueue<Item> q = cs.get(cuisine);
            while (q.peek().invalid) {
                q.poll();
            }
            return q.peek().f;
        }
    }
}

