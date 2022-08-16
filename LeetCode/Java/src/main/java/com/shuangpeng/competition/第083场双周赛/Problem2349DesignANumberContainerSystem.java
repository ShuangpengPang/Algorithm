package com.shuangpeng.competition.第083场双周赛;

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

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */
}

