package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0233NumberOfDigitOne {

    public int countDigitOne0(int n) {
        if (n == 0) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n % 10);
            n /= 10;
        }
        int size = list.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            count += getCount(list, i);
        }
        return count;
    }

    private int getCount(List<Integer> list, int i) {
        int count = 0;
        for (int j = list.size() - 1; j >= -1; j--) {
            if (j == -1) {
                count++;
                break;
            }
            if (i < j) {
                count += list.get(j) * (int) Math.pow(10, j - 1);
                continue;
            }
            if (list.get(i) == 0) {
                break;
            }
            if (i == j) {
                continue;
            }
            if (list.get(i) == 1) {
                count += list.get(j) * (int) Math.pow(10, j);
            } else {
                count += (int) Math.pow(10, j + 1);
                break;
            }
        }
        return count;
    }

    public int countDigitOne1(int n) {
        int countr = 0;
        for (long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            countr += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0L), i);
        }
        return countr;
    }

    public int countDigitOne(int n) {
        int count = 0;
        for (int i = 1; i <= n; i *= 10) {
            int divider = i * 10;
            count += n / divider * i + Math.max(0, Math.min(i, n % divider - i + 1));
        }
        return count;
    }

//    public static void main(String[] args) {
//        Problem0233NumberOfDigitOne a = new Problem0233NumberOfDigitOne();
//        a.countDigitOne(13);
//    }
}
