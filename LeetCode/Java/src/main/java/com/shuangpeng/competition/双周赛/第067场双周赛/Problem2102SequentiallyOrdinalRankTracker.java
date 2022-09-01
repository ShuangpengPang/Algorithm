package com.shuangpeng.competition.双周赛.第067场双周赛;

import java.util.PriorityQueue;

public class Problem2102SequentiallyOrdinalRankTracker {
}

class SORTracker {

    class Scenic {
        String name;
        int score;

        Scenic(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    PriorityQueue<Scenic> best, worst;

    public SORTracker() {
        best = new PriorityQueue<>((a, b) -> {
            if (b.score != a.score) {
                return b.score - a.score;
            }
            return a.name.compareTo(b.name);
        });
        worst = new PriorityQueue<>((a, b) -> {
            if (a.score != b.score) {
                return a.score - b.score;
            }
            return b.name.compareTo(a.name);
        });
    }

    public void add(String name, int score) {
        Scenic scenic = new Scenic(name, score);
        if (worst.isEmpty() || score < worst.peek().score || (score == worst.peek().score && name.compareTo(worst.peek().name) > 0)) {
            best.offer(scenic);
        } else {
            best.offer(worst.poll());
            worst.offer(scenic);
        }
    }

    public String get() {
        worst.offer(best.poll());
        return worst.peek().name;
    }
}

/**
 * Your SORTracker object will be instantiated and called as such:
 * SORTracker obj = new SORTracker();
 * obj.add(name,score);
 * String param_2 = obj.get();
 */
