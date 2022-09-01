package com.shuangpeng.competition.第251到260场周赛.第260场周赛;

import java.util.*;

public class Problem2019 {

    public int scoreOfStudents0(String s, int[] answers) {
        int n = s.length();
        List<Integer> stack = new ArrayList<>();
        stack.add(s.charAt(0) - '0');
        for (int i = 1; i < n; i += 2) {
            char c = s.charAt(i);
            int num = s.charAt(i + 1) - '0';
            if (c == '+') {
                stack.add(num);
            } else {
                int value = stack.remove(stack.size() - 1) * num;
                stack.add(value);
            }
        }
        int result = stack.remove(stack.size() - 1);
        while (!stack.isEmpty()) {
            result += stack.remove(stack.size() - 1);
        }
        Set<Integer> set = answerSet(s, 0, n - 1, new HashMap<>());
        int ans = 0;
        for (int num : answers) {
            if (num == result) {
                ans += 5;
            } else if (set.contains(num)) {
                ans += 2;
            }
        }
        return ans;
    }

    private Set<Integer> answerSet(String str, int s, int e, Map<Integer, Set<Integer>> memo) {
        if (s == e) {
            return new HashSet<Integer>() {{
                add(str.charAt(s) - '0');
            }};
        }
        int key = s * 32 + e;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        Set<Integer> set = new HashSet<>();
        for (int i = s; i < e; i += 2) {
            Set<Integer> left = answerSet(str, s, i, memo);
            Set<Integer> right = answerSet(str, i + 2, e, memo);
            if (str.charAt(i + 1) == '+') {
                for (int num1 : left) {
                    for (int num2 : right) {
                        if (num1 + num2 <= 1000) {
                            set.add(num1 + num2);
                        }
                    }
                }
            } else {
                for (int num1 : left) {
                    for (int num2 : right) {
                        if (num1 * num2 <= 1000) {
                            set.add(num1 * num2);
                        }
                    }
                }
            }
        }
        memo.put(key, set);
        return set;
    }

    public int scoreOfStudents(String s, int[] answers) {
        int n = s.length();
        int[] nums = new int[(n + 1) >> 1];
        char[] ops = new char[n >> 1];
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '+' || c == '*') {
                ops[i >> 1] = c;
            } else {
                nums[i >> 1] = c - '0';
            }
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerLast(nums[0]);
        for (int i = 1; i < nums.length; ++i) {
            if (ops[i - 1] == '+') {
                stack.offerLast(nums[i]);
            } else {
                int value = stack.pollLast() * nums[i];
                stack.offerLast(value);
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pollLast();
        }
        int N = (n + 1) >> 1;
        Set<Integer>[][] dp = new Set[N][N];
        for (int i = 0; i < N; ++i) {
            dp[i][i] = new HashSet<>();
            dp[i][i].add(nums[i]);
        }
        for (int i = N - 2; i >= 0; --i) {
            for (int j = i + 1; j < N; ++j) {
                dp[i][j] = new HashSet<>();
                for (int k = i; k < j; ++k) {
                    for (int num1 : dp[i][k]) {
                        for (int num2 : dp[k + 1][j]) {
                            if (ops[k] == '+' && num1 + num2 <= 1000) {
                                dp[i][j].add(num1 + num2);
                            } else if (ops[k] == '*' && num1 * num2 <= 1000) {
                                dp[i][j].add(num1 * num2);
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int num : answers) {
            if (num == result) {
                ans += 5;
            } else if (dp[0][N - 1].contains(num)) {
                ans += 2;
            }
        }
        return ans;
    }

    public int scoreOfStudents1(String s, int[] a) {
        // 计算正确答案
        int correct = 0;
        int n = s.length();
        Deque<String> stack = new LinkedList();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '*') {
                int t = Integer.parseInt(stack.pollLast());
                stack.offerLast(String.valueOf(t * (s.charAt(++i) - '0')));
            } else if (s.charAt(i) != '+') {
                stack.offerLast(String.valueOf(s.charAt(i)));
            }
        }
        while (!stack.isEmpty()) {
            correct += Integer.parseInt(stack.pollLast());
        }
        // 区间DP
        int[] numbers = new int[(n + 1) / 2];
        boolean[] operations = new boolean[n / 2];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '+') {
                operations[i / 2] = true;
            } else if (c != '*') {
                numbers[i / 2] = c - '0';
            }
        }
        int len = (n + 1) / 2;
        Set<Integer>[][] dp = new HashSet[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = new HashSet();
            dp[i][i].add(numbers[i]);
        }
        for (int j = 1; j < len; j++) {
            for (int i = j - 1; i >= 0; i--) {
                dp[i][j] = new HashSet();
                for (int k = i; k < j; k++) {
                    if (operations[k]) {
                        for (int x : dp[i][k]) {
                            for (int y : dp[k + 1][j]) {
                                if (x + y <= 1000) dp[i][j].add(x + y);
                            }
                        }
                    } else {
                        for (int x : dp[i][k]) {
                            for (int y : dp[k + 1][j]) {
                                if (x * y <= 1000) dp[i][j].add(x * y);
                            }
                        }
                    }
                }
            }
        }
        // 统计学生
        int res = 0;
        for (int x : a) {
            if (x == correct) res += 5;
            else if (dp[0][len - 1].contains(x)) res += 2;
        }
        return res;
    }

//    public static void main(String[] args) {
//        Problem2019 a = new Problem2019();
//        a.scoreOfStudents("7+3*1*2", new int[]{20, 13, 42});
//    }
}
