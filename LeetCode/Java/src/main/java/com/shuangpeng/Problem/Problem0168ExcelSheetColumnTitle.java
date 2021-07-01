package com.shuangpeng.Problem;

public class Problem0168ExcelSheetColumnTitle {

    public String convertToTitle(int columnNumber) {
        int base = 26;
        StringBuilder builder = new StringBuilder();
        while (columnNumber != 0) {
            char c = (char) ('A' + ((columnNumber - 1) % base));
            builder.insert(0, c);
            columnNumber = (columnNumber - 1) / base;
        }
        return builder.toString();
    }

//    public static void main(String[] args) {
//        Problem0168ExcelSheetColumnTitle a = new Problem0168ExcelSheetColumnTitle();
//        String s = a.convertToTitle(701);
//        int i = 1;
//    }
}
