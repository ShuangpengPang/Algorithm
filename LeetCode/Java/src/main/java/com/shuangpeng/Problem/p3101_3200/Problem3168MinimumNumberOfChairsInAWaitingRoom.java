package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3168MinimumNumberOfChairsInAWaitingRoom（候诊室中的最少椅子数）
 * @date 2024/6/14 12:22 AM
 */
public class Problem3168MinimumNumberOfChairsInAWaitingRoom {

    public int minimumChairs(String s) {
        int maxCnt = 0, cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == 'E') {
                maxCnt = Math.max(maxCnt, ++cnt);
            } else {
                cnt--;
            }
        }
        return maxCnt;
    }
}
