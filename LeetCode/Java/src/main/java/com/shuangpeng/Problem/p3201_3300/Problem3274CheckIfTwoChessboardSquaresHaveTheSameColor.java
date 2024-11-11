package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3274CheckIfTwoChessboardSquaresHaveTheSameColor（检查棋盘方格颜色是否相同）
 * @date 2024/11/11 3:15 PM
 */
public class Problem3274CheckIfTwoChessboardSquaresHaveTheSameColor {

    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        return ((coordinate1.charAt(0) - coordinate2.charAt(0)) & 1) == ((coordinate1.charAt(1) - coordinate2.charAt(1)) & 1);
    }
}
