package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR121FindTargetIn2DPlants（寻找目标值 - 二维数组）
 * @date 2024/7/12 10:53 AM
 */
public class LCR121FindTargetIn2DPlants {

    public boolean findTargetIn2DPlants(int[][] plants, int target) {
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
}
