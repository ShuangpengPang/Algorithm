package com.shuangpeng.Problem.p1601_1700;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1604AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod（警告一小时内使用相同员工卡大于等于三次的人）
 * @date 2023/2/9 5:02 PM
 */
public class Problem1604AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod {

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        int n = keyName.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparing(a -> keyTime[a]));
        Map<String, Deque<Integer>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for (int id : ids) {
            String name = keyName[id], t = keyTime[id];
            if (!set.contains(name)) {
                int time = Integer.parseInt(t.substring(0, 2)) * 60 + Integer.parseInt(t.substring(3));
                Deque<Integer> q = map.computeIfAbsent(name, k -> new ArrayDeque<>());
                while (!q.isEmpty() && q.peekFirst() < time - 60) {
                    q.pollFirst();
                }
                q.addLast(time);
                if (q.size() >= 3) {
                    set.add(name);
                    ans.add(name);
                }
            }
        }
        ans.sort(String::compareTo);
        return ans;
    }
}
