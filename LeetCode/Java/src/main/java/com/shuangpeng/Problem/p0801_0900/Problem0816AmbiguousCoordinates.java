package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0816AmbiguousCoordinates（模糊坐标）
 * @date 2022/11/7 10:21 AM
 */
public class Problem0816AmbiguousCoordinates {

    public List<String> ambiguousCoordinates0(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n - 2; i++) {
            List<String> list1 = parse(s.substring(1, i + 1)), list2 = parse(s.substring(i + 1, n - 1));
            if (list1.isEmpty() || list2.isEmpty()) {
                continue;
            }
            for (String s1 : list1) {
                for (String s2 : list2) {
                    sb.setLength(0);
                    sb.append('(').append(s1).append(", ").append(s2).append(')');
                    ans.add(sb.toString());
                }
            }
        }
        return ans;
    }

    private List<String> parse(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        if (s.charAt(0) != '0' || n == 1 && s.charAt(0) == '0') {
            ans.add(s);
        }
        if (s.charAt(n - 1) == '0') {
            return ans;
        }
        for (int i = 0; i < n - 1; i++) {
            String s1 = s.substring(0, i + 1), s2 = s.substring(i + 1, n);
            if (s1.charAt(0) == '0' && s1.length() > 1) {
                break;
            }
            ans.add(s1 + '.' + s2);
        }
        return ans;
    }

    public List<String> ambiguousCoordinates(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < n - 1; i++) {
            List<String> list1 = getPos(s.substring(1, i));
            if (list1.isEmpty()) {
                continue;
            }
            List<String> list2 = getPos(s.substring(i, n - 1));
            if (list2.isEmpty()) {
                continue;
            }
            for (String s1 : list1) {
                for (String s2 : list2) {
                    sb.setLength(0);
                    sb.append('(').append(s1).append(", ").append(s2).append(')');
                    ans.add(sb.toString());
                }
            }
        }
        return ans;
    }

    private List<String> getPos(String s) {
        int n = s.length();
        List<String> ans = new ArrayList<>();
        if (s.charAt(0) != '0' || s.equals("0")) {
            ans.add(s);
        }
        if (s.charAt(n - 1) == '0') {
            return ans;
        }
        for (int i = 1; i < n; i++) {
            String s1 = s.substring(0, i);
            if (s1.charAt(0) == '0' && s1.length() > 1) {
                break;
            }
            ans.add(s1 + '.' + s.substring(i));
        }
        return ans;
    }
}