package com.shuangpeng;

public class Problem0394DecodeString {

//    public static void main(String[] args) {
//        Problem0394DecodeString a = new Problem0394DecodeString();
//        String s = "3[a]2[bc]";
//        a.decodeString(s);
//    }

    public String decodeString(String s) {
        return recurse(s, 0, s.length());
    }

    public String recurse(String s, int start, int end) {
        StringBuilder builder = new StringBuilder();
        int i = start;
        while (i < end) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                builder.append(ch);
                i++;
            } else {
                String str = "";
                while (ch >= '0' && ch <= '9') {
                    str += ch;
                    i++;
                    ch = s.charAt(i);
                }
                int repeat = Integer.parseInt(str);
                int count = 1;
                int ss = i + 1;
                while (count > 0) {
                    i++;
                    if (s.charAt(i) == '[') {
                        count++;
                    } else if (s.charAt(i) == ']') {
                        count--;
                    }
                }
                String subPart = recurse(s, ss, i);
                for (int c = 0; c < repeat; c++) {
                    builder.append(subPart);
                }
                i++;
            }
        }
        return builder.toString();
    }
}
