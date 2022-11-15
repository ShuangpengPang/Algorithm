package com.shuangpeng.Problem.p1901_2000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1912DesignMovieRentalSystem（设计电影租借系统）
 * @date 2022/11/15 4:12 PM
 */
public class Problem1912DesignMovieRentalSystem {

    class MovieRentingSystem {

        private static final int MAX_COUNT = 5;
        private PriorityQueue<int[]> rent;
        private Map<Integer, PriorityQueue<int[]>> unRent;
        private Map<Long, Boolean> status;
        private Map<Long, Integer> price;

        public MovieRentingSystem(int n, int[][] entries) {
            rent = new PriorityQueue<>((a, b) -> {
                if (a[2] != b[2]) {
                    return a[2] - b[2];
                }
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                }
                return a[1] - b[1];
            });
            unRent = new HashMap<>();
            status = new HashMap<>();
            price = new HashMap<>();
            for (int[] entry : entries) {
                int s = entry[0], m = entry[1], p = entry[2];
                unRent.computeIfAbsent(m, k -> new PriorityQueue<>((a, b) -> {
                    if (a[1] != b[1]) {
                        return a[1] - b[1];
                    }
                    return a[0] - b[0];
                })).add(new int[]{s, p});
                long key = (long) s << 15 | m;
                status.put(key, false);
                price.put(key, p);
            }
        }

        public List<Integer> search(int movie) {
            PriorityQueue<int[]> q = unRent.get(movie);
            List<Integer> ans = new ArrayList<>();
            if (q == null) {
                return ans;
            }
            Set<Integer> set = new HashSet<>();
            List<int[]> list = new ArrayList<>();
            while (set.size() < MAX_COUNT && !q.isEmpty()) {
                int[] p = q.poll();
                int s = p[0];
                if (status.get((long) s << 15 | movie) || set.contains(s)) {
                    continue;
                }
                set.add(s);
                ans.add(s);
                list.add(p);
            }
            for (int[] p : list) {
                q.offer(p);
            }
            return ans;
        }

        public void rent(int shop, int movie) {
            long key = (long) shop << 15 | movie;
            status.put(key, true);
            rent.offer(new int[]{shop, movie, price.get(key)});
        }

        public void drop(int shop, int movie) {
            long key = (long) shop << 15 | movie;
            status.put(key, false);
            unRent.get(movie).offer(new int[]{shop, price.get(key)});
        }

        public List<List<Integer>> report() {
            List<List<Integer>> ans = new ArrayList<>();
            Set<Long> set = new HashSet<>();
            List<int[]> list = new ArrayList<>();
            while (set.size() < MAX_COUNT && !rent.isEmpty()) {
                int[] p = rent.poll();
                int s = p[0], m = p[1];
                long key = (long) s << 15 | m;
                if (!status.get(key) || set.contains(key)) {
                    continue;
                }
                set.add(key);
                list.add(p);
                ans.add(Arrays.asList(s, m));
            }
            for (int[] p : list) {
                rent.offer(p);
            }
            return ans;
        }
    }

/**
 * Your MovieRentingSystem object will be instantiated and called as such:
 * MovieRentingSystem obj = new MovieRentingSystem(n, entries);
 * List<Integer> param_1 = obj.search(movie);
 * obj.rent(shop,movie);
 * obj.drop(shop,movie);
 * List<List<Integer>> param_4 = obj.report();
 */
}

class Problem1912DesignMovieRentalSystem0 {

    class MovieRentingSystem {

        private static final int MAX_COUNT = 5;
        private TreeSet<Long> rent;
        private Map<Integer, TreeSet<Integer>> unRent;
        private Map<Long, Integer> price;

        public MovieRentingSystem(int n, int[][] entries) {
            price = new HashMap<>();
            rent = new TreeSet<>((a, b) -> {
                int p1 = price.get(a), p2 = price.get(b);
                if (p1 != p2) {
                    return p1 - p2;
                }
                int[] a1 = parse(a), a2 = parse(b);
                if (a1[0] != a2[0]) {
                    return a1[0] - a2[0];
                }
                return a1[1] - a2[1];
            });
            unRent = new HashMap<>();
            for (int[] entry : entries) {
                int s = entry[0], m = entry[1], p = entry[2];
                long key = getKey(s, m);
                price.put(key, p);
                unRent.computeIfAbsent(m, k -> new TreeSet<>((a, b) -> {
                    long k1 = getKey(a, m), k2 = getKey(b, m);
                    int p1 = price.get(k1), p2 = price.get(k2);
                    if (p1 != p2) {
                        return p1 - p2;
                    }
                    return a - b;
                })).add(s);
            }
        }

        public List<Integer> search(int movie) {
            List<Integer> ans = new ArrayList<>();
            TreeSet<Integer> set = unRent.get(movie);
            if (set == null) {
                return ans;
            }
            for (int s : set) {
                ans.add(s);
                if (ans.size() == MAX_COUNT) {
                    break;
                }
            }
            return ans;
        }

        public void rent(int shop, int movie) {
            long key = getKey(shop, movie);
            rent.add(key);
            unRent.get(movie).remove(shop);
        }

        public void drop(int shop, int movie) {
            long key = getKey(shop, movie);
            unRent.get(movie).add(shop);
            rent.remove(key);
        }

        public List<List<Integer>> report() {
            List<List<Integer>> ans = new ArrayList<>();
            for (long key : rent) {
                int[] a = parse(key);
                ans.add(Arrays.asList(a[0], a[1]));
                if (ans.size() == MAX_COUNT) {
                    break;
                }
            }
            return ans;
        }

        private long getKey(int s, int m) {
            return (long) s << 15 | m;
        }

        private int[] parse(long key) {
            return new int[]{(int) (key >> 15), (int) (key & ((1 << 15) - 1))};
        }
    }
}