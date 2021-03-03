package com.shuangpeng.competition;

import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution a = new Solution();
        int[] b = a.a(8);
        int i = 1;
    }

    public int[] a(int num) {
        if (num < 0) {
            return new int[0];
        }
        int[] answer = new int[num + 1];
        int j = 0;
        int k = 0;
        for (int i = 1; i <= num; i++) {
            if (i == (1 << j)) {
                answer[i] = 1;
                j++;
                k = 0;
            } else {
                answer[i] = 1 + answer[++k];
            }
        }
        return answer;
    }

}
