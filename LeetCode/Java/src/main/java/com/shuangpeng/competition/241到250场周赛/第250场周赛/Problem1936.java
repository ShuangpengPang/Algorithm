package com.shuangpeng.competition.第250场周赛;

public class Problem1936 {

    public int addRungs0(int[] rungs, int dist) {
        int answer = 0;
        int num = 0;
        int n = rungs.length;
        int i = 0;
        while (i < n) {
            if (rungs[i] > num + dist) {
                int diff = rungs[i] - num;
                int count = diff / dist;
                answer += count;
                num += count * dist;
                if (num == rungs[i]) {
                    answer--;
                    i++;
                }
                continue;
            } else {
                num = rungs[i];
            }
            i++;
        }
        return answer;
    }

    public int addRungs1(int[] rungs, int dist) {
        int n = rungs.length;
        int i = 0, num = 0;
        int count = 0;
        while (i < n) {
            if (num + dist < rungs[i]) {
                int diff = rungs[i] - num;
                int add = (diff - 1) / dist;
                count += add;
                num += add * dist;
            } else {
                num = rungs[i];
                i++;
            }
        }
        return count;
    }

    public int addRungs(int[] rungs, int dist) {
        int count = 0, num = 0;
        for (int rung : rungs) {
            count += (rung - num - 1) / dist;
            num = rung;
        }
        return count;
    }
}
