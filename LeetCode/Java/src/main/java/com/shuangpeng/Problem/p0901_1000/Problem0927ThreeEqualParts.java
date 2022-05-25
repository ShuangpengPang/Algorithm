package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem0927ThreeEqualParts
 * @Date 2022/4/21 3:44 PM
 * @Version 1.0
 */
public class Problem0927ThreeEqualParts {

    public int[] threeEqualParts0(int[] arr) {
        int n = arr.length;
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (arr[i] == 1) {
                indices.add(i);
            }
        }
        if (indices.isEmpty()) {
            return new int[]{0, n - 1};
        }
        int m = indices.size();
        if (m % 3 != 0) {
            return new int[]{-1, -1};
        }
        int count = m / 3;
        int right = indices.get(2 * count - 1) + n - indices.get(m - 1);
        if (right > indices.get(2 * count)) {
            return new int[]{-1, -1};
        }
        int left = indices.get(count - 1) + n - indices.get(m - 1) - 1;
        if (left >= indices.get(count)) {
            return new int[]{-1, -1};
        }
        for (int i = 0; i < count - 1; ++i) {
            int first = indices.get(i + 1) - indices.get(i);
            int second = indices.get(count + i + 1) - indices.get(count + i);
            int third = indices.get(2 * count + i + 1) - indices.get(2 * count + i);
            if (first != second || second != third) {
                return new int[]{-1, -1};
            }
        }
        return new int[]{left, right};
    }

    public int[] threeEqualParts1(int[] A) {
        int[] IMP = new int[]{-1, -1};
        int N = A.length;

        int S = 0;
        for (int x : A) S += x;
        if (S % 3 != 0) return IMP;
        int T = S / 3;
        if (T == 0)
            return new int[]{0, N - 1};

        int i1 = -1, j1 = -1, i2 = -1, j2 = -1, i3 = -1, j3 = -1;
        int su = 0;
        for (int i = 0; i < N; ++i) {
            if (A[i] == 1) {
                su += 1;
                if (su == 1) i1 = i;
                if (su == T) j1 = i;
                if (su == T + 1) i2 = i;
                if (su == 2 * T) j2 = i;
                if (su == 2 * T + 1) i3 = i;
                if (su == 3 * T) j3 = i;
            }
        }

        // The array is in the form W [i1, j1] X [i2, j2] Y [i3, j3] Z
        // where [i1, j1] is a block of 1s, etc.
        int[] part1 = Arrays.copyOfRange(A, i1, j1 + 1);
        int[] part2 = Arrays.copyOfRange(A, i2, j2 + 1);
        int[] part3 = Arrays.copyOfRange(A, i3, j3 + 1);

        if (!Arrays.equals(part1, part2)) return IMP;
        if (!Arrays.equals(part1, part3)) return IMP;

        // x, y, z: the number of zeros after part 1, 2, 3
        int x = i2 - j1 - 1;
        int y = i3 - j2 - 1;
        int z = A.length - j3 - 1;

        if (x < z || y < z) return IMP;
        return new int[]{j1 + z, j2 + z + 1};
    }

    public int[] threeEqualParts(int[] arr) {
        int cnt1 = 0;
        for (int n : arr) {
            cnt1 += n;
        }
        if (cnt1 % 3 != 0) return new int[]{-1, -1};
        if (cnt1 == 0) return new int[]{0, arr.length - 1};
        cnt1 = cnt1 / 3;//每个分区1的个数
        int suffix0 = 0;//每个分区后缀0的个数
        int i, j, k;
        for (i = arr.length - 1; i >= 0 && arr[i] == 0; i--) {
            suffix0++;
        }

        int idx1 = findSplit(arr, 0, cnt1, suffix0);
        if (idx1 == -1) return new int[]{-1, -1};
        int idx2 = findSplit(arr, idx1 + 1, cnt1, suffix0);
        if (idx2 == -1) return new int[]{-1, -1};

        for (i = arr.length - 1, j = idx2, k = idx1; i > idx2 && j > idx1 && k >= 0; i--, j--, k--) {
            if (arr[i] != arr[j] || arr[j] != arr[k]) return new int[]{-1, -1};
        }

        return new int[]{idx1, idx2 + 1};
    }

    public int findSplit(int[] arr, int idx, int cnt1, int suffix0) {
        int i = idx;
        while (i < arr.length) {
            if (arr[i] == 1) {
                cnt1--;
                if (cnt1 < 0) return -1;
                if (cnt1 == 0 && suffix0 == 0) return i;
            } else if (cnt1 == 0 && --suffix0 == 0) return i;
            i++;
        }

        return 0;
    }
}
