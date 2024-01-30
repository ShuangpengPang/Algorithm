package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1232CheckIfItIsAStraightLine（缀点成线）
 * @date 2024/1/30 10:12 AM
 */
public class Problem1232CheckIfItIsAStraightLine {

    public boolean checkStraightLine(int[][] coordinates) {
        int x = coordinates[1][0] - coordinates[0][0], y = coordinates[1][1] - coordinates[0][1];
        for (int i = 2; i < coordinates.length; i++) {
            if (x * (coordinates[i][1] - coordinates[0][1]) != y * (coordinates[i][0] - coordinates[0][0])) {
                return false;
            }
        }
        return true;
    }
}
