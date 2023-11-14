package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2211CountCollisionsOnARoad（统计道路上的碰撞次数）
 * @date 2023/11/13 6:15 PM
 */
public class Problem2211CountCollisionsOnARoad {

    public int countCollisions(String directions) {
        int n = directions.length();
        boolean hasRight = false;
        int ans = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            char c = directions.charAt(i);
            if (c == 'L') {
                if (hasRight) {
                    ans++;
                }
                ans += cnt;
                cnt = 0;
            } else if (c == 'R') {
                cnt++;
                hasRight = true;
            } else {
                ans += cnt;
                cnt = 0;
                hasRight = true;
            }
        }
        return ans;
    }
}
