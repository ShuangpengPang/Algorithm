package com.shuangpeng.competition.第301场周赛;

import java.util.TreeSet;

/**
 * @Description: Problem2336SmallestNumberInInfiniteSet（无限集中的最小数字）
 * @Date 2022/7/20 4:53 PM
 * @Version 1.0
 */
public class Problem2336SmallestNumberInInfiniteSet {

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

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */
}


