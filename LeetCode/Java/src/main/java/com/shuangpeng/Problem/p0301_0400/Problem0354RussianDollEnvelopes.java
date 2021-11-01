package com.shuangpeng.Problem.p0301_0400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem0354RussianDollEnvelopes {

//    public static void main(String[] args) {
//        Problem0354RussianDollEnvelopes a = new Problem0354RussianDollEnvelopes();
//        int[][] envelopes = {{30,50},{12,2},{3,4},{12,15}};
//        a.maxEnvelopes(envelopes);
//    }

    public int maxEnvelopes0(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] < b[0]) {
                return -1;
            } else if (a[0] > b[0]) {
                return 1;
            } else {
                return b[1] - a[1];
            }
        });
        int n = envelopes.length;
        int[] dp = new int[n];
        int answer = 1;
        for (int i = 0; i < n; i++) {
            int length = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    length = Math.max(length, dp[j] + 1);
                }
            }
            dp[i] = length;
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }

    public int maxEnvelopes1(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < envelopes.length; i++) {
            int h = envelopes[i][1];
            int left = 0, right = lists.size() - 1;
            while (left <= right) {
                int mid = (left + right) >> 1;
                List<Integer> list = lists.get(mid);
                int value = list.get(list.size() - 1);
                if (h > value) {
                    left = mid + 1;
                } else if (h <= value) {
                    right = mid - 1;
                }
            }
            if (left >= lists.size()) {
                lists.add(new ArrayList<>());
            }
            lists.get(left).add(h);
        }
        return lists.size();
    }

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        List<Integer> answer = new ArrayList<>();
        answer.add(envelopes[0][1]);
        for (int i = 1; i < envelopes.length; i++) {
            int num = envelopes[i][1];
            if (num > answer.get(answer.size() - 1)) {
                answer.add(num);
            } else {
                int left = 0, right = answer.size() - 1;
                while (left <= right) {
                    int mid = (left + right) >> 1;
                    int data = answer.get(mid);
                    if (num <= data) {
                        right = mid - 1;
                    } else if (num > data) {
                        left = mid + 1;
                    }
                }
                answer.set(left, num);
            }
        }
        return answer.size();
    }
}
