package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR121FindTargetIn2DPlants（寻找目标值 - 二维数组）
 * @date 2024/7/12 10:53 AM
 */
public class LCR121FindTargetIn2DPlants {

    public boolean findTargetIn2DPlants0(int[][] plants, int target) {
        if (plants == null || plants.length == 0 || plants[0].length == 0) {
            return false;
        }
        int m = plants.length, n = plants[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (plants[i][j] < target) {
                j++;
            } else if (plants[i][j] > target) {
                i--;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean findTargetIn2DPlants(int[][] plants, int target) {
        if (plants == null || plants.length == 0 || plants[0].length == 0) {
            return false;
        }
        return find(plants, 0, 0, plants.length - 1, plants[0].length - 1, target);
    }

    private boolean find(int[][] plants, int x1, int y1, int x2, int y2, int target) {
        int m = plants.length, n = plants[0].length;
        if (x1 < 0 || x2 >= m || x1 > x2 || y1 < 0 || y2 >= n || y1 > y2) {
            return false;
        }
        int x = x1 + (x2 - x1 >> 1), y = y1 + (y2 - y1 >> 1);
        if (plants[x][y] < target) {
            return find(plants, x1, y + 1, x2, y2, target) || find(plants, x + 1, y1, x2, y, target);
        } else if (plants[x][y] > target) {
            return find(plants, x1, y1, x - 1, y2, target) || find(plants, x, y1, x2, y - 1, target);
        } else {
            return true;
        }
    }
}
