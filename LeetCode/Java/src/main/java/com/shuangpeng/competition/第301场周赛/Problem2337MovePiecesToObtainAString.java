package com.shuangpeng.competition.第301场周赛;

/**
 * @Description: Problem2337MovePiecesToObtainAString（移动片段得到字符串）
 * @Date 2022/7/20 5:36 PM
 * @Version 1.0
 */
public class Problem2337MovePiecesToObtainAString {

    public boolean canChange(String start, String target) {
        int n = start.length();
        int i = 0, j = 0;
        while (i < n || j < n) {
            while (i < n && start.charAt(i) == '_') {
                i++;
            }
            while (j < n && target.charAt(j) == '_') {
                j++;
            }
            if (i == n || j == n) {
                return i == j;
            }
            char c1 = start.charAt(i), c2 = target.charAt(j);
            if (c1 != c2) {
                return false;
            }
            if (c1 == 'L' && i < j || c1 == 'R' && i > j) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }
}
