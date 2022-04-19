package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description: Problem0821ShortestDistanceToACharacter
 * @Date 2022/4/19 10:11 AM
 * @Version 1.0
 */
public class Problem0821ShortestDistanceToACharacter {

    public int[] shortestToChar0(String s, char c) {
        int n = s.length();
        int[] left = new int[n];
        int previousIndex = -n;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == c) {
                previousIndex = i;
                left[i] = 0;
            } else {
                left[i] = i - previousIndex;
            }
        }
        int nextIndex = 2 * n - 1;
        int[] right = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            if (s.charAt(i) == c) {
                nextIndex = i;
                right[i] = 0;
            } else {
                right[i] = nextIndex - i;
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = Math.min(left[i], right[i]);
        }
        return ans;
    }

    public int[] shortestToChar1(String s, char c) {
        int n = s.length();
        int[] left = new int[n];
        int[] right = new int[n];
        int previousIndex = -n, nextIndex = 2 * n - 1;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == c) {
                previousIndex = i;
                left[i] = 0;
            } else {
                left[i] = i - previousIndex;
            }
            int j = n - i - 1;
            if (s.charAt(j) == c) {
                nextIndex = j;
                right[j] = 0;
            } else {
                right[j] = nextIndex - j;
            }
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = Math.min(left[i], right[i]);
        }
        return ans;
    }

    public int[] shortestToChar2(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        for (int i = 0, idx = -n; i < n; ++i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = i - idx;
        }
        for (int i = n - 1, idx = 2 * n; i >= 0; --i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = Math.min(ans[i], idx - i);
        }
        return ans;
    }

    public int[] shortestToChar3(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == c) {
                ans[i] = 0;
                queue.offer(i);
            }
        }
        int[] dirs = {-1, 1};
        while (!queue.isEmpty()) {
            int i = queue.poll();
            for (int dir : dirs) {
                int j = i + dir;
                if (j >= 0 && j < n && ans[j] == -1) {
                    ans[j] = ans[i] + 1;
                    queue.offer(j);
                }
            }
        }
        return ans;
    }

    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int first = s.indexOf(c);
        int last = s.lastIndexOf(c);
        int previous = -1, next = first;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i == next) {
                previous = next;
                if (next != last) {
                    next = s.indexOf(c, i + 1);
                }
            }
            if (i <= first) {
                ans[i] = first - i;
            } else if (i <= last) {
                ans[i] = Math.min(i - previous, next - i);
            } else {
                ans[i] = i - last;
            }
        }
        return ans;
    }
}
