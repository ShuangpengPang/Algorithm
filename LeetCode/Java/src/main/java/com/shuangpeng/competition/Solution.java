package com.shuangpeng.competition;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution a = new Solution();
        int[][] classes = {{1,2},{3,5},{2,2}};
        a.maxAverageRatio(classes, 2);
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






}
