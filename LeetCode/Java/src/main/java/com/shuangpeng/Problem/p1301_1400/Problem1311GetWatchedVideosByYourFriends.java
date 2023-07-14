package com.shuangpeng.Problem.p1301_1400;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1311GetWatchedVideosByYourFriends（获取你好友已观看的视频）
 * @date 2023/7/14 5:21 PM
 */
public class Problem1311GetWatchedVideosByYourFriends {

    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int n = friends.length;
        boolean[] visited = new boolean[n];
        Set<Integer> peoples = new HashSet<>();
        visited[id] = true;
        peoples.add(id);
        for (int i = 0; i < level; i++) {
            Set<Integer> tmp = new HashSet<>();
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
        List<Pair<String, Integer>> list = new ArrayList<>(freq.size());
        for (String key : freq.keySet()) {
            list.add(new Pair<>(key, freq.get(key)));
        }
        list.sort((a, b) -> a.getValue() != b.getValue() ? a.getValue() - b.getValue() : a.getKey().compareTo(b.getKey()));
        List<String> ans = new ArrayList<>(list.size());
        for (Pair<String, Integer> pair : list) {
            ans.add(pair.getKey());
        }
        return ans;
    }
}
