package com.shuangpeng.Problem.p0401_0500;

public class Problem0405ConvertANumberToHexadecimal {

    public String toHex0(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 31, j = 0; i >= 0; --i) {
            j <<= 1;
            if ((num & (1 << i)) != 0) {
                j += 1;
            }
            if (i % 4 == 0) {
                if (j != 0 || sb.length() > 0) {
                    if (j <= 9) {
                        sb.append(j);
                    } else {
                        sb.append((char) ('a' + j - 10));
                    }
                }
                j = 0;
            }
        }
        return sb.toString();
    }

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; --i) {
            int val = num >> (i * 4) & 0xf;
            if (val > 0 || sb.length() > 0) {
                sb.append((char) (val < 10 ? '0' + val : 'a' + val - 10));
            }
        }
        return sb.toString();
    }
}
