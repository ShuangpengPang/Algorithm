package com.shuangpeng.Problem.p2901_3000;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2933HighAccessEmployees（高访问员工）
 * @date 2024/1/8 1:43 PM
 */
public class Problem2933HighAccessEmployees {

    public List<String> findHighAccessEmployees(List<List<String>> access_times) {
        access_times.sort((a, b) -> {
            if (!a.get(0).equals(b.get(0))) {
                return a.get(0).compareTo(b.get(0));
            }
            return a.get(1).compareTo(b.get(1));
        });
        List<String> ans = new ArrayList<>();
        int n = access_times.size();
        for (int i = 0, j = 0; i < n; i++) {
            String name = access_times.get(i).get(0);
            int time = getTime(access_times.get(i).get(1)) + 60;
            while (j < n && access_times.get(j).get(0).equals(name) && getTime(access_times.get(j).get(1)) < time) {
                j++;
            }
            if (j - i >= 3) {
                ans.add(name);
            }
            if (j - i >= 3 || j == n || !access_times.get(j).get(0).equals(name)) {
                while (j < n && access_times.get(j).get(0).equals(name)) {
                    j++;
                }
                i = j - 1;
            }
        }
        return ans;
    }

    private int getTime(String s) {
        return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(2, 4));
    }
}
