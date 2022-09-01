package com.shuangpeng.competition.双周赛.第068场双周赛;

public class Problem2117AbbreviatingTheProductOfARange {

    public String abbreviateProduct(int left, int right) {
        int[] ans = {1};
        int zeroes = 0;
        for (int i = left; i <= right; ++i) {
            Info info = multiply(ans, toArray(i));
            ans = info.arr;
            zeroes += info.zeroes;
        }
        StringBuilder sb = new StringBuilder();
        int n = ans.length;
        if (n <= 10) {
            for (int i = 0; i < n; ++i) {
                sb.append(ans[i]);
            }
        } else {
            for (int i = 0; i < 5; ++i) {
                sb.append(ans[i]);
            }
            sb.append("...");
            for (int i = n - 5; i < n; ++i) {
                sb.append(ans[i]);
            }
        }
        sb.append('e').append(zeroes);
        return sb.toString();
    }

    private int[] toArray(int num) {
        int bit = 0;
        int[] arr = new int[6];
        while (num > 0) {
            arr[bit++] = num % 10;
            num /= 10;
        }
        int[] ans = new int[bit];
        for (int i = bit - 1; i >= 0; --i) {
            ans[bit - i - 1] = arr[i];
        }
        return ans;
    }

    public Info multiply(int[] num1, int[] num2) {
        int n1 = num1.length, n2 = num2.length;
        int n = n1 + n2;
        int[] arr = new int[n];
        for (int i = n1 - 1; i >= 0; --i) {
            for (int j = n2 - 1; j >= 0; --j) {
                arr[i + j + 1] += num1[i] * num2[j];
                arr[i + j] += arr[i + j + 1] / 10;
                arr[i + j + 1] %= 10;
            }
        }
        if (arr[0] != 0 && arr[n - 1] != 0) {
            return new Info(arr, 0);
        }
        int r = n - 1;
        while (arr[r] == 0) {
            --r;
        }
        int l = arr[0] == 0 ? 1 : 0;
        int length = r - l + 1;
        int N = 30;
        int size = length <= N ? length : N;
        int[] ans = new int[size];
        if (length <= N) {
            for (int i = l; i <= r; ++i) {
                ans[i - l] = arr[i];
            }
        } else {
            for (int i = l; i < l + (N >> 1); ++i) {
                ans[i - l] = arr[i];
            }
            for (int i = r - (N >> 1) + 1; i <= r; ++i) {
                ans[i + N - r - 1] = arr[i];
            }
        }
        return new Info(ans, n - r - 1);
    }

    class Info {
        int[] arr;
        int zeroes;

        public Info(int[] arr, int zeroes) {
            this.arr = arr;
            this.zeroes = zeroes;
        }
    }
}
