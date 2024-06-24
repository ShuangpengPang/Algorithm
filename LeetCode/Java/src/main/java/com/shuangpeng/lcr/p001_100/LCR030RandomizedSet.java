package com.shuangpeng.lcr.p001_100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR030RandomizedSet（O(1) 时间插入、删除和获取随机元素）
 * @date 2024/6/18 10:38 AM
 */
public class LCR030RandomizedSet {

    class RandomizedSet {

        List<Integer> nums;
        Map<Integer, Integer> indices;
        Random random;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            nums = new ArrayList<>();
            indices = new HashMap<>();
            random = new Random();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (indices.containsKey(val)) {
                return false;
            }
            indices.put(val, nums.size());
            nums.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!indices.containsKey(val)) {
                return false;
            }
            int idx = indices.get(val);
            int last = nums.size() - 1, value = nums.get(last);
            nums.set(idx, value);
            indices.put(value, idx);
            nums.remove(last);
            indices.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            return nums.get(random.nextInt(nums.size()));
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
