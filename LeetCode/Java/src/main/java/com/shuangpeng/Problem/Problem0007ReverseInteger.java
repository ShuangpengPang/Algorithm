package com.shuangpeng.Problem;

public class Problem0007ReverseInteger {

//    public static void main(String[] args) {
//        Problem0007ReverseInteger a = new Problem0007ReverseInteger();
//        a.reverse(-123);
//    }

    public int reverse0(int x) {
        int answer = 0;
        while (x != 0) {
            int i = x % 10;
            x /= 10;
            if ((answer > 0 && answer > (Integer.MAX_VALUE - i) / 10)
                    || (answer < 0 && answer < (Integer.MIN_VALUE - i) / 10)) {
                answer = 0;
                break;
            }
            answer = answer * 10 + i;
        }
        return answer;
    }
}
