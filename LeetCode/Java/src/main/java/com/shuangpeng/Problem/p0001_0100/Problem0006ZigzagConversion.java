package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.List;

public class Problem0006ZigzagConversion {

    public String convert0(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int diff = (numRows - 1) << 1;
        for (int i = 0; i < numRows; ++i) {
            int pre = (diff - (i << 1)) % diff;
            for (int j = i; j < n; j += diff - pre) {
                sb.append(s.charAt(j));
                pre = (diff - pre) % diff;
            }
        }
        return sb.toString();
    }

    public String convert1(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.length();
        List<List<Character>> lists = new ArrayList <>(numRows);
        for (int i = 0; i < numRows; ++i) {
            lists.add(new ArrayList<>());
        }
        boolean add = true;
        for (int i = 0, j = 0; i < n; ++i) {
            lists.get(j).add(s.charAt(i));
            j = add ? ++j : --j;
            if (j < 0) {
                j = 1;
                add = true;
            } else if (j == numRows) {
                j = numRows - 2;
                add = false;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (List<Character> list : lists) {
            for (char c : list) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String convert2(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.length();
        int size = Math.min(n, numRows);
        List<StringBuilder> lists = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            lists.add(new StringBuilder());
        }
        boolean goDown = false;
        for (int i = 0, j = 0; i < n; ++i) {
            lists.get(j).append(s.charAt(i));
            if (j == 0 || j == numRows - 1) {
                goDown = !goDown;
            }
            j += goDown ? 1 : -1;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder builder : lists) {
            sb.append(builder);
        }
        return sb.toString();
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.length();
        int diff = (numRows - 1) << 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; ++i) {
            for (int j = 0; i + j < n; j += diff) {
                sb.append(s.charAt(i + j));
                if (i != 0 && i != numRows - 1 && j + diff - i < n) {
                    sb.append(s.charAt(j + diff - i));
                }
            }
        }
        return sb.toString();
    }
}
