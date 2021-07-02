package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem0119PascalTriangleII {

    public List<Integer> getRow0(int rowIndex) {
        Integer[] array = {1};
        for (int i = 1; i <= rowIndex; i++) {
            Integer[] temp = new Integer[i + 1];
            temp[0] = 1;
            temp[i] = 1;
            for (int j = 1; j < i; j++) {
                temp[j] = array[j - 1] + array[j];
            }
            array = temp;
        }
        return Arrays.asList(array);
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> answer = new ArrayList<>(rowIndex + 1);
        int half = rowIndex >> 1;
        for (int i = 0; i <= rowIndex; i++) {
            if (i <= half) {
                answer.add(count(rowIndex, i));
            } else {
                answer.add(answer.get(rowIndex - i));
            }
        }
        return answer;
    }

    private int count(int a, int b) {
        if (b == 0) {
            return 1;
        }
        b = Math.min(b, a - b);
        long m = 1;
        for (int i = 1; i <= b; i++) {
            m *= (a - i + 1);
            m /= i;
        }
        return (int) m;
    }
}
