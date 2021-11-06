package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

public class Problem0739DailyTemperatures {

    public int[] dailyTemperatures0(int[] T) {
        if (T == null) {
            return null;
        }
        int length = T.length;
        int[] answer = new int[length];
        Stack<Integer> stack = new Stack<>();
        for (int i = length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                answer[i] = 0;
            } else {
                answer[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        return answer;
    }

    public int[] dailyTemperatures1(int[] T) {
        if (T == null) {
            return null;
        }
        int length = T.length;
        int[] next = new int[71];
        Arrays.fill(next, Integer.MAX_VALUE);
        for (int i = length - 1; i >= 0; i--) {
            int warmerIndex = Integer.MAX_VALUE;
            for (int j = T[i] + 1 - 30; j <= 70; j++) {
                if (next[j] != Integer.MAX_VALUE) {
                    warmerIndex = Math.min(warmerIndex, next[j]);
                }
            }
            next[T[i] - 30] = i;
            T[i] = warmerIndex == Integer.MAX_VALUE ? 0 : warmerIndex - i;
        }
        return T;
    }

    public int[] dailyTemperatures2(int[] T) {
        if (T == null) {
            return null;
        }
        int length = T.length;
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int previous = stack.pop();
                answer[previous] = i - previous;
            }
            stack.push(i);
        }
        return answer;
    }

    public int[] dailyTemperatures3(int[] T) {
        if (T == null) {
            return null;
        }
        int length = T.length;
        List<Integer> stack = new ArrayList<>(length);
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && T[stack.get(stack.size() - 1)] < T[i]) {
                int previous = stack.get(stack.size() - 1);
                answer[previous] = i - previous;
                stack.remove(stack.size() - 1);
            }
            stack.add(i);
        }
        return answer;
    }

    public int[] dailyTemperatures4(int[] T) {
        if (T == null) {
            return null;
        }
        int length = T.length;
        int[] answer = new int[length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int previous = stack.poll();
                answer[previous] = i - previous;
            }
            stack.push(i);
        }
        return answer;
    }

    public int[] dailyTemperatures5(int[] T) {
        if (T == null || T.length == 0) return null;
        int[] result = new int[T.length];

        for (int i = T.length - 2; i >= 0; i--) {
            int j = i + 1;
            while (true) {
                if (T[i] < T[j]) {
                    result[i] = j - i;
                    break;
                }  else {
                    if (result[j] == 0) break;
                    j = j + result[j];
                }
            }
        }
        return result;
    }

    public int[] dailyTemperatures6(int[] T) {
        if (T == null) {
            return null;
        }
        int length = T.length;
        int[] answer = new int[length];
        for (int i = length - 2; i >= 0; i--) {
            int j = i + 1;
            while (j < length) {
                if (T[j] > T[i]) {
                    answer[i] = j - i;
                    break;
                } else if (answer[j] == 0) {
                    break;
                } else {
                    j += answer[j];
                }
            }
        }
        return answer;
    }

    public int[] dailyTemperatures(int[] T) {
        if (T == null) {
            return null;
        }
        int length = T.length;
        int[] stack = new int[length];
        int[] answer = new int[length];
        int count = 0;
        for (int i = 0; i < length; i++) {
            while (count > 0 && T[stack[count - 1]] < T[i]) {
                int previous = stack[count - 1];
                answer[previous] = i - previous;
                count--;
            }
            stack[count++] = i;
        }
        return answer;
    }
}
