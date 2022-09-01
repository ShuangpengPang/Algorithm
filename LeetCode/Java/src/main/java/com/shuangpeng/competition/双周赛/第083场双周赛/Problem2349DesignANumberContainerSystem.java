package com.shuangpeng.competition.双周赛.第083场双周赛;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Description: Problem2349DesignANumberContainerSystem（设计数字容器系统）
 * @Date 2022/8/16 11:01 PM
 * @Version 1.0
 */
public class Problem2349DesignANumberContainerSystem {

    class NumberContainers {

        Map<Integer, TreeSet<Integer>> indices;
        Map<Integer, Integer> map;

        public NumberContainers() {
            indices = new HashMap<>();
            map = new HashMap<>();
        }

        public void change(int index, int number) {
            int old = map.getOrDefault(index, 0);
            if (old != 0) {
                indices.get(old).remove(index);
            }
            map.put(index, number);
            indices.putIfAbsent(number, new TreeSet());
            indices.get(number).add(index);
        }

        public int find(int number) {
            TreeSet<Integer> set = indices.get(number);
            if (set == null || set.isEmpty()) {
                return -1;
            }
            return set.first();
        }
    }

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */
}

class Problem2349DesignANumberContainerSystem0 {
    class NumberContainers {

        Map<Integer, Integer> indexToNumber;
        Map<Integer, TreeSet<Integer>> numberToIndices;

        public NumberContainers() {
            indexToNumber = new HashMap<>();
            numberToIndices = new HashMap<>();
        }

        public void change(int index, int number) {
            if (indexToNumber.containsKey(index)) {
                numberToIndices.get(indexToNumber.get(index)).remove(index);
            }
            indexToNumber.put(index, number);
            numberToIndices.putIfAbsent(number, new TreeSet<Integer>());
            numberToIndices.get(number).add(index);
        }

        public int find(int number) {
            if (!numberToIndices.containsKey(number)) {
                return -1;
            }
            TreeSet<Integer> set = numberToIndices.get(number);
            return set.isEmpty() ? -1 : set.first();
        }
    }
}

class Problem2349DesignANumberContainerSystem1 {

    class NumberContainers {

        Map<Integer, Integer> m;
        Map<Integer, TreeSet<Integer>> indices;

        public NumberContainers() {
            m = new HashMap<>();
            indices = new HashMap<>();
        }

        public void change(int index, int number) {
            Integer old = m.get(index);
            if (old != null) {
                indices.get(old).remove(index);
            }
            m.put(index, number);
            indices.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
        }

        public int find(int number) {
            TreeSet<Integer> set = indices.get(number);
            return set == null || set.isEmpty() ? -1 : set.first();
        }
    }
}

class NumberContainers {

    Map<Integer, Integer> m;
    Map<Integer, TreeSet<Integer>> indices;

    public NumberContainers() {
        m = new HashMap<>();
        indices = new HashMap<>();
    }

    public void change(int index, int number) {
        Integer old = m.get(index);
        if (old != null) {
            indices.get(old).remove(index);
        }
        m.put(index, number);
        indices.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
    }

    public int find(int number) {
        TreeSet<Integer> set = indices.get(number);
        return set == null || set.isEmpty() ? -1 : set.first();
    }
}

