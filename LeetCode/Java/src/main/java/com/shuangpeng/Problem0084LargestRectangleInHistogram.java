package com.shuangpeng;

public class Problem0084LargestRectangleInHistogram {

    // 单调栈优化
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] stack = new int[heights.length + 1];
        int count = 0;
        stack[count++] = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            while (stack[count - 1] != -1 && heights[stack[count - 1]] >= heights[i]) {
                max = Math.max(max, heights[stack[count - 1]] * (i - stack[count - 2] - 1));
                count--;
            }
            stack[count++] = i;
        }
        if (count > 1) {
            int lastIndex = stack[count - 1];
            for (int i = 1; i < count; i++) {
                max = Math.max(max, heights[stack[i]] * (lastIndex - stack[i - 1]));
            }
        }
        return max;
    }

    // 单调栈
    public int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] leftStack = new int[heights.length];
        int[] rightStack = new int[heights.length];
        int[] stack = new int[heights.length + 1];
        int count = 0;
        stack[count++] = -1;
        for (int i = 0; i < heights.length; i++) {
            while (stack[count - 1] != -1 && heights[stack[count - 1]] >= heights[i]) {
                count--;
            }
            leftStack[i] = stack[count - 1];
            stack[count++] = i;
        }
        count = 0;
        stack[count++] = heights.length;
        for (int i = heights.length - 1; i >= 0; i--) {
            while (stack[count - 1] != heights.length && heights[stack[count - 1]] >= heights[i]) {
                count--;
            }
            rightStack[i] = stack[count - 1];
            stack[count++] = i;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] * (rightStack[i] - leftStack[i] - 1));
        }
        return max;
    }

    // 暴力求解
    public int largestRectangleArea0(int[] heights) {
        // [2,1,5,6,2,3,1,1,1,1,1,1,1]
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // min * (end - start + 1)
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i]);
            int min = heights[i];
            for (int j = i - 1; j >= 0; j--) {
                min = Math.min(min, heights[j]);
                max = Math.max(max, min * (i - j + 1));
            }
        }
        return max;
    }
}
