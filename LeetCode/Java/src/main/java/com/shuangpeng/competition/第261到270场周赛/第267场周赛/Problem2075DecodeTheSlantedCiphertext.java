package com.shuangpeng.competition.第261到270场周赛.第267场周赛;

public class Problem2075DecodeTheSlantedCiphertext {

    // 比赛时写法
    public String decodeCiphertext0(String encodedText, int rows) {
        int length = encodedText.length();
        int cols = length / rows;
        int idx = 0;
        char[][] chars = new char[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                chars[i][j] = encodedText.charAt(idx++);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < cols; ++j) {
            for (int i = 0; i < rows && i + j < cols; ++i) {
                sb.append(chars[i][i + j]);
            }
        }
        int n = sb.length();
        for (int i = n - 1; i >= 0; --i) {
            if (sb.charAt(i) == ' ') {
                sb.deleteCharAt(i);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public String decodeCiphertext(String encodedText, int rows) {
        int n = encodedText.length();
        int cols = n / rows;
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < cols; ++j) {
            for (int i = 0; i < rows && i * cols + i + j < n; ++i) {
                sb.append(encodedText.charAt(i * cols + i + j));
            }
        }
        for (int i = sb.length() - 1; i >= 0 && sb.charAt(i) == ' '; --i) {
            sb.deleteCharAt(i);
        }
        return sb.toString();
    }
}
