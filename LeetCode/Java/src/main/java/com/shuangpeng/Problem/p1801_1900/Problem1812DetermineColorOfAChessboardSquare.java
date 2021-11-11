package com.shuangpeng.Problem.p1801_1900;

public class Problem1812DetermineColorOfAChessboardSquare {

    public boolean squareIsWhite(String coordinates) {
        int x = coordinates.charAt(0) - 'a';
        int y = coordinates.charAt(1) - '0';
        return (x & 1) == 0 ? (y & 1) == 0 : (y & 1) == 1;
    }
}
