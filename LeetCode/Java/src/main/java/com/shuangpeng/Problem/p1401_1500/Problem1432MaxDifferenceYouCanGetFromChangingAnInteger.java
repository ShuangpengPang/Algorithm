package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1432MaxDifferenceYouCanGetFromChangingAnInteger（改变一个整数能得到的最大差值）
 * @date 2023/8/24 6:43 PM
 */
public class Problem1432MaxDifferenceYouCanGetFromChangingAnInteger {

    public int maxDiff(int num) {
        String s = Integer.toString(num);
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - '0';
        }
        return getNum(arr, num, 9) - (arr[0] == 1 ? getNum(arr, num, 0) : replace(arr, arr[0], 1));
    }

    private int getNum(int[] arr, int value, int t) {
        if (t != 0 && arr[0] != t) {
            return replace(arr, arr[0], t);
        }
        int s = -1;
        for (int num : arr) {
            if (num != arr[0] && num != t) {
                s = num;
                break;
            }
        }
        return s == -1 ? value : replace(arr, s, t);
    }

    private int replace(int[] arr, int s, int t) {
        int ans = 0;
        for (int num : arr) {
            ans = ans * 10 + (num == s ? t : num);
        }
        return ans;
    }
}
