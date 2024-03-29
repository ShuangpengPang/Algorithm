package com.shuangpeng.Problem.p0301_0400;

import java.util.*;

class Problem0380InsertDeleteGetRandom0 {

    class RandomizedSet {

//        private Set<Integer> set;
//
//        /** Initialize your data structure here. */
//        public RandomizedSet() {
//            set = new HashSet<>();
//        }
//
//        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
//        public boolean insert(int val) {
//            if (!set.contains(val)) {
//                set.add(val);
//                return true;
//            }
//            return false;
//        }
//
//        /** Removes a value from the set. Returns true if the set contained the specified element. */
//        public boolean remove(int val) {
//            if (set.contains(val)) {
//                set.remove(val);
//                return true;
//            }
//            return false;
//        }
//
//        /** Get a random element from the set. */
//        public int getRandom() {
//            int size = set.size();
//            int rand = (int) (Math.random() * size);
//            int i = 0;
//            for (int data : set) {
//                if (i == rand) {
//                    return data;
//                }
//                i++;
//            }
//            return 0;
//        }


//        private List<Integer>[] dataArray;
//        private int capacity = 200;
//        private int count = 0;
//
//        /** Initialize your data structure here. */
//        public RandomizedSet() {
//            dataArray = new ArrayList[capacity];
//        }
//
//        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
//        public boolean insert(int val) {
//            int backet = val % capacity;
//            backet = backet >= 0 ? backet : backet + capacity;
//            if (dataArray[backet] == null) {
//                dataArray[backet] = new ArrayList<>();
//                dataArray[backet].add(val);
//                count++;
//                return true;
//            }
//            List<Integer> list = dataArray[backet];
//            for (int data : list) {
//                if (data == val) {
//                    return false;
//                }
//            }
//            count++;
//            list.add(val);
//            return true;
//        }
//
//        /** Removes a value from the set. Returns true if the set contained the specified element. */
//        public boolean remove(int val) {
//            int backet = val % capacity;
//            backet = backet >= 0 ? backet : backet + capacity;
//            if (dataArray[backet] == null) {
//                return false;
//            }
//            List<Integer> list = dataArray[backet];
//            int size = list.size();
//            for (int i = 0; i < size; i++) {
//                if (list.get(i) == val) {
//                    list.remove(i);
//                    count--;
//                    return true;
//                }
//            }
//            return false;
//        }
//
//        /** Get a random element from the set. */
//        public int getRandom() {
//            int rand = (int) (Math.random() * count) + 1;
//            for (List<Integer> list : dataArray) {
//                if (list != null) {
//                    if (list.size() < rand) {
//                        rand -= list.size();
//                    } else {
//                        return list.get(rand - 1);
//                    }
//                }
//            }
//            return 0;
//        }


        private List<Integer> dataList;
        private Map<Integer, Integer> map;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            dataList = new ArrayList<>(150);
            map = new HashMap<>(150);
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, dataList.size());
            dataList.add(val);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int index = map.get(val);
            int last = dataList.get(dataList.size() - 1);
            if (index == dataList.size() - 1) {
                map.remove(last);
                dataList.remove(index);
                return true;
            }
            dataList.set(index, last);
            dataList.remove(dataList.size() - 1);
            map.remove(val);
            map.put(last, index);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int rand = (int) (Math.random() * dataList.size());
            return dataList.get(rand);
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

class Problem0380InsertDeleteGetRandom1 {
    class RandomizedSet {

        List<Integer> array;
        Map<Integer, Integer> map;
        Random random;

        public RandomizedSet() {
            array = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (!map.containsKey(val)) {
                map.put(val, array.size());
                array.add(val);
                return true;
            }
            return false;
        }

        public boolean remove(int val) {
            if (map.containsKey(val)) {
                int i = array.size() - 1;
                int last = array.get(i);
                if (val != last) {
                    int j = map.get(val);
                    array.set(j, last);
                    map.put(last, j);
                }
                array.remove(i);
                map.remove(val);
                return true;
            }
            return false;
        }

        public int getRandom() {
            return array.get(random.nextInt(array.size()));
        }
    }
}


public class Problem0380InsertDeleteGetRandom {
    class RandomizedSet {
        List<Integer> nums;
        Map<Integer, Integer> indices;
        Random random;

        public RandomizedSet() {
            nums = new ArrayList<Integer>();
            indices = new HashMap<Integer, Integer>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (indices.containsKey(val)) {
                return false;
            }
            int index = nums.size();
            nums.add(val);
            indices.put(val, index);
            return true;
        }

        public boolean remove(int val) {
            if (!indices.containsKey(val)) {
                return false;
            }
            int index = indices.get(val);
            int last = nums.get(nums.size() - 1);
            nums.set(index, last);
            indices.put(last, index);
            nums.remove(nums.size() - 1);
            indices.remove(val);
            return true;
        }

        public int getRandom() {
            int randomIndex = random.nextInt(nums.size());
            return nums.get(randomIndex);
        }
    }
}


