package com.shuangpeng.Problem.p0801_0900;

public class Problem0845LongestMountainInArray {

    public int longestMountain0(int[] arr) {
        int n = arr.length;
        int start = 0;
        int maxLength = 0;
        boolean flag = false;
        for (int i = 1; i < n; ++i) {
            if (!flag) {
                if (arr[i] == arr[i - 1]) {
                    start = i;
                } else if (start == i - 1 && arr[i] < arr[i - 1]) {
                    start = i;
                } else if (arr[i] < arr[i - 1]) {
                    flag = true;
                }
            } else if (arr[i] == arr[i - 1]) {
                maxLength = Math.max(maxLength, i - start);
                start = i;
                flag = false;
            } else if (arr[i] > arr[i - 1]) {
                maxLength = Math.max(maxLength, i - start);
                start = i - 1;
                flag = false;
            }
        }
        if (flag) {
            maxLength = Math.max(maxLength, n - start);
        }
        return maxLength;
    }

    public int longestMountain1(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 1; i < n; ++i) {
            if (arr[i] > arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 0;
            }
            if (arr[n - i - 1] > arr[n - i]) {
                right[n - i - 1] = right[n - i] + 1;
            } else {
                right[n - i - 1] = 0;
            }
        }
        int maxLength = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (left[i] > 0 && right[i] > 0) {
                maxLength = Math.max(maxLength, left[i] + right[i] + 1);
            }
        }
        return maxLength;
    }

    public int longestMountain2(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int left = 0;
        while (left + 2 < n) {
            int right = left + 1;
            if (arr[left] < arr[left + 1]) {
                while (right + 1 < n && arr[right] < arr[right + 1]) {
                    ++right;
                }
                if (right < n - 1 && arr[right] > arr[right + 1]) {
                    while (right + 1 < n && arr[right] > arr[right + 1]) {
                        ++right;
                    }
                    ans = Math.max(ans, right - left + 1);
                } else {
                    ++right;
                }
            }
            left = right;
        }
        return ans;
    }

    public int longestMountain3(int[] arr) {
        int n = arr.length;
        int ans = 0;
        int left = 0;
        while (left + 2 < n) {
            if (arr[left] < arr[left + 1]) {
                int right = left;
                while (right < n - 1 && arr[right] < arr[right + 1]) {
                    right++;
                }
                if (right < n - 1 && arr[right] > arr[right + 1]) {
                    while (right < n - 1 && arr[right] > arr[right + 1]) {
                        right++;
                    }
                    ans = Math.max(ans, right - left + 1);
                }
                left = right;
            } else {
                left++;
            }
        }
        return ans;
    }

    // 参考 https://leetcode-cn.com/problems/longest-mountain-in-array/solution/tong-su-yi-dong-de-jie-fa-by-rudy0530/
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int i = 0;
        int ans = 0;
        while (i + 2 < n) {
            int increasing = 0, decreasing = 0;
            while (i < n - 1 && arr[i] < arr[i + 1]) {
                i++;
                increasing++;
            }
            while (i < n - 1 && arr[i] > arr[i + 1]) {
                i++;
                decreasing++;
            }
            if (increasing > 0 && decreasing > 0) {
                ans = Math.max(ans, increasing + decreasing + 1);
            }
            while (i < n - 1 && arr[i] == arr[i + 1]) {
                i++;
            }
        }
        return ans;
    }
}
