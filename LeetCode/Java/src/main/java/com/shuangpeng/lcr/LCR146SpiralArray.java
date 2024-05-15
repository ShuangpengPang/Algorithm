package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR146SpiralArray（螺旋遍历二维数组）
 * @date 2024/5/15 2:25 PM
 */
public class LCR146SpiralArray {

    public int[] spiralArray(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return new int[0];
        }
        int m = array.length, n = array[0].length, N = m * n;
        int up = 0, down = m - 1, left = 0, right = n - 1;
        int[] ans = new int[N];
        int index = 0;
        while (up <= down && left <= right) {
            for (int i = left; i <= right; i++) {
                ans[index++] = array[up][i];
            }
            up++;
            for (int i = up; i <= down; i++) {
                ans[index++] = array[i][right];
            }
            right--;
            if (up <= down) {
                for (int i = right; i >= left; i--) {
                    ans[index++] = array[down][i];
                }
                down--;
            }
            if (left <= right) {
                for (int i = down; i >= up; i--) {
                    ans[index++] = array[i][left];
                }
                left++;
            }
        }
        return ans;
    }
}
