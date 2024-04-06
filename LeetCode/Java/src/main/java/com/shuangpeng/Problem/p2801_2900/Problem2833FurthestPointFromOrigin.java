package com.shuangpeng.Problem.p2801_2900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2833FurthestPointFromOrigin（距离原点最远的点）
 * @date 2024/4/6 11:54 PM
 */
public class Problem2833FurthestPointFromOrigin {

    public int furthestDistanceFromOrigin(String moves) {
        int diff = 0, cnt = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'R') {
                diff++;
            } else if (c == 'L') {
                diff--;
            } else {
                cnt++;
            }
        }
        return Math.abs(diff) + cnt;
    }
}
