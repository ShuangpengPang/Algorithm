package com.shuangpeng.Problem.p3401_3500;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3408DesignTaskManager（设计任务管理器）
 * @date 2025/4/14 19:01
 */
public class Problem3408DesignTaskManager {

    class TaskManager {

        private Map<Integer, Integer> users, priorities;
        private PriorityQueue<int[]> pq;

        public TaskManager(List<List<Integer>> tasks) {
            users = new HashMap<>();
            priorities = new HashMap<>();
            pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
            for (List<Integer> task : tasks) {
                add(task.get(0), task.get(1), task.get(2));
            }
        }

        public void add(int userId, int taskId, int priority) {
            users.put(taskId, userId);
            priorities.put(taskId, priority);
            pq.offer(new int[]{priority, taskId});
        }

        public void edit(int taskId, int newPriority) {
            int userId = users.get(taskId);
            rmv(taskId);
            add(userId, taskId, newPriority);
        }

        public void rmv(int taskId) {
            users.remove(taskId);
            priorities.remove(taskId);
        }

        public int execTop() {
            while (!pq.isEmpty() && (!priorities.containsKey(pq.peek()[1]) || pq.peek()[0] != priorities.get(pq.peek()[1]))) {
                pq.poll();
            }
            if (pq.isEmpty()) {
                return -1;
            }
            int taskId = pq.poll()[1], userId = users.get(taskId);
            rmv(taskId);
            return userId;
        }
    }

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */
}

class Problem3408DesignTaskManager0 {

    class TaskManager {

        Map<Integer, int[]> taskMap;
        PriorityQueue<int[]> pq;

        public TaskManager(List<List<Integer>> tasks) {
            taskMap = new HashMap<>();
            pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
            for (List<Integer> task : tasks) {
                add(task.get(0), task.get(1), task.get(2));
            }
        }

        public void add(int userId, int taskId, int priority) {
            taskMap.put(taskId, new int[]{priority, userId});
            pq.offer(new int[]{priority, taskId});
        }

        public void edit(int taskId, int newPriority) {
            add(taskMap.get(taskId)[1], taskId, newPriority);
        }

        public void rmv(int taskId) {
            taskMap.get(taskId)[0] = -1;
        }

        public int execTop() {
            while (!pq.isEmpty()) {
                int[] p = pq.poll(), a = taskMap.get(p[1]);
                if (p[0] == a[0]) {
                    a[0] = -1;
                    return a[1];
                }
            }
            return -1;
        }
    }
}
