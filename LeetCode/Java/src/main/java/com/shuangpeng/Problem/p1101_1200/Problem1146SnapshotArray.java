package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1146SnapshotArray（快照数组）
 * @date 2023/6/2 3:24 PM
 */
public class Problem1146SnapshotArray {

    class SnapshotArray {

        List<int[]>[] arr;
        int snapId;

        public SnapshotArray(int length) {
            snapId = 0;
            arr = new List[length];
            for (int i = 0; i < length; i++) {
                arr[i] = new ArrayList<>();
                arr[i].add(new int[2]);
            }
        }

        public void set(int index, int val) {
            List<int[]> list = arr[index];
            if (list.get(list.size() - 1)[0] < snapId) {
                list.add(new int[]{snapId, val});
            } else {
                list.get(list.size() - 1)[1] = val;
            }
        }

        public int snap() {
            return snapId++;
        }

        public int get(int index, int snap_id) {
            List<int[]> list = arr[index];
            int left = 0, right = list.size() - 1;
            while (left <= right) {
                int mid = left + (right - left >> 1);
                if (list.get(mid)[0] <= snap_id) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return list.get(left - 1)[1];
        }
    }

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */
}

class Problem1146SnapshotArray0 {

    class SnapshotArray {

        TreeMap<Integer, Integer>[] arr;
        int snapId;

        public SnapshotArray(int length) {
            snapId = 0;
            arr = new TreeMap[length];
            for (int i = 0; i < length; i++) {
                arr[i] = new TreeMap<>();
                arr[i].put(0, 0);
            }
        }

        public void set(int index, int val) {
            arr[index].put(snapId, val);
        }

        public int snap() {
            return snapId++;
        }

        public int get(int index, int snap_id) {
            return arr[index].floorEntry(snap_id).getValue();
        }
    }
}
