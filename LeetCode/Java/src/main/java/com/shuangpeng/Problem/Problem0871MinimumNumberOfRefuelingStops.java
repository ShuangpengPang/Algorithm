package com.shuangpeng.Problem;

import java.util.*;

public class Problem0871MinimumNumberOfRefuelingStops {

    public int minRefuelStops0(int target, int startFuel, int[][] stations) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{0, startFuel});
        for (int i = 0; i < stations.length; ++i) {
            int[] last = list.get(list.size() - 1);
            if (last[0] == stations[i][0]) {
                last[1] += stations[i][1];
            } else {
                list.add(new int[]{stations[i][0], stations[i][1]});
            }
        }
        int n = list.size();
        Map<Integer, Integer> positionMap = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            positionMap.put(list.get(i)[0], i);
        }
        // 0 : count, 1: position, 2: fuel
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return (b[1] + b[2]) - (a[1] + a[2]);
        });
        queue.offer(new int[]{0, 0, list.get(0)[1]});
        int[] memo = new int[n];
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int count = tuple[0];
            int position = tuple[1];
            int fuel = tuple[2];
            if (target <= position + fuel) {
                return count;
            }
            for (int i = positionMap.get(position) + 1; i < n; ++i) {
                int[] pair = list.get(i);
                if (position + fuel >= pair[0]) {
                    int f = pair[1] + position + fuel - pair[0];
                    if (f > memo[i]) {
                        memo[i] = f;
                        queue.offer(new int[]{count + 1, pair[0], f});
                    }
                } else {
                    break;
                }
            }
        }
        return -1;
    }

    // TLE
    public int minRefuelStops1(int target, int startFuel, int[][] stations) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{0, startFuel});
        for (int i = 0; i < stations.length; ++i) {
            int[] last = list.get(list.size() - 1);
            if (last[0] == stations[i][0]) {
                last[1] += stations[i][1];
            } else {
                list.add(new int[]{stations[i][0], stations[i][1]});
            }
        }
        int n = list.size();
        Map<Integer, Integer> positionMap = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            positionMap.put(list.get(i)[0], i);
        }
        int[] memo = new int[n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, list.get(0)[1]});
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] pair = queue.poll();
                int position = pair[0];
                int fuel = pair[1];
                if (target <= position + fuel) {
                    return count;
                }
                for (int j = positionMap.get(position) + 1; j < n; ++j) {
                    int p = list.get(j)[0];
                    int f = list.get(j)[1];
                    if (position + fuel >= p) {
                        f += position + fuel - p;
                        if (f > memo[j]) {
                            memo[j] = f;
                            queue.offer(new int[]{p, f});
                        }
                    } else {
                        break;
                    }
                }
            }
            count++;
        }
        return -1;
    }

    // 错误做法
    public int minRefuelStops2(int target, int startFuel, int[][] stations) {
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{0, startFuel});
        for (int i = 0; i < stations.length; ++i) {
            int[] last = list.get(list.size() - 1);
            if (last[0] == stations[i][0]) {
                last[1] += stations[i][1];
            } else {
                list.add(new int[]{stations[i][0], stations[i][1]});
            }
        }
        int n = list.size();
        if (target <= list.get(0)[1]) {
            return 0;
        }
        int[] dp = new int[n + 2];
        dp[1] = list.get(0)[1];
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < n; ++j) {
                int position = list.get(j)[0];
                if (position > dp[i - 2] && position <= dp[i - 1]) {
                    dp[i] = Math.max(dp[i], dp[i - 1] + list.get(j)[1]);
                    if (dp[i] >= target) {
                        return i - 1;
                    }
                } else if (position > dp[i - 1]) {
                    break;
                }
            }
            if (dp[i] == dp[i - 1]) {
                break;
            }
        }
        return -1;
    }

    public int minRefuelStops3(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        long[] dp = new long[n + 1];
        dp[0] = startFuel;
        for (int i = 0; i < n; ++i) {
            for (int j = i; j >= 0 && dp[j] >= stations[i][0]; --j) {
                dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
            }
            if (dp[i] == dp[i + 1]) {
                break;
            }
        }
        for (int i = 0; i <= n; ++i) {
            if (dp[i] >= target) {
                return i;
            }
        }
        return -1;
    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        int maxDistance = startFuel;
        int i = 0;
        int count = 0;
        while (maxDistance < target) {
            while (i < n && maxDistance >= stations[i][0]) {
                queue.offer(stations[i][1]);
                i++;
            }
            if (!queue.isEmpty()) {
                maxDistance += queue.poll();
                count++;
            } else {
                return -1;
            }
        }
        return count;
    }
}
