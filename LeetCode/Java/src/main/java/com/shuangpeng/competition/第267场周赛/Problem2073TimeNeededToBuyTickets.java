package com.shuangpeng.competition.第267场周赛;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem2073TimeNeededToBuyTickets {

    // 比赛时写法
    public int timeRequiredToBuy0(int[] tickets, int k) {
        int ans = 0;
        int n = tickets.length;
        boolean finish = false;
        while (!finish) {
            for (int i = 0; i < n; ++i) {
                if (tickets[i] == 0) {
                    continue;
                }
                ++ans;
                --tickets[i];
                if (i == k && tickets[i] == 0) {
                    finish = true;
                    break;
                }
            }
        }
        return ans;
    }

    public int timeRequiredToBuy1(int[] tickets, int k) {
        int n = tickets.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int i = 0; i < n; ++i) {
            pq.offer(new int[]{i, tickets[i]});
        }
        int ans = 0;
        int pre = 0;
        int count = k;
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            if (pair[0] != k && pair[1] != tickets[k]) {
                ans += (pair[1] - pre) * (pq.size() + 1);
            } else {
                ans += (pair[1] - pre - 1) * (pq.size() + 1) + count + 1;
                break;
            }
            pre = pair[1];
            if (pair[0] < k) {
                --count;
            }
        }
        return ans;
    }

    public int timeRequiredToBuy(int[] tickets, int k) {
        int n = tickets.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= k) {
                ans += Math.min(tickets[i], tickets[k]);
            } else {
                ans += Math.min(tickets[i], tickets[k] - 1);
            }
        }
        return ans;
    }
}
