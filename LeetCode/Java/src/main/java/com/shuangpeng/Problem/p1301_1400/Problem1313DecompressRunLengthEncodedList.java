package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1313DecompressRunLengthEncodedList（解压缩编码列表）
 * @date 2024/2/5 12:13 AM
 */
public class Problem1313DecompressRunLengthEncodedList {

    public int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i += 2) {
            int f = nums[i], v = nums[i + 1];
            for (int j = 0; j < f; j++) {
                list.add(v);
            }
        }
        int n = list.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
