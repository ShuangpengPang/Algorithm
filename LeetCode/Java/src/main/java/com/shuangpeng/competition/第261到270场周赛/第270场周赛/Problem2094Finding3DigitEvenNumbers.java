package com.shuangpeng.competition.第261到270场周赛.第270场周赛;

import java.util.*;

public class Problem2094Finding3DigitEvenNumbers {

    // 比赛时写法
    public int[] findEvenNumbers0(int[] digits) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(digits);
        int n = digits.length;
        for (int i = 0; i < n; ++i) {
            if (digits[i] == 0) {
                continue;
            }
            int num = digits[i];
            for (int j = 0; j < n; ++j) {
                if (j == i) {
                    continue;
                }
                num = num * 10 + digits[j];
                for (int k = 0; k < n; ++k) {
                    if (k == i || k == j) {
                        continue;
                    }
                    if ((digits[k] & 1) == 0) {
                        set.add(num * 10 + digits[k]);
                    }
                }
                num = digits[i];
            }
        }
        int size = set.size();
        int[] ans = new int[size];
        int idx = 0;
        for (int num : set) {
            ans[idx++] = num;
        }
        Arrays.sort(ans);
        return ans;
    }

    public int[] findEvenNumbers1(int[] digits) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(digits);
        int n = digits.length;
        for (int i = 0; i < n; ++i) {
            if ((digits[i] & 1) == 0 && (i == 0 || (digits[i] != digits[i - 1]))) {
                int num = digits[i];
                for (int j = 0; j < n; ++j) {
                    if (i != j && (j == 0 || j == i + 1 || digits[j] != digits[j - 1])) {
                        num += digits[j] * 10;
                        for (int k = 0; k < n; ++k) {
                            if (k != i && k != j && (k == 0 || k == i + 1 || k == j + 1 || digits[k] != digits[k - 1])) {
                                if (digits[k] != 0) {
                                    list.add(num + digits[k] * 100);
                                }
                            }
                        }
                        num = digits[i];
                    }
                }
            }
        }
        Collections.sort(list);
        int size = list.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; ++i) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public int[] findEvenNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int num : digits) {
            ++freq[num];
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 100; i < 1000; i += 2) {
            int a = i / 100, b = i / 10 % 10, c = i % 10;
            --freq[a];
            --freq[b];
            --freq[c];
            if (freq[a] >= 0 && freq[b] >= 0 && freq[c] >= 0) {
                list.add(i);
            }
            ++freq[a];
            ++freq[b];
            ++freq[c];
        }
        int size = list.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; ++i) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
