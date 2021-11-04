package com.shuangpeng.Problem.p0601_0700;

import java.util.*;

public class Problem0621TaskScheduler {

//    public static void main(String[] args) {
//        Problem0621TaskScheduler a = new Problem0621TaskScheduler();
//        char[] tasks = {'A','A','A','B','B','B'};
////        char[] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
//        a.leastInterval(tasks, 2);
//    }

    public int leastInterval0(char[] tasks, int n) {
        int[] taskCount = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            taskCount[tasks[i] - 'A']++;
        }
        List<Integer> list = new ArrayList<>(taskCount.length);
        for (int i = 0; i < taskCount.length; i++) {
            if (taskCount[i] > 0) {
                list.add(taskCount[i]);
            }
        }
        Collections.sort(list, (a, b) -> b - a);
        int interval = 0;
        while (list.size() > 0) {
            int count = list.size();
            if (count > n + 1) {
                interval += n + 1;
                for (int i = 0; i < n + 1; i++) {
                    list.set(i, list.get(i) - 1);
                }
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) == 0) {
                        list.remove(i);
                        i = -1;
                    }
                }
                Collections.sort(list, (a, b) -> b - a);
            } else if (count > 1) {
                int min = list.get(count - 1);
                interval += (n + 1) * min;
                for (int i = count - 1; i >= 0; i--) {
                    list.set(i, list.get(i) - min);
                    if (list.get(i) == 0) {
                        list.remove(i);
                    }
                }
                if (list.size() == 0) {
                    interval -= n + 1 - count;
                }
            } else {
                interval += (n + 1) * (list.get(0) - 1) + 1;
                list.remove(0);
                break;
            }
        }
        return interval;
    }

    public int leastInterval1(char[] tasks, int n) {
        int[] map = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        Arrays.sort(map);
        int time = 0;
        int length = map.length;
        int count = 0;
        while (map[length - 1] > 0) {
            count = 0;
            for (int i = 0; i < n + 1; i++) {
                if (length - i - 1 >= 0 && map[length - i - 1] > 0) {
                    map[length - i - 1]--;
                    count++;
                }
                time++;
            }
            Arrays.sort(map);
        }
        return time - (n + 1 - count);
    }

    public int leastInterval2(char[] tasks, int n) {
        int length = 26;
        int[] map = new int[length];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        Arrays.sort(map);
        int time = 0;
        while (map[length - 1] > 0) {
            for (int i = 0; i < n + 1; i++) {
                if (map[length - 1] == 0) {
                    break;
                }
                if (length - i - 1 >= 0 && map[length - i - 1] > 0) {
                    map[length - i - 1]--;
                }
                time++;
            }
            Arrays.sort(map);
        }
        return time;
    }

    public int leastInterval3(char[] tasks, int n) {
        int length = 26;
        int[] map = new int[length];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(length, (a, b) -> b - a);
        for (int i = 0; i < length; i++) {
            if (map[i] > 0) {
                queue.offer(map[i]);
            }
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int[] array = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                if (!queue.isEmpty()) {
                    array[i] = queue.poll();
                    array[i]--;
                    time++;
                    if (array[0] == 0) {
                        break;
                    }
                } else {
                    time++;
                }
            }
            for (int i = 0; i < n + 1; i++) {
                if (array[i] > 0) {
                    queue.offer(array[i]);
                }
            }
        }
        return time;
    }

    public int leastInterval(char[] tasks, int n) {
        int length = 26;
        int[] map = new int[length];
        for (int i = 0; i < tasks.length; i++) {
            map[tasks[i] - 'A']++;
        }
        Arrays.sort(map);
        int maxValue = map[length - 1] - 1;
        int slots = maxValue * n;
        for (int i = length - 2; i >= 0; i--) {
            if (map[i] > 0) {
                slots -= Math.min(maxValue, map[i]);
            } else {
                break;
            }
        }
        return slots > 0 ? tasks.length + slots : tasks.length;
    }
}
