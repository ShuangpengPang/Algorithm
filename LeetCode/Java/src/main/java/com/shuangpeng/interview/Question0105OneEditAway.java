package com.shuangpeng.interview;

/**
 * @Description: Question0105OneEditAway（一次编辑）
 * @Date 2022/5/13 10:11 AM
 * @Version 1.0
 */
public class Question0105OneEditAway {

    public boolean oneEditAway0(String first, String second) {
        int n1 = first.length(), n2 = second.length();
        if (Math.abs(n1 - n2) > 1) {
            return false;
        }
        boolean opt = false;
        for (int i = 0, j = 0; i < n1 || j < n2; ++i, ++j) {
            if (i == n1 || j == n2) {
                return !opt;
            }
            if (first.charAt(i) != second.charAt(j)) {
                if (opt) {
                    return false;
                }
                opt = true;
                if (n1 < n2) {
                    --i;
                } else if (n1 > n2) {
                    --j;
                }
            }
        }
        return true;
    }

    public boolean oneEditAway(String first, String second) {
        int m = first.length(), n = second.length();
        if (n - m == 1) {
            return oneInsert(first, second);
        } else if (m - n == 1) {
            return oneInsert(second, first);
        } else if (m == n) {
            boolean foundDifference = false;
            for (int i = 0; i < m; i++) {
                if (first.charAt(i) != second.charAt(i)) {
                    if (!foundDifference) {
                        foundDifference = true;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean oneInsert(String shorter, String longer) {
        int length1 = shorter.length(), length2 = longer.length();
        int index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            if (shorter.charAt(index1) == longer.charAt(index2)) {
                index1++;
            }
            index2++;
            if (index2 - index1 > 1) {
                return false;
            }
        }
        return true;
    }
}
