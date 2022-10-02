package com.shuangpeng.Problem.p0701_0800;

/**
 * @Description: Problem0777SwapAdjacentInLRString（在LR字符串中交换相邻字符）
 * @Date 2022/10/2 11:05 PM
 * @Version 1.0
 */
public class Problem0777SwapAdjacentInLRString {

    public boolean canTransform(String start, String end) {
        int n = start.length();
        for (int i = 0, j = 0; i < n || j < n; i++, j++) {
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && end.charAt(j) == 'X') {
                j++;
            }
            if (i == n && j == n) {
                return true;
            }
            if (i < n && j == n || j < n && i == n) {
                return false;
            }
            char c1 = start.charAt(i), c2 = end.charAt(j);
            if (c1 != c2 || c1 == 'L' && i < j || c2 == 'R' && i > j) {
                return false;
            }
        }
        return true;
    }
}
