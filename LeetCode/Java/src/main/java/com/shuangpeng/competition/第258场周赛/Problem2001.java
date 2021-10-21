package com.shuangpeng.competition.第258场周赛;

import java.util.HashMap;
import java.util.Map;

public class Problem2001 {

    // 比赛时写法
    public long interchangeableRectangles0(int[][] rectangles) {
        Map<String, Integer> map = new HashMap<>();
        for (int[] rect : rectangles) {
            int w = rect[0], h = rect[1];
            int g = gcd(w, h);
            String key = "" + (w / g) + '/' + (h / g);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        long ans = 0;
        for (String key : map.keySet()) {
            int value = map.get(key);
            if (value >= 2) {
                ans += ((long) value * (value - 1)) >> 1;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public long interchangeableRectangles(int[][] rectangles) {
        Map<String, Integer> map = new HashMap<>();
        for (int[] rectangle : rectangles) {
            int a = gcd(rectangle[0], rectangle[1]);
            String key = rectangle[0] / a + "/" + rectangle[1] / a;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        long ans = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            ans += (long) value * (value - 1) >> 1;
        }
        return ans;
    }
}
