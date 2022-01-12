package com.shuangpeng.Problem.p0301_0400;

public class Problem0306AdditiveNumber {

    public boolean isAdditiveNumber0(String num) {
        int n = num.length();
        for (int i = 0; i < n - 2; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                if (dfs(num, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(String num, int i, int j) {
        int n = num.length();
        long data1 = toLong(num, 0, i);
        long data2 = toLong(num, i + 1, j);
        if (data1 == -1 || data2 == -1) {
            return false;
        }
        return check(num, j + 1, data1, data2);
    }

    private long toLong(String num, int i, int j) {
        if (j - i >= 17 || (num.charAt(i) == '0' && i < j)) {
            return -1;
        }
        long ans = 0;
        for (int k = i; k <= j; ++k) {
            ans = ans * 10 + num.charAt(k) - '0';
        }
        return ans;
    }

    private boolean check(String num, int start, long data1, long data2) {
        int n = num.length();
        if (n == start) {
            return true;
        }
        long target = data1 + data2;
        if (target == 0) {
            return num.charAt(start) == '0' && check(num, start + 1, data2, target);
        }
        if (target > 0 && num.charAt(start) == '0') {
            return false;
        }
        int i = start;
        long sum = 0;
        while (i < n && sum < target) {
            sum = sum * 10 + num.charAt(i) - '0';
            ++i;
        }
        return sum == target ? check(num, i, data2, sum) : false;
    }

    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i < n - 1; ++i) {
            if (num.charAt(0) == '0' && i > 1) {
                break;
            }
            for (int j = i; j < n - 1; ++j) {
                if (num.charAt(i) == '0' && j > i) {
                    break;
                }
                if (valid(num, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean valid(String s, int secondStart, int secondEnd) {
        int firstStart = 0, firstEnd = secondStart - 1;
        int n = s.length();
        while (secondEnd < n) {
            String sum = stringAdd(s, firstStart, firstEnd, secondStart, secondEnd);
            int length = sum.length();
            if (secondEnd + length >= n || !sum.equals(s.substring(secondEnd + 1, secondEnd + length + 1))) {
                return false;
            }
            if (secondEnd + length == n - 1) {
                return true;
            }
            firstStart = secondStart;
            firstEnd = secondEnd;
            secondStart = secondEnd + 1;
            secondEnd += length;
        }
        return false;
    }

    private String stringAdd(String s, int firstStart, int firstEnd, int secondStart, int secondEnd) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (firstEnd >= firstStart || secondEnd >= secondStart || carry > 0) {
            int sum = carry;
            if (firstEnd >= firstStart) {
                sum += s.charAt(firstEnd) - '0';
                --firstEnd;
            }
            if (secondEnd >= secondStart) {
                sum += s.charAt(secondEnd) - '0';
                --secondEnd;
            }
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }
}
