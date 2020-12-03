package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0406QueueReconstructionByHeight {

    public int[][] reconstructQueue0(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        int[][] answer = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            answer[i][0] = -1;
        }
        for (int i = 0; i < people.length; i++) {
            int j = people[i][1] + 1;
            for (int k = 0; k < answer.length; k++) {
                if (answer[k][0] == -1) {
                    j--;
                    if (j == 0) {
                        answer[k] = people[i];
                        break;
                    }
                }
            }
        }
        return answer;
    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });
        int[][] answers = new int[people.length][];
        int count = 0;
        for (int i = 0; i < people.length; i++) {
            int k = count - 1;
            while (k >= people[i][1]) {
                answers[k + 1] = answers[k];
                k--;
            }
            answers[k + 1] = people[i];
            count++;
        }
        return answers;
    }
}
