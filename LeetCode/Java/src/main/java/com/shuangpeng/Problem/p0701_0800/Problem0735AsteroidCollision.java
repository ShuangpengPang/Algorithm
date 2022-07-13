package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @Description: Problem0735AsteroidCollision（行星碰撞）
 * @Date 2022/7/13 9:58 AM
 * @Version 1.0
 */
public class Problem0735AsteroidCollision {

    public int[] asteroidCollision0(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int a : asteroids) {
            if (a > 0) {
                stack.push(a);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < -a) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(a);
                } else if (stack.peek() == -a) {
                    stack.pop();
                }
            }
        }
        int n = stack.size();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    public int[] asteroidCollision1(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int aster : asteroids) {
            boolean alive = true;
            while (alive && aster < 0 && !stack.isEmpty() && stack.peek() > 0) {
                alive = stack.peek() < -aster;
                if (stack.peek() <= -aster) {
                    stack.pop();
                }
            }
            if (alive) {
                stack.push(aster);
            }
        }
        int n = stack.size();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }
        return ans;
    }

    public int[] asteroidCollision(int[] asteroids) {
        int n = asteroids.length;
        int top = -1;
        for (int i = 0; i < n; i++) {
            int aster = asteroids[i];
            if (top != -1 && aster < 0 && asteroids[top] > 0) {
                if (asteroids[top] + aster > 0) {
                    continue;
                }
                if (asteroids[top] + aster < 0) {
                    i--;
                }
                top--;
            } else {
                asteroids[++top] = aster;
            }
        }
        return Arrays.copyOfRange(asteroids, 0, top + 1);
    }
}
