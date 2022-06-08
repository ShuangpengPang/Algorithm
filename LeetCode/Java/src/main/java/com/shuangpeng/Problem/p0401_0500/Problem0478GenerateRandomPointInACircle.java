package com.shuangpeng.Problem.p0401_0500;

import java.util.Random;

/**
 * @Description: Problem0478GenerateRandomPointInACircle（在圆内随机生成点）
 * @Date 2022/6/8 4:34 PM
 * @Version 1.0
 */
public class Problem0478GenerateRandomPointInACircle {

    //            (x - x0) * (x - x0) + (y - y0) * (y - y0) = radius * radius

    // 错误做法
    class Solution {

        double xCenter, yCenter, radius, square;
        Random random;

        public Solution(double radius, double x_center, double y_center) {
            this.radius = radius;
            this.xCenter = x_center;
            this.yCenter = y_center;
            this.square = radius * radius;
            this.random = new Random();
        }

        public double[] randPoint() {
            while (true) {
                double x = this.xCenter - this.radius + this.random.nextDouble() * 2 * radius;
                double s = Math.sqrt(this.square - (x - this.xCenter) * (x - this.xCenter));
                double y1 = this.yCenter - s;
                double y2 = this.yCenter + s;
                double y = y1 + (y2 - y1) * this.random.nextDouble();
                if ((x - this.xCenter) * (x - this.xCenter) + (y - this.yCenter) * (y - this.yCenter) <= this.radius * this.radius) {
                    return new double[]{x, y};
                }
            }
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */
}

class Problem0478GenerateRandomPointInACircle0 {

    class Solution {

        double xCenter, yCenter, radius, square;
        Random random;

        public Solution(double radius, double x_center, double y_center) {
            this.radius = radius;
            this.xCenter = x_center;
            this.yCenter = y_center;
            this.square = radius * radius;
            this.random = new Random();
        }

        public double[] randPoint() {
            while (true) {
                double x = random.nextDouble() * (2 * this.radius) - this.radius;
                double y = random.nextDouble() * (2 * this.radius) - this.radius;
                if (x * x + y * y <= this.square) {
                    return new double[]{x + this.xCenter, y + this.yCenter};
                }
            }
        }
    }
}