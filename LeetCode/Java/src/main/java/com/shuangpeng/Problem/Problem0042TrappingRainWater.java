package com.shuangpeng.Problem;

public class Problem0042TrappingRainWater {

//    public static void main(String[] args) {
//        Problem0042TrappingRainWater a = new Problem0042TrappingRainWater();
//        int[] array = {0,1,0,2,1,0,1,3,2,1,2,1};
//        a.trap(array);
//    }

    public int trap0(int[] height) {
        // 4,2,0,3,2,5   9
        //   6
        if (height.length <= 2) {
            return 0;
        }

        int preLeft = 0;
        int maxLeft = 0;
        int preRight = height.length - 1;
        int maxRight = height.length - 1;
        int resultLeft = 0;
        int resultRight = 0;
        while (maxLeft < maxRight) {
            // left
            int sumLeft = 0;
            for (int i = maxLeft + 1; i < height.length; i++) {
                if (height[i] >= height[preLeft]) {
                    maxLeft = i;
                    break;
                }
                sumLeft += height[i];
            }
            if (maxLeft - preLeft > 1) {
                resultLeft += height[preLeft] * (maxLeft - preLeft - 1) - sumLeft;
            }
            if (maxLeft > preLeft) {
                preLeft = maxLeft;
            }

            // right
            if (maxLeft >= maxRight) {
                break;
            }

            int sumRight = 0;
            for (int i = maxRight - 1; i >= 0; i--) {
                if (height[i] >= height[preRight]) {
                    maxRight = i;
                    break;
                }
                sumRight += height[i];
            }
            if (preRight - maxRight > 1) {
                resultRight += height[preRight] * (preRight - maxRight - 1) - sumRight;
            }
            if (maxRight < preRight) {
                preRight = maxRight;
            }
        }
        return resultLeft + resultRight;
    }

    // 暴力求解
    public int trap1(int[] height) {
//        int[] array = {0,1,0,2,1,0,1,3,2,1,2,1};
        if (height.length <= 2) {
            return 0;
        }
        int[] map = new int[height.length];
        int sum = 0;
        for (int i = 2; i < height.length; i++) {
            int max = height[0];
            int min = Math.min(max, height[i]);
            for (int j = 1; j < i; j++) {
                if (height[j] < min && map[j] < min) {
                    int diff = min - map[j];
                    if (map[j] == 0) {
                        diff = min - height[j];
                    }
                    sum += diff;
                    map[j] = min;
                }
                if (height[j] >= max) {
                    max = height[j];
                    min = Math.min(max, height[i]);
                }
            }
        }
        return sum;
    }

    // 动态编程
    public int trap2(int[] height) {
//        int[] array = {0,1,0,2,1,0,1,3,2,1,2,1};
        if (height.length <= 2) {
            return 0;
        }
        int maxLeft = height[0];
        int maxRight = height[0];
        int right = 0;
        int sum = 0;
        for (int i = 1; i < height.length - 1; i++) {
            if (i > right) {
                maxRight = height[i + 1];
                right = i + 1;
                for (int j = i + 2; j < height.length; j++) {
                    if (height[j] > maxRight) {
                        maxRight = height[j];
                        right = j;
                    }
                }
            }
            int h = Math.min(maxLeft, maxRight);
            if (height[i] < h) {
                sum += h - height[i];
            }
            maxLeft = Math.max(maxLeft, height[i]);
        }
        return sum;
    }

    // 栈
    public int trap3(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int[] stack = new int[height.length];
        int count = 0;
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            while (count > 0 && height[stack[count - 1]] < height[i]) {
                int top = stack[count - 1];
                count--;
                if (count > 0) {
                    int index = stack[count - 1];
                    int distance = i - index - 1;
                    sum += distance * (Math.min(height[index], height[i]) - height[top]);
                }
            }
            stack[count++] = i;
        }
        return sum;
    }

    // 双指针
    public int trap(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int sum = 0;
        while (left < right) {
            if (height[left] <= height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    sum += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    sum += rightMax - height[right];
                }
                right--;
            }
        }
        return sum;
    }
}
