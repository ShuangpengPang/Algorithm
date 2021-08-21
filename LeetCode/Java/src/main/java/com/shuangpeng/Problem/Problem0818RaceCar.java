package com.shuangpeng.Problem;

import java.util.*;

public class Problem0818RaceCar {

    public int racecar0(int target) {
        final int MIN = -(target * 10), MAX = target * 10, D = (target << 1);
        Set<int[]> seen = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 1, 0});
        seen.add(new int[]{0, 1});
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int position = tuple[0];
            int speed = tuple[1];
            int count = tuple[2];
            if (position + speed >= MIN && position + speed <= MAX) {
                int p = position + speed;
                int s = speed << 1;
                if (p == target) {
                    return count + 1;
                }
                check(p, s, queue, count + 1, seen, D);
            }
            int s = speed > 0 ? -1 : 1;
            check(position, s, queue, count + 1, seen, D);
        }
        return -1;
    }

    private void check(int position, int speed, Queue<int[]> queue, int count, Set<int[]> seen, int D) {
        int[] pair = new int[]{position, speed};
        if (!seen.contains(pair) && !seen.contains(new int[]{D - position, speed})) {
            queue.offer(new int[]{position, speed, count});
            seen.add(pair);
        }
    }

    public int racecar1(int target) {
        final int M = (target << 1) + 2;
        int[] dp = new int[M];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; (1 << i) < M; ++i) {
            dp[(1 << i) - 1] = i;
        }
        if (dp[target] != Integer.MAX_VALUE) {
            return dp[target];
        }
        for (int i = 1; i <= target; ++i) {
            if (dp[i] != Integer.MAX_VALUE) {
                continue;
            }
            int j = 1;
            while ((1 << j) - 1 < i) {
                dp[i] = Math.min(dp[i], j + 2 + dp[i - (1 << j) + 1]);
                j++;
            }
            dp[i] = Math.min(dp[i], j + 1 + dp[(1 << j) - 1 - i]);
        }
        return dp[target];
    }

    public int racecar(int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= target; ++i) {
            int k = 32 - Integer.numberOfLeadingZeros(i);
            if ((1 << k) - 1 == i) {
                dp[i] = k;
                continue;
            }
            for (int j = 0; j < k - 1; ++j) {
                dp[i] = Math.min(dp[i], dp[i - (1 << (k - 1)) + (1 << j)] + k + j + 1);
            }
            dp[i] = Math.min(dp[i], dp[(1 << k) - 1 - i] + k + 1);
        }
        return dp[target];
    }

//    public static void main(String[] args) {
//        Problem0818RaceCar a = new Problem0818RaceCar();
//        a.racecar(5);
//    }

    class Solution {
        public int racecar(int target) {
            int K = 33 - Integer.numberOfLeadingZeros(target - 1);
            int barrier = 1 << K;
            int[] dist = new int[2 * barrier + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[target] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<Node>(
                    (a, b) -> a.steps - b.steps);
            pq.offer(new Node(0, target));

            while (!pq.isEmpty()) {
                Node node = pq.poll();
                int steps = node.steps, targ1 = node.target;
                if (dist[Math.floorMod(targ1, dist.length)] > steps) continue;

                for (int k = 0; k <= K; ++k) {
                    int walk = (1 << k) - 1;
                    int targ2 = walk - targ1;
                    int steps2 = steps + k + (targ2 != 0 ? 1 : 0);

                    if (Math.abs(targ2) <= barrier && steps2 < dist[Math.floorMod(targ2, dist.length)]) {
                        pq.offer(new Node(steps2, targ2));
                        dist[Math.floorMod(targ2, dist.length)] = steps2;
                    }
                }
            }

            return dist[0];
        }
    }

    class Node {
        int steps, target;
        Node(int s, int t) {
            steps = s;
            target = t;
        }
    }

//    public static void main(String[] args) {
//        Problem0818RaceCar a = new Problem0818RaceCar();
//        a.racecar(330);
//    }
}
