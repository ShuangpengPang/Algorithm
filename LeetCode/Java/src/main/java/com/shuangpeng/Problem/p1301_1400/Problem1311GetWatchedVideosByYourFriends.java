package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1311GetWatchedVideosByYourFriends（获取你好友已观看的视频）
 * @date 2023/7/14 5:21 PM
 */
public class Problem1311GetWatchedVideosByYourFriends {

    public List<String> watchedVideosByFriends0(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        List<Integer> peoples = new ArrayList<>();
        visited[id] = true;
        peoples.add(id);
        for (int i = 0; i < level; i++) {
            List<Integer> tmp = new ArrayList<>();
            for (int p : peoples) {
                for (int f : friends[p]) {
                    if (!visited[f]) {
                        visited[f] = true;
                        tmp.add(f);
                    }
                }
            }
            peoples = tmp;
        }
        Map<String, Integer> freq = new HashMap<>();
        for (int p : peoples) {
            for (String v : watchedVideos.get(p)) {
                freq.merge(v, 1, Integer::sum);
            }
        }
        List<String> ans = new ArrayList<>(freq.keySet());
        ans.sort((a, b) -> freq.get(a).equals(freq.get(b)) ? a.compareTo(b) : freq.get(a) - freq.get(b));
        return ans;
    }

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>(n);
        q.offer(id);
        visited[id] = true;
        for (int i = 0; i < level && !q.isEmpty(); i++) {
            for (int j = q.size() - 1; j >= 0; j--) {
                for (int y : friends[q.poll()]) {
                    if (!visited[y]) {
                        visited[y] = true;
                        q.offer(y);
                    }
                }
            }
        }
        Map<String, Integer> freq = new HashMap<>();
        for (int i = q.size() - 1; i >= 0; i--) {
            for (String v : watchedVideos.get(q.poll())) {
                freq.merge(v, 1, Integer::sum);
            }
        }
        List<String> ans = new ArrayList<>(freq.keySet());
        ans.sort((a, b) -> freq.get(a) != freq.get(b) ? freq.get(a) - freq.get(b) : a.compareTo(b));
        return ans;
    }
}
