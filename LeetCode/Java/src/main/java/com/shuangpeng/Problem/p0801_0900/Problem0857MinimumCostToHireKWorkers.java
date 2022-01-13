package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0857MinimumCostToHireKWorkers {

    class Info {
        int wage;
        int quality;
        double unit;

        Info(int wage, int quality) {
            this.wage = wage;
            this.quality = quality;
            this.unit = (double) wage / quality;
        }
    }

    public double mincostToHireWorkers0(int[] quality, int[] wage, int k) {
        int n = quality.length;
        List<Info> workers = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            workers.add(new Info(wage[i], quality[i]));
        }
        Collections.sort(workers, (a, b) -> {
            if (a.unit < b.unit) {
                return -1;
            }
            if (a.unit > b.unit) {
                return 1;
            }
            return 0;
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int totalQuality = 0;
        for (int i = 0; i < k; ++i) {
            int q = workers.get(i).quality;
            totalQuality += q;
            pq.offer(q);
        }
        double ans = totalQuality * workers.get(k - 1).unit;
        for (int i = k; i < n; ++i) {
            Info w = workers.get(i);
            if (w.quality < pq.peek()) {
                totalQuality += w.quality - pq.poll();
                ans = Math.min(ans, totalQuality * w.unit);
                pq.offer(w.quality);
            }
        }
        return ans;
    }

    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int N = quality.length;
        Worker[] workers = new Worker[N];
        for (int i = 0; i < N; ++i)
            workers[i] = new Worker(quality[i], wage[i]);
        Arrays.sort(workers);

        double ans = 1e9;
        int sumq = 0;
        PriorityQueue<Integer> pool = new PriorityQueue();
        for (Worker worker: workers) {
            pool.offer(-worker.quality);
            sumq += worker.quality;
            if (pool.size() > K)
                sumq += pool.poll();
            if (pool.size() == K)
                ans = Math.min(ans, sumq * worker.ratio());
        }

        return ans;
    }

    class Worker implements Comparable<Worker> {
        public int quality, wage;
        public Worker(int q, int w) {
            quality = q;
            wage = w;
        }

        public double ratio() {
            return (double) wage / quality;
        }

        public int compareTo(Worker other) {
            return Double.compare(ratio(), other.ratio());
        }
    }

//    public static void main(String[] args) {
//        Problem0857MinimumCostToHireKWorkers a = new Problem0857MinimumCostToHireKWorkers();
//        int[] quality = {32,43,66,9,94,57,25,44,99,19};
//        int[] wage = {187,366,117,363,121,494,348,382,385,262};
//        a.mincostToHireWorkers(quality, wage, 4);
//    }
}
