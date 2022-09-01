package com.shuangpeng.competition.第251到260场周赛.第251场周赛;

public class Problem1946 {

    public String maximumNumber0(String num, int[] change) {
        char[] chars = num.toCharArray();
        int index = -1;
        for (int i = 0; i < chars.length; i++) {
            int a = chars[i] - '0';
            if (a < change[a]) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            while (index < chars.length) {
                int i = chars[index] - '0';
                if (i < change[i]) {
                    chars[index] = (char) (change[i] + '0');
                } else if (i > change[i]) {
                    break;
                }
                index++;
            }
        }
        return new String(chars);
    }

    public String maximumNumber(String num, int[] change) {
        char[] chars = num.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int d = chars[i] - '0';
            if (d < change[d]) {
                while (i < chars.length && d <= change[d]) {
                    chars[i] = (char) (change[d] + '0');
                    i++;
                    if (i < chars.length) {
                        d = chars[i] - '0';
                    }
                }
                break;
            }
        }
        return new String(chars);
    }
}
