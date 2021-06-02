package com.shuangpeng.Problem;

public class Problem1744EatCandyOnFavoriteDay {

    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        long[] array = new long[candiesCount.length];
        long sum = 0;
        for (int i = 0; i < candiesCount.length; i++) {
            array[i] = sum;
            sum += candiesCount[i];
        }
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int type = queries[i][0];
            int day = queries[i][1];
            int cap = queries[i][2];
            answer[i] = (day + 1 <= array[type] + candiesCount[type]) && ((long) (day + 1) * cap > array[type]);
        }
        return answer;
    }
}
