package com.shuangpeng.Problem;

public class Problem1828QueriesOnNumberOfPointsInsideACircle {

    public int[] countPoints(int[][] points, int[][] queries) {
        int n = queries.length;
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int[] point : points) {
                int x = point[0] - queries[i][0];
                int y = point[1] - queries[i][1];
                if (x * x + y * y <= queries[i][2] * queries[i][2]) {
                    count++;
                }
            }
            answer[i] = count;
        }
        return answer;
    }
}
