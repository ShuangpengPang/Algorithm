package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2570MergeTwo2DArraysBySummingValues（合并两个二维数组-求和法）
 * @date 2024/4/4 6:31 PM
 */
public class Problem2570MergeTwo2DArraysBySummingValues {

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0, j = 0, n1 = nums1.length, n2 = nums2.length; i < n1 || j < n2;) {
            if (i < n1 && j < n2 && nums1[i][0] == nums2[j][0]) {
                list.add(new int[]{nums1[i][0], nums1[i][1] + nums2[j][1]});
                i++;
                j++;
            } else if (j == n2 || i < n1 && nums1[i][0] < nums2[j][0]) {
                list.add(nums1[i++]);
            } else {
                list.add(nums2[j++]);
            }
        }
        return list.toArray(new int[0][0]);
    }
}
