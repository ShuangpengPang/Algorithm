package com.shuangpeng.Problem.p3201_3300;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3248SnakeInMatrix（矩阵中的蛇）
 * @date 2024/11/12 5:02 PM
 */
public class Problem3248SnakeInMatrix {

    public int finalPositionOfSnake0(int n, List<String> commands) {
        int right = 0, down = 0;
        for (String c : commands) {
            if (c.equals("UP")) {
                down--;
            } else if (c.equals("RIGHT")) {
                right++;
            } else if (c.equals("DOWN")) {
                down++;
            } else {
                right--;
            }
        }
        return down * n + right;
    }

    public int finalPositionOfSnake(int n, List<String> commands) {
        int ans = 0;
        for (String c : commands) {
            char ch = c.charAt(0);
            if (ch == 'U') {
                ans -= n;
            } else if (ch == 'R') {
                ans++;
            } else if (ch == 'D') {
                ans += n;
            } else {
                ans--;
            }
        }
        return ans;
    }
}
