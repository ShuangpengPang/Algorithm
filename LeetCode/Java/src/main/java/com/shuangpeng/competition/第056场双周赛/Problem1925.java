package com.shuangpeng.competition.第056场双周赛;

public class Problem1925 {

    public int countTriples0(int n) {
        int answer = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                int t = i * i - j * j;
                for (int k = 1; k < i; k++) {
                    int s = k * k;
                    if (s == t) {
                        answer++;
                        break;
                    } else if (s > t) {
                        break;
                    }
                }
            }
        }
        return answer;
    }

    public int countTriples(int n) {
        int answer = 0;
        for (int c = 2; c <= n; c++) {
            int sum = c * c;
            int max = (int) Math.pow(sum / 2, 0.5) + 1;
            for (int a = 1; a <= max; a++) {
                int b = (int) Math.pow(sum - a * a, 0.5);
                if (a * a + b * b == sum) {
                    if (a < b) {
                       answer += 2;
                    } else if (a == b) {
                        answer += 1;
                    } else {
                        break;
                    }
                }
            }
        }
        return answer;
    }
}
