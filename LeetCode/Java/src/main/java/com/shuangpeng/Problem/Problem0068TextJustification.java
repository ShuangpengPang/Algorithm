package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0068TextJustification {

    public List<String> fullJustify0(String[] words, int maxWidth) {
        int n = words.length;
        int left = 0, right = 0;
        int width = 0;
        List<String> ans = new ArrayList<>();
        while (right < n) {
            if (width + words[right].length() <= maxWidth) {
                width += words[right].length() + 1;
            } else {
                getLine(words, left, right, maxWidth, ans);
                width = words[right].length() + 1;
                left = right;
            }
            right++;
        }
        if (left < right) {
            getLine(words, left, right, maxWidth, ans);
        }
        return ans;
    }

    private void getLine(String[] words, int left, int right, int maxWidth, List<String> list) {
        if (words.length == right || right - left == 1) {
            StringBuilder sb = new StringBuilder(words[left]);
            int width = words[left].length();
            for (int i = left + 1; i < right; ++i) {
                sb.append(' ' + words[i]);
                width += words[i].length() + 1;
            }
            while (width < maxWidth) {
                sb.append(' ');
                width++;
            }
            list.add(sb.toString());
        } else {
            int length = 0;
            for (int i = left; i < right; ++i) {
                length += words[i].length();
            }
            int total = maxWidth - length;
            int space = total / (right - left - 1);
            int remain = total % (right - left - 1);
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < space; ++i) {
                s.append(' ');
            }
            StringBuilder sb = new StringBuilder(words[left]);
            int j = 0;
            for (int i = left + 1; i < right; ++i) {
                sb.append(s);
                if (j < remain) {
                    sb.append(' ');
                    j++;
                }
                sb.append(words[i]);
            }
            list.add(sb.toString());
        }
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        List<String> ans = new ArrayList<>();
        int right = 0;
        while (true) {
            int left = right;
            int sumLength = 0;
            while (right < n && sumLength + words[right].length() + right - left <= maxWidth) {
                sumLength += words[right++].length();
            }
            if (right == n || right - left == 1) {
                StringBuilder sb = join(words, left, right, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
            }
            if (right == n) {
                break;
            }
            if (right - left == 1) {
                continue;
            }
            int totalSpaces = maxWidth - sumLength;
            int avgSpaces = totalSpaces / (right - left - 1);
            int extraSpaces = totalSpaces % (right - left - 1);
            StringBuilder sb = join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1));
            String str = blank(avgSpaces);
            sb.append(str);
            sb.append(join(words, left + extraSpaces + 1, right, str));
            ans.add(sb.toString());
        }
        return ans;
    }

    private StringBuilder join(String[] words, int left, int right, String separate) {
        StringBuilder sb = new StringBuilder(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(separate).append(words[i]);
        }
        return sb;
    }

    private String blank(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        Problem0068TextJustification a = new Problem0068TextJustification();
//        String[] words = {"What","must","be","acknowledgment","shall","be"};
//        a.fullJustify(words, 16);
//    }
}
