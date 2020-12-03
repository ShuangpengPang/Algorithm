package com.shuangpeng.Problem;

import java.util.*;

public class Problem0347TopKFrequentElements {

    public int[] topKFrequent0(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.compute(nums[i], (key, v) -> v == null ? 1 : v + 1);
        }
        int[] heap = new int[k + 1];
        int count = 0;
        int current = 1;
        for (int key : map.keySet()) {
            if (count < k) {
                insert(map, heap, current, key);
                count++;
                current++;
            } else if (map.get(key) > map.get(heap[1])) {
                heap[1] = key;
                sink(map, heap, 1, count);
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap[i + 1];
        }
        return result;
    }

    public void insert(Map<Integer, Integer> map, int[] heap, int current, int data) {
        heap[current] = data;
        int parent = current / 2;
        while (parent >= 1 && map.get(heap[current]) < map.get(heap[parent])) {
            heap[current] = heap[parent];
            heap[parent] = data;
            current = parent;
            parent = current / 2;
        }
    }

    public void sink(Map<Integer, Integer> map, int[] heap, int current, int last) {
        while (current * 2 <= last) {
            int left = current * 2;
            int right = left + 1;
            int min = left;
            if (right <= last && map.get(heap[right]) < map.get(heap[left])) {
                min = right;
            }
            if (map.get(heap[current]) > map.get(heap[min])) {
                int temp = heap[current];
                heap[current] = heap[min];
                heap[min] = temp;
                current = min;
            } else {
                break;
            }
        }
    }

    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (int num : map.keySet()) {
            int count = map.get(num);
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll()[0];
        }
        return result;
    }


//    public static void main(String[] args) {
//        Problem0347TopKFrequentElements a = new Problem0347TopKFrequentElements();
//        int[] nums = {1,1,1,2,2,3};
//        a.topKFrequent(nums, 2);
//    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int size = map.size();
        List<int[]> list = new ArrayList<>(size);
        Set<Integer> keySet = map.keySet();
        for (int num : keySet) {
            list.add(new int[]{num, map.get(num)});
        }
        int[] result = new int[k];
        qSort(list, result, new int[]{0}, 0, size - 1, k);
        return result;
    }

    public void qSort(List<int[]> list, int[] result, int[] count, int start, int end, int k) {
        if (k <= 0) {
            return;
        }
        int rand = (int) (Math.random() * (end - start + 1) + start);
        Collections.swap(list, start, rand);
        int pivot = list.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (list.get(i)[1] >= pivot) {
                Collections.swap(list, ++index, i);
            }
        }
        Collections.swap(list, start, index);
        if (k < (index - start + 1)) {
            qSort(list, result, count, start, index, k);
        } else if (k >= (index - start + 1)) {
            for (int i = start; i <= index; i++) {
                result[count[0]++] = list.get(i)[0];
            }
            qSort(list, result, count, index + 1, end, k - (index - start + 1));
        }
    }
}
