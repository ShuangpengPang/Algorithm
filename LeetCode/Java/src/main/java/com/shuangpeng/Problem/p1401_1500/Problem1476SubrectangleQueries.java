package com.shuangpeng.Problem.p1401_1500;


import java.util.ArrayList;
import java.util.List;

/**
 * @program: Algorithm
 * @description: Problem1476SubrectangleQueries（子矩形查询）
 * @author: ShuangPengPang
 * @create: 2025-05-23 18:07
 */
public class Problem1476SubrectangleQueries {
}

class SubrectangleQueries {

    List<int[]> updateList;
    int[][] rectangle;

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
        updateList = new ArrayList<>();
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        updateList.add(new int[]{row1, col1, row2, col2, newValue});
    }

    public int getValue(int row, int col) {
        for (int i = updateList.size() - 1; i >= 0; i--) {
            int[] array = updateList.get(i);
            if (row >= array[0] && row <= array[2] && col >= array[1] && col <= array[3]) {
                return array[4];
            }
        }
        return rectangle[row][col];
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries obj = new SubrectangleQueries(rectangle);
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj.getValue(row,col);
 */
