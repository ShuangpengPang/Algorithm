package com.shuangpeng.competition.第251场周赛;

public class Problem1945 {

    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append((int) (c - 'a' + 1));
        }
        String string = sb.toString();
        int sum = 0;
        for (char c : string.toCharArray()) {
            sum += (c - '0');
        }
        for (int i = 1; i < k; i++) {
            int temp = sum;
            sum = 0;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
        }
        return sum;
    }
}
