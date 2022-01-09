package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Problem0850RectangleAreaII {

    public int rectangleArea(int[][] rectangles) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            if (a[2] != b[2]) {
                return a[2] - b[2];
            }
            return a[3] - b[3];
        });
        for (int[] rect : rectangles) {
            if (rect[0] < rect[2] && rect[1] < rect[3]) {
                pq.offer(rect);
            }
        }
        final int M = (int) 1e9 + 7;
        long ans = 0;
        int l = -1, r = -1;
        List<int[]> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            int[] quad = pq.peek();
            int x1 = quad[0], y1 = quad[1], x2 = quad[2], y2 = quad[3];
            if (x1 == l) {
                pq.poll();
                int[] last = list.get(list.size() - 1);
                int xx2 = last[2], yy2 = last[3];
                if (y2 <= yy2 && x2 > xx2) {
                    pq.offer(new int[]{xx2, y1, x2, y2});
                } else if (y1 <= yy2 && y2 > yy2 && x2 < xx2) {
                    clip(pq, list, x2);
                    last[3] = y2;
                } else if (y1 <= yy2 && y2 > yy2 && x2 == xx2) {
                    last[3] = y2;
                } else if (y1 <= yy2 && y2 > yy2 && x2 > xx2) {
                    pq.offer(new int[]{xx2, y1, x2, y2});
                    last[3] = y2;
                } else if (y1 > yy2 && x2 < xx2) {
                    clip(pq, list, x2);
                    list.add(quad);
                } else if (y1 > yy2 && x2 == xx2) {
                    list.add(quad);
                } else if (y1 > yy2 && x2 > xx2) {
                    list.add(new int[]{x1, y1, xx2, y2});
                    pq.offer(new int[]{xx2, y1, x2, y2});
                }
                r = list.get(list.size() - 1)[2];
            } else if (x1 < r) {
                clip(pq, list, x1);
                r = x1;
            } else if (x1 >= r) {
                for (int[] rect : list) {
                    ans += (long) (rect[2] - rect[0]) * (rect[3] - rect[1]);
                    ans %= M;
                }
                list.clear();
                l = x1;
                r = x2;
                list.add(pq.poll());
            }
        }
        for (int[] rect : list) {
            ans += (long) (rect[2] - rect[0]) * (rect[3] - rect[1]);
            ans %= M;
        }
        return (int) ans;
    }

    private void clip(PriorityQueue<int[]> pq, List<int[]> list, int x) {
        for (int[] rect : list) {
            pq.offer(new int[]{x, rect[1], rect[2], rect[3]});
            rect[2] = x;
        }
    }

//    public static void main(String[] args) {
//        Problem0850RectangleAreaII a = new Problem0850RectangleAreaII();
////        int[][] rect = {{14,59,56,89},{26,76,39,86},{3,18,36,44}};
//        int[][] rect = {{471,0,947,999},{780,0,823,320},{868,0,948,538},{907,0,911,673},{929,0,952,596},{458,0,889,669},{156,0,364,754},{900,0,973,236},{406,0,620,454},{773,0,946,538},{407,0,834,23},{759,0,858,526},{431,0,776,599},{969,0,979,30},{642,0,737,339},{239,0,448,183},{260,0,517,903},{14,0,674,976},{251,0,850,112},{57,0,794,395},{595,0,728,149},{970,0,989,36},{496,0,954,791},{447,0,832,805},{829,0,939,100},{169,0,568,501},{704,0,969,411},{607,0,609,221},{935,0,953,437},{47,0,670,130},{794,0,799,230},{943,0,959,90},{332,0,337,732},{123,0,228,344},{281,0,487,598},{381,0,732,443},{235,0,391,548},{646,0,930,20},{219,0,675,95},{8,0,212,227},{138,0,704,658},{368,0,782,707},{810,0,826,957},{543,0,697,654},{887,0,986,180},{837,0,900,228},{280,0,391,331},{180,0,229,42},{201,0,489,687},{648,0,680,732},{228,0,630,922},{886,0,960,56},{946,0,955,522},{903,0,992,464},{557,0,860,38},{89,0,268,642},{669,0,774,185},{1,0,724,374},{395,0,923,782},{82,0,230,550},{166,0,166,808},{441,0,644,435},{497,0,823,224},{372,0,973,556},{188,0,846,127},{226,0,396,535},{869,0,945,575},{406,0,526,795},{781,0,795,569},{563,0,831,991},{466,0,486,641},{274,0,855,529},{61,0,819,364},{285,0,421,101},{193,0,950,748},{320,0,655,836},{207,0,627,945},{782,0,899,56},{578,0,970,913},{499,0,684,205},{490,0,877,16},{483,0,668,915},{364,0,741,16}};
//        int ans = a.rectangleArea(rect);
//        int i = 1;
//    }
}
