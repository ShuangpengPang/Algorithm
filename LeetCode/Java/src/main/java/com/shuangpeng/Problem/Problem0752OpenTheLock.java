package com.shuangpeng.Problem;

import java.util.*;
import java.util.concurrent.locks.Lock;

public class Problem0752OpenTheLock {

    public int openLock0(String[] deadends, String target) {
        Set<Integer> deadSet = new HashSet<>();
        for (String dead : deadends) {
            deadSet.add(Integer.parseInt(dead));
        }
        if (deadSet.contains(0)) {
            return -1;
        }
        int t = Integer.parseInt(target);
        if (t == 0) {
            return 0;
        }
        boolean[] visited = new boolean[10000];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[4]);
        visited[0] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] data = queue.poll();
                for (int j = 0; j < data.length; j++) {
                    for (int k = -1; k <= 1; k = k + 2) {
                        int origin = data[j];
                        data[j] = (data[j] + 10 + k) % 10;
                        int value = toInt(data);
                        if (value == t) {
                            return step;
                        }
                        if (!visited[value] && !deadSet.contains(value)) {
                            visited[value] = true;
                            queue.offer(Arrays.copyOf(data, data.length));
                        }
                        data[j] = origin;
                    }
                }
            }
        }
        return -1;
    }

    private int toInt(int[] data) {
        return data[0] * 1000 + data[1] * 100 + data[2] * 10 + data[3];
    }



    class LockInfo {
        String lock;
        int f;
        int g;
        int h;

        LockInfo(String lock, int g, int h) {
            this.lock = lock;
            this.g = g;
            this.h = h;
            this.f = g + h;
        }
    }

    public int openLock1(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>();
        for (String dead : deadends) {
            deadSet.add(dead);
        }
        String s = "0000";
        if (deadSet.contains(s)) {
            return -1;
        }
        if (target.equals(s)) {
            return 0;
        }
        PriorityQueue<LockInfo> queue = new PriorityQueue<>((a, b) -> {
            if (a.f != b.f) {
                return a.f - b.f;
            }
            return b.g - a.g;
        });
        Set<String> closeSet = new HashSet<>();
        queue.add(new LockInfo(s, 0, heuristic(s, target)));
        while (!queue.isEmpty()) {
            LockInfo lockInfo = queue.poll();
            String lock = lockInfo.lock;
            if (closeSet.contains(lock)) {
                continue;
            }
            closeSet.add(lock);
            char[] chars = lock.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                for (int j = -1; j <= 1; j = j + 2) {
                    char c = chars[i];
                    int value = (c - '0' + j + 10) % 10;
                    chars[i] = (char) ('0' + value);
                    String string = new String(chars);
                    if (string.equals(target)) {
                        return lockInfo.g + 1;
                    }
                    if (!deadSet.contains(string) && !closeSet.contains(string)) {
                        queue.add(new LockInfo(string, lockInfo.g + 1,  heuristic(string, target)));
                    }
                    chars[i] = c;
                }
            }
        }
        return -1;
    }

    private int heuristic(String s, String t) {
        int n = s.length();
        int d = 0;
        for (int i = 0; i < n; i++) {
            int abs = Math.abs(s.charAt(i) - t.charAt(i));
            d += Math.min(abs, 10 - abs);
        }
        return d;
    }

    public int openLock(String[] deadends, String target) {
        if ("0000".equals(target)) {
            return 0;
        }

        Set<String> dead = new HashSet<String>();
        for (String deadend : deadends) {
            dead.add(deadend);
        }
        if (dead.contains("0000")) {
            return -1;
        }

        PriorityQueue<AStar> pq = new PriorityQueue<AStar>((a, b) -> a.f - b.f);
        pq.offer(new AStar("0000", target, 0));
        Set<String> seen = new HashSet<String>();
        seen.add("0000");

        while (!pq.isEmpty()) {
            AStar node = pq.poll();
            for (String nextStatus : get(node.status)) {
                if (!seen.contains(nextStatus) && !dead.contains(nextStatus)) {
                    if (nextStatus.equals(target)) {
                        return node.g + 1;
                    }
                    pq.offer(new AStar(nextStatus, target, node.g + 1));
                    seen.add(nextStatus);
                }
            }
        }

        return -1;
    }

    public char numPrev(char x) {
        return x == '0' ? '9' : (char) (x - 1);
    }

    public char numSucc(char x) {
        return x == '9' ? '0' : (char) (x + 1);
    }

    // 枚举 status 通过一次旋转得到的数字
    public List<String> get(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        for (int i = 0; i < 4; ++i) {
            char num = array[i];
            array[i] = numPrev(num);
            ret.add(new String(array));
            array[i] = numSucc(num);
            ret.add(new String(array));
            array[i] = num;
        }
        return ret;
    }

    class AStar {
        String status;
        int f, g, h;

        public AStar(String status, String target, int g) {
            this.status = status;
            this.g = g;
            this.h = getH(status, target);
            this.f = this.g + this.h;
        }

        // 计算启发函数
        public int getH(String status, String target) {
            int ret = 0;
            for (int i = 0; i < 4; ++i) {
                int dist = Math.abs(status.charAt(i) - target.charAt(i));
                ret += Math.min(dist, 10 - dist);
            }
            return ret;
        }
    }
}
