package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1300SumOfMutatedArrayClosestToTarget（转变数组后最接近目标值的数组和）
 * @date 2023/7/6 3:39 PM
 */
public class Problem1300SumOfMutatedArrayClosestToTarget {

    public int findBestValue0(int[] arr, int target) {
        int maxVal = 0, sum = 0;
        for (int num : arr) {
            maxVal = Math.max(maxVal, num);
            sum += num;
        }
        if (sum <= target) {
            return maxVal;
        }
        int left = 0, right = maxVal, closest = 0;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            int s = getSum(arr, mid);
            if (s < target) {
                closest = s;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return target - closest <= getSum(arr, left) - target ? left - 1 : left;
    }

    private int getSum(int[] arr, int value) {
        int sum = 0;
        for (int num : arr) {
            sum += Math.min(num, value);
        }
        return sum;
    }

    public int findBestValue1(int[] arr, int target) {
        int n = arr.length, maxValue = 0, sum = 0;
        for (int num : arr) {
            sum += num;
            maxValue = Math.max(maxValue, num);
        }
        if (sum <= target) {
            return maxValue;
        }
        Arrays.sort(arr);
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
        }
        int left = 0, right = maxValue, s1 = 0;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            int s = binarySearch(arr, preSum, mid);
            if (s < target) {
                left = mid + 1;
                s1 = s;
            } else {
                right = mid - 1;
            }
        }
        int s2 = binarySearch(arr, preSum, left);
        return target - s1 <= s2 - target ? left - 1 : left;
    }

    private int binarySearch(int[] arr, int[] preSum, int value) {
        int left = 0, n = arr.length, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return preSum[left] + (n - left) * value;
    }

    public int findBestValue2(int[] arr, int target) {
        Arrays.sort(arr);
        int sum = 0, n = arr.length;
        for (int num : arr) {
            sum += num;
        }
        if (sum <= target) {
            return arr[n - 1];
        }
        int i = n - 1;
        for (; i >= 0 && sum > target; i--) {
            int d = arr[i] - (i > 0 ? arr[i - 1] : 0);
            sum -= d * (n - i);
        }
        int p = i >= 0 ? arr[i] : 0;
        int add = (target - sum) / (n - i - 1), ans = p + add;
        sum += add * (n - i - 1);
        return target - sum <= sum + (n - i - 1) - target ? ans : ans + 1;
    }

    public int findBestValue3(int[] arr, int target) {
        int sum = 0, n = arr.length;
        for (int num : arr) {
            sum += num;
        }
        Arrays.sort(arr);
        int ans = arr[n - 1];
        for (int i = n - 1; i >= 0 && sum > target; i--) {
            int d = Math.min(i > 0 ? arr[i] - arr[i - 1] : arr[i], (sum - target) / (n - i) + 1);
            sum -= d * (n - i);
            if (sum <= target) {
                ans = target - sum <= sum + n - i - target ? arr[i] - d : arr[i] - d + 1;
            }
        }
        return ans;
    }

    public int findBestValue(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }
        int l = 0, r = arr[n - 1], ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2;
            int index = Arrays.binarySearch(arr, mid);
            if (index < 0) {
                index = -index - 1;
            }
            int cur = prefix[index] + (n - index) * mid;
            if (cur <= target) {
                ans = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        int chooseSmall = check(arr, ans);
        int chooseBig = check(arr, ans + 1);
        return Math.abs(chooseSmall - target) <= Math.abs(chooseBig - target) ? ans : ans + 1;
    }

    public int check(int[] arr, int x) {
        int ret = 0;
        for (int num : arr) {
            ret += Math.min(num, x);
        }
        return ret;
    }
}
