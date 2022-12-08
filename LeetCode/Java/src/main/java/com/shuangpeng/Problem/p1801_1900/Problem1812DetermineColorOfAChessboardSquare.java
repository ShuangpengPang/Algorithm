package com.shuangpeng.Problem.p1801_1900;

/**
 * @description: 判断国际象棋棋盘中一个格子的颜色
 * @date 2022/12/8 10:15 AM
 **/
public class Problem1812DetermineColorOfAChessboardSquare {

    public boolean squareIsWhite0(String coordinates) {
        int x = coordinates.charAt(0) - 'a';
        int y = coordinates.charAt(1) - '0';
        return (x & 1) == 0 ? (y & 1) == 0 : (y & 1) == 1;
    }

    public boolean squareIsWhite(String coordinates) {
        return ((coordinates.charAt(0) - 'a' + coordinates.charAt(1) - '0') & 1) == 0;
    }
}
