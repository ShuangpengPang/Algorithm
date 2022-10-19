package com.shuangpeng.Problem.p1601_1700;

/**
 * @Description: Problem1700NumberOfStudentsUnableToEatLunch（无法吃午餐的学生数量）
 * @Date 2022/10/19 10:45 AM
 * @Version 1.0
 */
public class Problem1700NumberOfStudentsUnableToEatLunch {

    public int countStudents(int[] students, int[] sandwiches) {
        int n = students.length;
        int[] types = new int[2];
        int i = 0;
        for (int j = 0; i < n && (j < n || types[sandwiches[i]] > 0); ) {
            if (j < n) {
                types[students[j++]]++;
            }
            if (types[sandwiches[i]] > 0) {
                types[sandwiches[i++]]--;
            }
        }
        return n - i;
    }
}
