package com.shuangpeng.competition;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution a = new Solution();
//        int[][] classes = {{1,2},{3,5},{2,2}};
//        a.maxAverageRatio(classes, 2);
        int i = a.numDifferentIntegers("a1b01c001");
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


//    public int maxValue(int n, int index, int maxSum) {
//        if (n <= 0) {
//            return 0;
//        }
//        int left = index;
//        int right = n - 1 - index;
//        int min = Math.min(left, right);
////        int count = n - 2 * min - 1;
//        // (x - min)
//        // (x - min + x - 1) * min + x + (x - min - 1 + x - min - (n - 2 * min - 1)) * (
////        return (2 * maxSum + count * count + count + 2 * min * count + 2 * min + 2 * min * min)
////                / 2 - 2 * min - count;
//        return Math.floor((2 * maxSum + left + left * left + right + right * right) / (2 * left + 2 * right + 2));
//    }


    public int numDifferentIntegers(String word) {
        if (word == null || word.length() == 0) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        int n = word.length();
        int i = 0;
        int j = 0;
        for (; i < n; i++) {
            char ch = word.charAt(i);
            boolean isDigit = Character.isDigit(ch);
            if (isDigit) {
                continue;
            }
            if (i > j) {
                while (j < i && word.charAt(j) == '0') {
                    j++;
                }
                if (j >= i) {
                    set.add("0");
                } else {
                    set.add(word.substring(j, i));
                }
            }
            j = i + 1;
        }
        if (i > j) {
            while (j < i && word.charAt(j) == '0') {
                j++;
            }
            if (j >= i) {
                set.add("0");
            } else {
                set.add(word.substring(j, i));
            }
        }
        return set.size();
    }

    public int reinitializePermutation(int n) {
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        int count = 0;
        do {
            int[] temp = new int[n];
            for (int i = 0; i < n; i++) {
                if ((i & 1) == 0) {
                    temp[i] = array[i / 2];
                } else {
                    temp[i] = array[n / 2 + (i - 1) / 2];
                }
            }
            array = temp;
            count++;
        } while (!isValid(array, n));
        return count;
    }

    private boolean isValid(int[] array, int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] != i) {
                return false;
            }
        }
        return true;
    }

    public String evaluate(String s, List<List<String>> knowledge) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Map<String, String> map = new HashMap<>(knowledge.size());
        for (List<String> list : knowledge) {
            map.put(list.get(0), list.get(1));
        }
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        int left = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                flag = true;
                left = i + 1;
                continue;
            }
            if (ch == ')') {
                String key = s.substring(left, i);
                String value = map.getOrDefault(key, "?");
                builder.append(value);
                flag = false;
                left = i + 1;
                continue;
            }
            if (flag) {
                continue;
            }
            builder.append(ch);
        }
        return builder.toString();
    }


//    public int maxNiceDivisors(int primeFactors) {
//        if (primeFactors <= 2) {
//            return primeFactors;
//        }
//
//    }


    public int countNicePairs(int[] nums) {
        int mod = 1000000007;
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long key = nums[i] - rev(nums[i]);
            long value = map.getOrDefault(key, 0L);
            map.put(key, value + 1);
        }
        long answer = 0;
        Set<Map.Entry<Long, Long>> entries = map.entrySet();
        for (Map.Entry<Long, Long> entry : entries) {
            long value = entry.getValue();
            answer += value * (value - 1) / 2;
            answer %= mod;
        }
        return (int) answer;
    }

    private long rev(int x) {
        int result = 0;
        while (x > 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }
        return result;
    }

    public int maxHappyGroups(int batchSize, int[] groups) {
        int[] remain = new int[batchSize];
        for (int i = 0; i < groups.length; i++) {
            remain[groups[i] % batchSize]++;
        }
        int answer = remain[0];
        for (int i = 1; i < batchSize; i++) {
            int j = batchSize - i;
            if (j <= i) {
                break;
            }
            answer += Math.min(remain[i], remain[j]);
        }
        return answer;
    }

    public String truncateSentence(String s, int k) {
        int count = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == ' ') {
                count++;
                if (count == k) {
                    return s.substring(0, i);
                }
            }
        }
        return s;
    }

    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] log : logs) {
            Set<Integer> set = map.getOrDefault(log[0], new HashSet<>());
            set.add(log[1]);
            map.put(log[0], set);
        }
        int[] answer = new int[k];
        Set<Map.Entry<Integer, Set<Integer>>> entries = map.entrySet();
        for (Map.Entry<Integer, Set<Integer>> entry : entries) {
            int value = entry.getValue().size();
            answer[value - 1]++;
        }
        return answer;
    }

    public int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] copy = new int[n];
        for (int i = 0; i < n; i++) {
            copy[i] = nums1[i];
        }
        Arrays.sort(copy);
        int[] array = new int[n];
        int maxMinus = 0;
        for (int i = 0; i < n; i++) {
            array[i] = Math.abs(nums1[i] - nums2[i]);
            if (array[i] > 0) {
                maxMinus = Math.max(maxMinus, array[i] - getMinus(copy, nums2[i]));
            }
        }
        int mod = 1000000007;
        int answer = -maxMinus;
        for (int diff : array) {
            answer += diff;
            answer %= mod;
        }
        return answer;
    }

    private int getMinus(int[] copy, int num) {
        int i = 0, j = copy.length - 1;
        while (i < j) {
            int mid = (i + j) >> 1;
            if (copy[mid] > num) {
                j = mid - 1;
            } else if (copy[mid] < num) {
                i = mid + 1;
            } else {
                return 0;
            }
        }
        int result = Math.abs(num - copy[i]);
        if (i > 0) {
            result = Math.min(result, Math.abs(num - copy[i - 1]));
        }
        if (i < copy.length - 1) {
            result = Math.min(result, Math.abs(num - copy[i + 1]));
        }
        return result;
    }

