package com.shuangpeng.Problem;

public class Problem0043MultiplyStrings {

    public String multiply0(String num1, String num2) {
        if (num2.length() > num1.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int length1 = num1.length();
        int length2 = num2.length();
        String answer = "0";
        for (int i = length2 - 1; i >= 0; i--) {
            int n2 = num2.charAt(i) - '0';
            if (n2 != 0) {
                int carry = 0;
                StringBuilder builder = new StringBuilder();
                for (int k = 0; k < length2 - i - 1; k++) {
                    builder.append('0');
                }
                for (int j = length1 - 1; j >= 0; j--) {
                    int n1 = num1.charAt(j) - '0';
                    int result = n1 * n2 + carry;
                    builder.insert(0, "" + result % 10);
                    carry = result / 10;
                }
                if (carry != 0) {
                    builder.insert(0, "" + carry);
                }
                answer = stringAdd(answer, builder.toString());
            }
        }
        return answer.toString();
    }

    public String stringAdd(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return str1 != null ? str1 : str2;
        }
        int length1 = str1.length();
        int length2 = str2.length();
        int i = length1 - 1;
        int j = length2 - 1;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while (i >= 0 || j >= 0 || carry > 0) {
            int x = i >= 0 ? str1.charAt(i) - '0' : 0;
            int y = j >= 0 ? str2.charAt(j) - '0' : 0;
            int sum = x + y + carry;
            builder.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }
        return builder.reverse().toString();
    }

    public StringBuilder add(StringBuilder builder1, StringBuilder builder2) {
        if (builder1 == null || builder2 == null) {
            return builder1 == null ? builder2 : builder1;
        }
        if (builder2.length() > builder1.length()) {
            StringBuilder builder = builder1;
            builder1 = builder2;
            builder2 = builder;
        }
        int length1 = builder1.length();
        int length = builder2.length();
        int carry = 0;
        for (int i = length - 1; i >= 0; i--) {
            int j = length1 - length + i;
            int result = builder2.charAt(i) + builder1.charAt(j) + carry - 2 * '0';
            int data = result % 10;
            carry = result / 10;
            builder1.setCharAt(j, (char) ('0' + data));
        }
        int j = length1 - length - 1;
        while (j >= 0 && carry != 0) {
            int data = builder1.charAt(j) + carry - '0';
            builder1.setCharAt(j, (char) ('0' + data % 10));
            carry = data / 10;
            j--;
        }
        if (carry > 0) {
            builder1.insert(0, "" + carry);
        }
        return builder1;
    }

//    public static void main(String[] args) {
//        Problem0043MultiplyStrings a = new Problem0043MultiplyStrings();
//        String answer = a.multiply("123", "456");
//    }

    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "0";
        }
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int length1 = num1.length();
        int length2 = num2.length();
        int[] result = new int[length1 + length2];
        for (int i = length2 - 1; i >= 0; i--) {
            int num = num2.charAt(i) - '0';
            for (int j = length1 - 1; j >= 0; j--) {
                int product = num * (num1.charAt(j) - '0') + result[i + j + 1];
                result[i + j + 1] = product % 10;
                result[i + j] += product / 10;
            }
        }
        StringBuilder builder = new StringBuilder();
        int start = result[0] == 0 ? 1 : 0;
        for (int i = start; i < length1 + length2; i++) {
            builder.append(result[i]);
        }
        return builder.toString();
    }
}
