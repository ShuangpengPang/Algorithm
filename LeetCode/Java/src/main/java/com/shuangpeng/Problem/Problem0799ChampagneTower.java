package com.shuangpeng.Problem;

import java.util.LinkedList;
import java.util.Queue;

public class Problem0799ChampagneTower {

    public double champagneTower0(int poured, int query_row, int query_glass) {
        Queue<Double> queue = new LinkedList<>();
        queue.offer((double) poured);
        int row = 0;
        while (row <= query_row) {
            int cols = queue.size();
            double previousCup = 0;
            for (int i = 0; i < cols; ++i) {
                double cup = queue.poll();
                if (row == query_row && i == query_glass) {
                    return Math.min(1, cup);
                }
                double half = Math.max(0, (cup - 1) / 2);
                queue.offer(previousCup + half);
                previousCup = half;
            }
            queue.offer(previousCup);
            row++;
        }
        return 0;
    }

    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] array = new double[query_row + 1][query_row + 1];
        array[0][0] = poured;
        for (int r = 0; r < query_row; ++r) {
            for (int c = 0; c <= r; ++c) {
                double half = (array[r][c] - 1) / 2;
                if (half > 0) {
                    array[r + 1][c] += half;
                    array[r + 1][c + 1] += half;
                }
            }
        }
        return Math.min(1, array[query_row][query_glass]);
    }

//    public static void main(String[] args) {
//        Problem0799ChampagneTower a = new Problem0799ChampagneTower();
//        a.champagneTower(2, 1, 1);
//    }
}
