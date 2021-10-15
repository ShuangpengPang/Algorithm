package com.shuangpeng.competition.第062场双周赛;

import java.util.HashMap;
import java.util.Map;

public class Problem2023 {

    public int numOfPairs0(String[] nums, String target) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : nums) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        int n = target.length();
        int ans = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String num = entry.getKey();
            if (num.length() < n && target.startsWith(num) && map.containsKey(target.substring(num.length()))) {
                int count = entry.getValue();
                entry.setValue(count - 1);
                ans += count * map.get(target.substring(num.length()));
                entry.setValue(count);
            }
        }
        return ans;
    }

    public int numOfPairs(String[] nums, String target) {
        Map<String, Integer> map = new HashMap<>();
        for (String num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int n = target.length();
        int ans = 0;
        for (String str : map.keySet()) {
            int length = str.length();
            int count1 = map.get(str);
            if (length < n && target.startsWith(str)) {
                String str1 = target.substring(length);
                int count2 = map.getOrDefault(str1, 0);
                if (count2 > 0) {
                    if (str.equals(str1)) {
                        ans += count1 * (count1 - 1);
                    } else {
                        ans += count1 * count2;
                    }
                }
            }
        }
        return ans;
    }
}
