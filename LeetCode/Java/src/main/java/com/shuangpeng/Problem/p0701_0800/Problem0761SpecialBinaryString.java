package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

public class Problem0761SpecialBinaryString {

    class Info {
        StringBuilder sb;
        int count;

        Info(StringBuilder sb, int count) {
            this.sb = sb;
            this.count = count;
        }
    }

    public String makeLargestSpecial0(String s) {
        return merge(s, 0, s.length() - 1).sb.toString();
    }

    private Info merge(String s, int start, int end) {
        if (start + 1 == end) {
            return new Info(new StringBuilder("10"), 1);
        }
        List<Info> list = new ArrayList<>();
        int count = 0;
        for (int l = start, r = start; r <= end; ++r) {
            if (s.charAt(r) == '1') {
                ++count;
            } else {
                --count;
            }
            if (count == 0) {
                if (l == start && r == end) {
                    list.add(merge(s, l + 1, r - 1));
                } else {
                    list.add(merge(s, l, r));
                }
                l = r + 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        if (size == 1) {
            Info info = list.get(0);
            sb.append('1').append(info.sb).append('0');
            return new Info(sb, info.count + 1);
        }
        Collections.sort(list, (a, b) -> {
            if (a.count != b.count) {
                return b.count - a.count;
            }
            int n = Math.min(a.sb.length(), b.sb.length());
            for (int i = 0; i < n; ++i) {
                if (a.sb.charAt(i) != b.sb.charAt(i)) {
                    return b.sb.charAt(i) - a.sb.charAt(i);
                }
            }
            return b.sb.length() - a.sb.length();
        });
        for (int i = 0; i < size; ++i) {
            sb.append(list.get(i).sb);
        }
        return new Info(sb, list.get(0).count);
    }

    public String makeLargestSpecial(String s) {
        int n = s.length();
        int[] location = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '1') {
                stack.push(i);
            } else {
                location[stack.pop()] = i;
            }
        }
        return arrange(0, s.length() - 1, location);
    }

    private String arrange(int start, int end, int[] location) {
        if (start + 1 == end) {
            return "10";
        }
        if (location[start] == end) {
            return '1' + arrange(start + 1, end - 1, location) + '0';
        }
        List<String> list = new ArrayList<>();
        for (int i = start; i < end; i = location[i] + 1) {
            list.add(arrange(i, location[i], location));
        }
        Collections.sort(list, Comparator.reverseOrder());
        StringBuilder ans = new StringBuilder();
        for (String s : list) {
            ans.append(s);
        }
        return ans.toString();
    }

//    public static void main(String[] args) {
//        Problem0761SpecialBinaryString a = new Problem0761SpecialBinaryString();
//        a.makeLargestSpecial("11011000");
//    }
}
