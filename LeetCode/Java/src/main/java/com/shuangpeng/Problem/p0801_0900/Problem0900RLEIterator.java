package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0900RLEIterator（RLE 迭代器）
 * @date 2023/3/8 10:50 AM
 */
public class Problem0900RLEIterator {

    class RLEIterator {

        List<long[]> list;
        long total;
        long count;
        int index;

        public RLEIterator(int[] encoding) {
            total = 0L;
            count = 0L;
            index = 0;
            list = new ArrayList<>();
            int n = encoding.length;
            for (int i = 0; i < n; i += 2) {
                if (encoding[i] == 0) {
                    continue;
                }
                total += encoding[i];
                list.add(new long[]{total, encoding[i + 1]});
            }
        }

        public int next(int n) {
            count += n;
            if (count > total) {
                return -1;
            }
            int size = list.size();
            while (index < size && count > list.get(index)[0]) {
                index++;
            }
            return (int) list.get(index)[1];
        }
    }

/**
 * Your RLEIterator object will be instantiated and called as such:
 * RLEIterator obj = new RLEIterator(encoding);
 * int param_1 = obj.next(n);
 */
}

class Problem0900RLEIterator0 {

    class RLEIterator {

        int[] encoding;
        int index, p;

        public RLEIterator(int[] encoding) {
            this.encoding = encoding;
            index = p = 0;
        }

        public int next(int n) {
            while (index < encoding.length) {
                if (p + n > encoding[index]) {
                    n -= encoding[index] - p;
                    p = 0;
                    index += 2;
                } else {
                    p += n;
                    return encoding[index + 1];
                }
            }
            return -1;
        }
    }
}
