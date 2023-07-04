package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1401CircleAndRectangleOverlapping（圆和矩形是否有重叠）
 * @date 2023/7/3 6:13 PM
 */
public class Problem1401CircleAndRectangleOverlapping {

    public boolean checkOverlap0(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        if (xCenter >= x1 && xCenter <= x2 && yCenter >= y1 && yCenter <= y2) {
            return true;
        }
        int s = radius * radius;
        if (check(xCenter, yCenter, x1, y1, s) || check(xCenter, yCenter, x2, y2, s)) {
            return true;
        }
        if (check(xCenter, yCenter, x1, y2, s) || check(xCenter, yCenter, x2, y1, s)) {
            return true;
        }
        if (yCenter >= y1 && yCenter <= y2 && (check(xCenter, yCenter, x1, yCenter, s) || check(xCenter, yCenter, x2, yCenter, s))) {
            return true;
        }
        if (xCenter >= x1 && xCenter <= x2 && (check(xCenter, yCenter, xCenter, y1, s) || check(xCenter, yCenter, xCenter, y2, s))) {
            return true;
        }
        return false;
    }

    private boolean check(int x1, int y1, int x2, int y2, int s) {
        int x = x1 - x2, y = y1 - y2;
        return x * x + y * y <= s;
    }

    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        int x = xCenter < x1 ? x1 - xCenter : (xCenter > x2 ? xCenter - x2 : 0);
        int y = yCenter < y1 ? y1 - yCenter : (yCenter > y2 ? yCenter - y2 : 0);
        return x * x + y * y <= radius * radius;
    }
}
