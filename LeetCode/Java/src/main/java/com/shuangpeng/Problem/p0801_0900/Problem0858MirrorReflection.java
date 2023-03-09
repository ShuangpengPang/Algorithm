package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0858MirrorReflection（镜面反射）
 * @date 2023/3/2 2:20 PM
 */
public class Problem0858MirrorReflection {

    // t / x = c / (c - p)
    //' x = t * (c - p) / c
    // x = t - t * p / c

    public int mirrorReflection(int p, int q) {
        int d = 1, b = 0;
        long t1 = p, t2 = q, x = p, y = 1;
        int ans = -1;
        while (true) {
            long tx = x, ty = y;
            x = x * t2;
            y = y * t1;
            long g = gcd(x, y);
            x /= g;
            y /= g;
            ans = getCornet(b, d, x, y, p);
            if (ans != -1) {
                break;
            }
            if (x > y * p) {
                b = (b + d * 2 + 4) % 4;
                d *= -1;
                long[] a = add(x, y, -p, 1);
                a = multiple(tx, ty, a[0], a[1]);
                a = multiple(a[0], a[1], y, x);
                x = a[0];
                y = a[1];
            } else {
                b = (b + d + 4) % 4;
                x = p * y - x;
                long t = t1;
                t1 = t2;
                t2 = t;
            }
        }
        return ans;
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int getCornet(int b, int d, long x, long y, long p) {
        if (y != 1 || x != p) {
            return -1;
        }
        if (d == 1) {
            if (b == 2) {
                return -1;
            }
            return (b + 1) % 4;
        }
        if (b == 1) {
            return -1;
        }
        return (b + 2) % 4;
    }

    private long[] add(long x, long y, long a, long b) {
        long tx = x * b + y * a, ty = y * b;
        long g = gcd(tx, ty);
        return new long[]{tx/ g, ty / g};
    }

    private long[] multiple(long x, long y, long a, long b) {
        x *= a;
        y *= b;
        long g = gcd(x, y);
        return new long[]{x / g, y / g};
    }

//    public static void main(String[] args) {
//        Problem0858MirrorReflection a = new Problem0858MirrorReflection();
//        a.mirrorReflection(347, 89);
//    }
}

class Problem0858MirrorReflection0 {

    static final double E = 1e-6;

    public int mirrorReflection(int p, int q) {
        double rx = p, ry = q, x = 0, y = 0;
        while (!(close(x, p) && close(y, 0) || close(x, p) && close(y, p) || close(x, 0) && close(y, p))) {
            double t = Double.MAX_VALUE;
            if (-x / rx > E) {
                t = Math.min(t, -x / rx);
            }
            if (-y / ry > E) {
                t = Math.min(t, -y / ry);
            }
            if ((p - x) / rx > E) {
                t = Math.min(t, (p - x) / rx);
            }
            if ((p - y) / ry > E) {
                t = Math.min(t, (p - y) / ry);
            }
            x += t * rx;
            y += t * ry;
            if (close(x, 0) || close(x, p)) {
                rx = -rx;
            }
            if (close(y, 0) || close(y, p)) {
                ry = -ry;
            }
        }
        if (close(x, p)) {
            return close(y, 0) ? 0 : 1;
        }
        return 2;
    }

    private boolean close(double d1, double d2) {
        return Math.abs(d1 - d2) < E;
    }
}
