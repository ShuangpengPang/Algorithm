package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0972EqualRationalNumbers
 * @Date 2022/4/26 1:28 PM
 * @Version 1.0
 */
public class Problem0972EqualRationalNumbers {

    public boolean isRationalEqual0(String s, String t) {
        long[] fraction1 = getFraction(s), fraction2 = getFraction(t);
        return fraction1[0] == fraction2[0] && fraction1[1] == fraction2[1];
    }

    private long[] getFraction(String s) {
        int n = s.length();
        long num = 0;
        int i = 0;
        while (i < n && s.charAt(i) != '.') {
            num = num * 10 + s.charAt(i) - '0';
            ++i;
        }
        ++i;
        long a = 0, b = 1;
        while (i < n && s.charAt(i) != '(') {
            a = a * 10 + s.charAt(i) - '0';
            b *= 10;
            ++i;
        }
        ++i;
        long c = 0, d = 0;
        while (i < n && s.charAt(i) != ')') {
            c = c * 10 + s.charAt(i) - '0';
            d = d * 10 + 9;
            ++i;
        }
        long x = a, y = b;
        if (d != 0) {
            x = a * d + c;
            y = b * d;
        }
        x += num * y;
        long g = gcd(x, y);
        return new long[]{x / g, y / g};
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public boolean isRationalEqual(String S, String T) {
        Fraction f1 = convert(S);
        Fraction f2 = convert(T);
        return f1.n == f2.n && f1.d == f2.d;
    }

    public Fraction convert(String S) {
        int state = 0; //whole, decimal, repeating
        Fraction ans = new Fraction(0, 1);
        int decimal_size = 0;

        for (String part: S.split("[.()]")) {
            state++;
            if (part.isEmpty()) continue;
            long x = Long.valueOf(part);
            int sz = part.length();

            if (state == 1) { // whole
                ans.iadd(new Fraction(x, 1));
            } else if (state == 2) { // decimal
                ans.iadd(new Fraction(x, (long) Math.pow(10, sz)));
                decimal_size = sz;
            } else { // repeating
                long denom = (long) Math.pow(10, decimal_size);
                denom *= (long) (Math.pow(10, sz) - 1);
                ans.iadd(new Fraction(x, denom));
            }
        }
        return ans;
    }

    class Fraction {
        long n, d;
        Fraction(long n, long d) {
            long g = gcd(n, d);
            this.n = n / g;
            this.d = d / g;
        }

        public void iadd(Fraction other) {
            long numerator = this.n * other.d + this.d * other.n;
            long denominator = this.d * other.d;
            long g = gcd(numerator, denominator);
            this.n = numerator / g;
            this.d = denominator / g;
        }

        long gcd(long x, long y) {
            return x != 0 ? gcd(y % x, x) : y;
        }
    }

//    public static void main(String[] args) {
//        Problem0972EqualRationalNumbers a = new Problem0972EqualRationalNumbers();
//        a.isRationalEqual("1.", "0.9(9)");
//    }
}
