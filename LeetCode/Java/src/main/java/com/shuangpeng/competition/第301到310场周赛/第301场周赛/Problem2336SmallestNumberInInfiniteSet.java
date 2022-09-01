package com.shuangpeng.competition.第301到310场周赛.第301场周赛;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @Description: Problem2336SmallestNumberInInfiniteSet（无限集中的最小数字）
 * @Date 2022/7/20 4:53 PM
 * @Version 1.0
 */
public class Problem2336SmallestNumberInInfiniteSet {

    // 比赛时写法
    class SmallestInfiniteSet {

        TreeSet<Integer> set;

        public SmallestInfiniteSet() {
            set = new TreeSet<>();
            for (int i = 1; i <= 1000; i++) {
                set.add(i);
            }
        }

        public int popSmallest() {
            if (set.isEmpty()) {
                return 1001;
            }
            int ans = 0;
            for (int num : set) {
                ans = num;
                break;
            }
            set.remove(ans);
            return ans;
        }

        public void addBack(int num) {
            set.add(num);
        }
    }

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
}

class Problem2336SmallestNumberInInfiniteSet0 {

    class SmallestInfiniteSet {

        TreeSet<Integer> set;

        public SmallestInfiniteSet() {
            set = new TreeSet<>();
            for (int i = 1; i <= 1000; i++) {
                set.add(i);
            }
        }

        public int popSmallest() {
            if (set.isEmpty()) {
                return 1001;
            }
            return set.pollFirst();
        }

        public void addBack(int num) {
            set.add(num);
        }
    }
}

class Problem2336SmallestNumberInInfiniteSet1 {

    class SmallestInfiniteSet {

        Set<Integer> deletes;
        int min = 1;

        public SmallestInfiniteSet() {
            deletes = new HashSet<>();
        }

        public int popSmallest() {
            while (deletes.contains(min)) {
                min++;
            }
            deletes.add(min);
            return min;
        }

        public void addBack(int num) {
            deletes.remove(num);
            if (num < min) {
                min = num;
            }
        }
    }
}

class Problem2336SmallestNumberInInfiniteSet2 {

    class SmallestInfiniteSet {

        boolean[] deletes;
        int min;

        public SmallestInfiniteSet() {
            deletes = new boolean[1002];
            min = 1;
        }

        public int popSmallest() {
            while (deletes[min]) {
                min++;
            }
            deletes[min] = true;
            min++;
            return min - 1;
        }

        public void addBack(int num) {
            deletes[num] = false;
            if (num < min) {
                min = num;
            }
        }
    }
}


