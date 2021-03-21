package com.shuangpeng.competition;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution a = new Solution();
        int[][] classes = {{1,2},{3,5},{2,2}};
//        a.maxAverageRatio(classes, 2);
    }

    public int nearestValidPoint(int x, int y, int[][] points) {
        int answer = -1;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            int[] coord = points[i];
            if (coord[0] == x || coord[1] == y) {
                int d = Math.abs(x - coord[0]) + Math.abs(y - coord[1]);
                if (d < distance) {
                    distance = d;
                    answer = i;
                }
            }
        }
        return answer;
    }

    public boolean checkPowersOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 1) {
            if (n % 3 == 0) {
                n /= 3;
            } else if ((n - 1) % 3 == 0) {
                n = (n - 1) / 3;
            } else {
                return false;
            }
        }
        return true;
    }

    public int beautySum(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] array = new int[26];
        int n = s.length();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            array = new int[26];
            int min = 1, max = 1;
            int mChar = s.charAt(i) - 'a';
            array[mChar] = 1;
            for (int j = i + 1; j < n; j++) {
                int c = s.charAt(j) - 'a';
                array[c]++;
                max = Math.max(max, array[c]);
                if (c == mChar) {
                    min++;
                    for (int k = 0; k < array.length; k++) {
                        if (array[k] > 0 && array[k] < min) {
                            mChar = k;
                            min = array[k];
                        }
                    }
                } else if (array[c] < min) {
                    mChar = c;
                    min = array[c];
                }
                answer += (max - min);
            }
        }
        return answer;
    }

    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int length = queries.length;
        int[] answer = new int[length];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        List<Integer> list = new ArrayList<>(n * (n - 1) / 2);
        for (int u = 0; u < n; u++) {
            int e = graph.get(u).size();
            for (int v = u + 1; v < n; v++) {
                int b = e;
                List<Integer> l = graph.get(v);
                for (int a : l) {
                    if (a != u) {
                        b++;
                    }
                }
                list.add(b);
            }
        }
        Collections.sort(list);
        int size = list.size();
        for (int i = 0; i < queries.length; i++) {
            answer[i] = size - binarySearch(list, queries[i]);
        }
        return answer;
    }

    private int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int num = list.get(mid);
            if (num <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }




    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int answer = nums[k];
        int i = k, j = k, minLeft = nums[k], minRight = nums[k];
        while (i > 0 || j < n - 1) {
            answer = Math.max(answer, Math.min(minLeft, minRight) * (j - i + 1));
            if (i == 0) {
                j++;
                minRight = Math.min(minRight, nums[j]);
                continue;
            }
            if (j == n - 1) {
                i--;
                minLeft = Math.min(minLeft, nums[i]);
                continue;
            }
            if (minLeft > minRight) {
                i--;
                minLeft = Math.min(minLeft, nums[i]);
            } else if (minLeft < minRight) {
                j++;
                minRight = Math.min(minRight, nums[j]);
            } else {
                while (i >= 0) {
                    if (i > 0 && nums[i - 1] >= minLeft) {
                        i--;
                    } else {
                        break;
                    }
                }
                while (j < n) {
                    if (j < n - 1 && nums[j + 1] >= minRight) {
                        j++;
                    } else {
                        break;
                    }
                }
                answer = Math.max(answer, minLeft * (j - i + 1));
                if (i == 0 && j == n - 1) {
                    break;
                }
                if (i == 0) {
                    j++;
                    minRight = nums[j];
                } else if (j == n - 1) {
                    i--;
                    minLeft = nums[i];
                } else if (nums[i - 1] >= nums[j + 1]) {
                    i--;
                    minLeft = nums[i];
                } else {
                    j++;
                    minRight = nums[j];
                }
            }
        }
        return answer;
    }





    public int secondHighest(String s) {
        if (s == null || s.length() <= 1) {
            return -1;
        }
        int max = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d >= 0 && d <= 9) {
                if (d > max) {
                    secondMax = max;
                    max = d;
                } else if (d < max && d > secondMax) {
                    secondMax = d;
                }
            }
        }
        return secondMax == Integer.MIN_VALUE ? -1 : secondMax;
    }






    class AuthenticationManager {

        int timeToLive = 0;
        Map<String, Integer> map;

        public AuthenticationManager(int timeToLive) {
            this.timeToLive = timeToLive;
            map = new HashMap<>();
        }

        public void generate(String tokenId, int currentTime) {
            map.put(tokenId, currentTime);
        }

        public void renew(String tokenId, int currentTime) {
            int time = map.getOrDefault(tokenId, -2);
            if (time == -2) {
                return;
            }
            if (time + timeToLive <= currentTime) {
                map.remove(tokenId);
                return;
            }
            map.put(tokenId, currentTime);
        }

        public int countUnexpiredTokens(int currentTime) {
            Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
            int count = 0;
            for (Map.Entry<String, Integer> entry : entrySet) {
                int value = entry.getValue();
                if (value + timeToLive <= currentTime) {
                    entry.setValue(-1);
                } else {
                    count++;
                }
            }
            return count;
        }
    }







    public int getMaximumConsecutive(int[] coins) {
        if (coins == null || coins.length == 0) {
            return 0;
        }
        Arrays.sort(coins);
        int i = 1;
        int j = 0;
        int sum = 0;
        while (j < coins.length) {
            if (i > sum) {
                int num = coins[j];
                if (num - sum > i) {
                    break;
                }
                j++;
                sum += num;
            }
            i++;
        }
        return i;
    }










    public int maxAscendingSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int right = 1, answer = nums[0], sum = nums[0];
        while (right < nums.length) {
            if (nums[right] > nums[right - 1]) {
                sum += nums[right];
            } else {
                sum = nums[right];
            }
            answer = Math.max(answer, sum);
            right++;
        }
        return answer;
    }


    public int getNumberOfBacklogOrders(int[][] orders) {
        if (orders == null || orders.length == 0) {
            return 0;
        }
        // sell 最小
        PriorityQueue<int[]> sellQueue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // bug 最大
        PriorityQueue<int[]> buyQueue = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int[] order : orders) {
            if (order[2] == 0) {
                while (!sellQueue.isEmpty() && sellQueue.peek()[0] <= order[0] && order[1] > 0) {
                    int count1 = order[1];
                    int count2 = sellQueue.peek()[1];
                    order[1] -= count2;
                    if (count1 >= count2) {
                        sellQueue.poll();
                    } else {
                        sellQueue.peek()[1] = count2 - count1;
                    }
                }
                if (order[1] > 0) {
                    buyQueue.offer(order);
                }
            } else {
                while (!buyQueue.isEmpty() && buyQueue.peek()[0] >= order[0] && order[1] > 0) {
                    int count1 = order[1];
                    int count2 = buyQueue.peek()[1];
                    order[1] -= count2;
                    if (count1 >= count2) {
                        buyQueue.poll();
                    } else {
                        buyQueue.peek()[1] = count2 - count1;
                    }
                }
                if (order[1] > 0) {
                    sellQueue.offer(order);
                }
            }
        }
        int answer = 0;
        int mod = 1000000007;
        while (!sellQueue.isEmpty()) {
            answer += sellQueue.poll()[1];
            answer %= mod;
        }
        while (!buyQueue.isEmpty()) {
            answer += buyQueue.poll()[1];
            answer %= mod;
        }
        return answer;
    }




    public int maxValue(int n, int index, int maxSum) {
        if (n <= 0) {
            return 0;
        }
        int left = index;
        int right = n - 1 - index;
        int min = Math.min(left, right);
//        int count = n - 2 * min - 1;
        // (x - min)
        // (x - min + x - 1) * min + x + (x - min - 1 + x - min - (n - 2 * min - 1)) * (
//        return (2 * maxSum + count * count + count + 2 * min * count + 2 * min + 2 * min * min)
//                / 2 - 2 * min - count;
        return Math.floor((2 * maxSum + left + left * left + right + right * right) / (2 * left + 2 * right + 2));
    }

}