//    public int countDifferentSubsequenceGCDs(int[] nums) {
//
//    }

    public int arraySign(int[] nums) {
        int answer = 1;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                answer = -answer;
            }
        }
        return answer;
    }

    public int findTheWinner(int n, int k) {
        if (n == 1) {
            return 1;
        }
        return (findTheWinner(n - 1, k) + k - 1) % n + 1;
    }

//    public int minSideJumps(int[] obstacles) {
//
//    }

    public boolean checkIfPangram(String sentence) {
        boolean[] array = new boolean[26];
        int count = 0;
        int n = sentence.length();
        for (int i = 0; i < n; i++) {
            int index = sentence.charAt(i) - 'a';
            if (!array[index]) {
                array[index] = true;
                count++;
            }
        }
        return count == 26;
    }

    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int n = costs.length;
        for (int i = 0; i < n; i++) {
            if (coins >= costs[i]) {
                coins -= costs[i];
            } else {
                return i;
            }
        }
        return n;
    }

    public int sumBase(int n, int k) {
        int sum = 0;
        while (n > 0) {
            sum += n % k;
            n /= k;
        }
        return sum;
    }

    class Node {
        int depth;
        Map<Integer, Node> nodeMap;

        public Node(int depth) {
            this.nodeMap = new HashMap<>();
            this.depth = depth;
        }
    }

    public int longestCommonSubpath(int n, int[][] paths) {
        int len = paths.length;
        int index = 0;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            if (paths[i].length < minLength) {
                index = i;
                minLength = paths[i].length;
            }
        }
        Node root = new Node(0);
        int[] path = paths[index];
        int size = path.length;
        for (int i = 0; i < size; i++) {
            Node node = root;
            for (int j = i; j < size; j++) {
                Map<Integer, Node> map = node.nodeMap;
                int c = path[j];
                if (!map.containsKey(c)) {
                    map.put(c, new Node(node.depth + 1));
                }
                node = map.get(c);
            }
        }
        int answer = size;
        for (int i = 1; i < len; i++) {
            if (i == index) {
                continue;
            }
            int[] array = paths[i];
            Node tempRoot = new Node(0);
            int result = 0;
            for (int j = 0; j < array.length; j++) {
                Node node = root;
                Node tempNode = tempRoot;
                for (int k = j; k < array.length; k++) {
                    int c = array[k];
                    Map<Integer, Node> map = node.nodeMap;
                    if (!map.containsKey(c)) {
                        break;
                    }
                    Map<Integer, Node> tempMap = tempNode.nodeMap;
                    if (!tempMap.containsKey(c)) {
                        tempMap.put(c, new Node(node.depth + 1));
                    }
                    node = map.get(c);
                    tempNode = tempMap.get(c);
                    result = Math.max(result, node.depth);
                }
            }
            root = tempRoot;
            answer = Math.min(answer, result);
        }
        return answer;
    }

//    private int result(int base, int e) {
//        int mod = (int) 1e9 + 7;
//        long i = 1;
//
//    }

}
