package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0832FlippingAnImage（翻转图像）
 * @date 2024/1/13 2:21 PM
 */
public class Problem0832FlippingAnImage {

    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0, k = n - 1; j <= k; j++, k--) {
                if (image[i][j] == image[i][k]) {
                    image[i][j] = image[i][k] = image[i][j] ^ 1;
                }
            }
        }
        return image;
    }
}
