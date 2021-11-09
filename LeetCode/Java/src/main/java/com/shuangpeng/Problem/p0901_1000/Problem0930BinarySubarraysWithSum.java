package com.shuangpeng.Problem.p0901_1000;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Problem0930BinarySubarraysWithSum {

    public int numSubarraysWithSum0(int[] nums, int goal) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (sum[i] - sum[j] == goal) {
                    answer++;
                }
            }
        }
        return answer;
    }

    public int numSubarraysWithSum1(int[] nums, int goal) {
        int n = nums.length;
        Queue<Integer> queue = new LinkedList<>();
        int answer = 0;
        int previous = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                queue.offer(i);
            }
            int sum = queue.size();
            if (sum > goal) {
                previous = queue.poll();
                sum--;
            }
            if (sum == goal) {
                int start = queue.isEmpty() ? i : queue.peek();
                answer += start - previous;
            }
        }
        return answer;
    }

    public int numSubarraysWithSum2(int[] nums, int goal) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            answer += map.getOrDefault(sum - goal, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return answer;
    }

    public int numSubarraysWithSum3(int[] nums, int goal) {
        int n = nums.length;
        int left = 0, right = 0, previous = -1;
        int answer = 0;
        int sum = 0;
        while (right < n) {
            sum += nums[right];
            while (sum >= goal && left < right && (sum - nums[left]) >= goal) {
                if (sum > goal) {
                    previous = left;
                }
                sum -= nums[left];
                left++;
            }
            if (sum == goal) {
                answer += left - previous;
            }
            right++;
        }
        return answer;
    }

    public int numSubarraysWithSum4(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int ret = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            ret += left2 - left1;
            right++;
        }
        return ret;
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int answer = 0;
        while (right < n) {
            sum1 += nums[right];
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            answer += left2 - left1;
            right++;
        }
        return answer;
    }
}
