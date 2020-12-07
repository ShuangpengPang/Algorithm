package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0089GrayCode {

    public List<Integer> grayCode(int n) {
        if (n < 0) {
            return new ArrayList<>();
        }
        int length = (int) Math.pow(2, n);
        List<Integer> answer = new ArrayList<>(length);
        answer.add(0);
        int count = 0;
        while (count < n) {
            int size = answer.size();
            int base = 1 << count;
            for (int i = size - 1; i >= 0; i--) {
                answer.add(base + answer.get(i));
            }
            count++;
        }
        return answer;
    }
}
