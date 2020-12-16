package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0118PascalTriangle {

    public List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> answer = new ArrayList<>(numRows);
        for (int i = 1; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>(i);
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    list.add(1);
                } else {
                    list.add(answer.get(i - 2).get(j - 1) + answer.get(i - 2).get(j));
                }
            }
            answer.add(list);
        }
        return answer;
    }
}
