package com.shuangpeng.Problem.p0601_0700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0657RobotReturnToOrigin（机器人能否返回原点）
 * @date 2023/12/14 12:43 PM
 */
public class Problem0657RobotReturnToOrigin {

    public boolean judgeCircle(String moves) {
        int x = 0, y = 0, n = moves.length();
        for (int i = 0; i < n; i++) {
            char m = moves.charAt(i);
            if (m == 'U') {
                x += 1;
            } else if (m == 'D') {
                x -= 1;
            } else if (m == 'L') {
                y += 1;
            } else {
                y -= 1;
            }
        }
        return x == 0 && y == 0;
    }
}
