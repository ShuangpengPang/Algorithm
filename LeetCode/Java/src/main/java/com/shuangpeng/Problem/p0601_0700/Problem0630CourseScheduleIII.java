package com.shuangpeng.Problem.p0601_0700;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem0630CourseScheduleIII {

    public int scheduleCourse0(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int n = courses.length;
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            if (sum + courses[i][0] <= courses[i][1]) {
                ++ans;
                sum += courses[i][0];
                pq.offer(courses[i][0]);
            } else if (!pq.isEmpty() && pq.peek() > courses[i][0]) {
                sum -= pq.poll();
                sum += courses[i][0];
                pq.offer(courses[i][0]);
            }
        }
        return ans;
    }

    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        pq.offer(0);
        int duration = 0, count = 0;
        for (int[] course : courses) {
            if (duration + course[0] <= course[1]) {
                pq.offer(course[0]);
                duration += course[0];
                ++count;
            } else if (pq.peek() > course[0]) {
                duration -= pq.poll();
                pq.offer(course[0]);
                duration += course[0];
            }
        }
        return count;
    }
}
