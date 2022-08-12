package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Problem1282GroupThePeopleGivenTheGroupSizeTheyBelongTo（用户分组）
 * @Date 2022/8/12 10:33 AM
 * @Version 1.0
 */
public class Problem1282GroupThePeopleGivenTheGroupSizeTheyBelongTo {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        int n = groupSizes.length;
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int cnt = groupSizes[i];
            int idx = 0;
            if (!map.containsKey(cnt) || ans.get(map.get(cnt)).size() == cnt) {
                idx = ans.size();
            } else {
                idx = map.get(cnt);
            }
            map.put(cnt, idx);
            if (idx == ans.size()) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                ans.add(list);
            } else {
                ans.get(idx).add(i);
            }
        }
        return ans;
    }
}
