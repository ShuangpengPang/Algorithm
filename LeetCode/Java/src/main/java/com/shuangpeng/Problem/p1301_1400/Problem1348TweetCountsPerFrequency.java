package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1348TweetCountsPerFrequency（推文计数）
 * @date 2023/8/4 6:37 PM
 */
public class Problem1348TweetCountsPerFrequency {

    static class TweetCounts {

        static class Segment {
            int start, end, count;
            Segment left, right;

            Segment(int start, int end) {
                this.start = start;
                this.end = end;
                count = 0;
            }

            void add(int time) {
                count++;
                if (start == end) {
                    return;
                }
                int mid = start + (end - start >> 1);
                split(mid);
                if (time <= mid) {
                    left.add(time);
                } else {
                    right.add(time);
                }
            }

            int query(int time) {
                if (time < start) {
                    return 0;
                }
                if (time >= end) {
                    return count;
                }
                int mid = start + (end - start >> 1);
                split(mid);
                if (time <= mid) {
                    return left.query(time);
                }
                return left.count + right.query(time);
            }

            void split(int mid) {
                if (left == null) {
                    left = new Segment(start, mid);
                }
                if (right == null) {
                    right = new TweetCounts.Segment(mid + 1, end);
                }
            }
        }

        static int minute = 60, hour = 60 * 60, day = 24 * hour, N = (int) 1e9 + 2;
        Map<String, Segment> map;

        public TweetCounts() {
            map = new HashMap<>();
        }

        public void recordTweet(String tweetName, int time) {
            map.computeIfAbsent(tweetName, k -> new Segment(-1, N)).add(time);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            int step = freq.equals("minute") ? minute : freq.equals("hour") ? hour : day;
            int n = (endTime - startTime) / step + 1;
            List<Integer> list = new ArrayList<>(n);
            Segment segment = map.get(tweetName);
            if (segment == null) {
                for (int i = 0; i < n; i++) {
                    list.add(0);
                }
                return list;
            }
            int p = segment.query(startTime - 1);
            for (int t = startTime; t <= endTime; t += step) {
                int count = segment.query(Math.min(endTime, t + step - 1));
                list.add(count - p);
                p = count;
            }
            return list;
        }
    }
/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */
}
