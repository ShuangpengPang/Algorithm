package com.shuangpeng.lcr;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR037AsteroidCollision（行星碰撞）
 * @date 2024/6/20 12:16 AM
 */
public class LCR037AsteroidCollision {

    public int[] asteroidCollision0(int[] asteroids) {
        List<Integer> stack = new ArrayList<>();
        for (int a : asteroids) {
            if (a > 0) {
                stack.add(a);
            } else {
                while (!stack.isEmpty() && stack.get(stack.size() - 1) > 0 && stack.get(stack.size() - 1) < -a) {
                    stack.remove(stack.size() - 1);
                }
                if (!stack.isEmpty() && stack.get(stack.size() - 1) > 0) {
                    if (stack.get(stack.size() - 1) == -a) {
                        stack.remove(stack.size() - 1);
                    }
                } else {
                    stack.add(a);
                }
            }
        }
        return stack.stream().mapToInt(e -> e).toArray();
    }

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> s = new ArrayDeque<>();
        for (int a : asteroids) {
            boolean alive = true;
            while (alive && a < 0 && !s.isEmpty() && s.peekLast() > 0) {
                alive = s.peekLast() < -a;
                if (s.peekLast() <= -a) {
                    s.pollLast();
                }
            }
            if (alive) {
                s.offerLast(a);
            }
        }
        return s.stream().mapToInt(e -> e).toArray();
    }
}
