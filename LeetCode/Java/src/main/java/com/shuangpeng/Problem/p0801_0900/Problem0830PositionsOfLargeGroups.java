package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0830PositionsOfLargeGroups（较大分组的位置）
 * @date 2023/12/17 5:28 PM
 */
public class Problem0830PositionsOfLargeGroups {

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = s.length();
        for (int i = 0, start = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (i - start >= 2) {
                    ans.add(Arrays.asList(start, i));
                }
                start = i + 1;
            }
        }
        return ans;
    }
}
