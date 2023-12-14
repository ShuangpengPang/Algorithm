package com.shuangpeng.Problem.p0601_0700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0657RobotReturnToOrigin（机器人能否返回原点）
 * @date 2023/12/14 12:43 PM
 */
public class Problem0657RobotReturnToOrigin {

    public boolean judgeCircle(String moves) {
        int offset1 = 0, offset2 = 0, n = moves.length();
        for (int i = 0; i < n; i++) {
            char m = moves.charAt(i);
            if (m == 'U') {
                offset1 += 1;
            } else if (m == 'D') {
                offset1 -= 1;
            } else if (m == 'L') {
                offset2 += 1;
            } else {
                offset2 -= 1;
            }
        }
        return offset1 == 0 && offset2 == 0;
    }
}
