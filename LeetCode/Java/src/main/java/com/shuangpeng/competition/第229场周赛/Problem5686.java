package com.shuangpeng.competition.第229场周赛;

public class Problem5686 {

    public int[] minOperations(String boxes) {
        int length = boxes.length();
        int move = 0;
        int count = 0;
        for (int i = 0; i < length; i++) {
            if (boxes.charAt(i) == '1') {
                count++;
                move += i;
            }
        }
        int[] answer = new int[length];
        answer[0] = move;
        int left = 0;
        int right = count - (boxes.charAt(0) == '1' ? 1 : 0);
        for (int i = 1; i < length; i++) {
            int sum = answer[i - 1] - right;
            if (boxes.charAt(i - 1) == '1') {
                left++;
            }
            sum += left;
            if (boxes.charAt(i) == '1') {
                right--;
            }
            answer[i] = sum;
        }
        return answer;
    }

}
