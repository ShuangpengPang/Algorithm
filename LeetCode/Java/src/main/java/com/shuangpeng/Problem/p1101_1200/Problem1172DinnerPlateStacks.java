package com.shuangpeng.Problem.p1101_1200;

import java.util.*;

/**
 * @Description: Problem1172DinnerPlateStacks（餐盘栈）
 * @Date 2022/7/11 10:41 PM
 * @Version 1.0
 */
public class Problem1172DinnerPlateStacks {

    class DinnerPlates {

        int capacity;
        TreeMap<Integer, Deque<Integer>> map;
        TreeSet<Integer> set;

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
            map = new TreeMap<>();
            set = new TreeSet<>();
        }

        public void push(int val) {
            int id = set.isEmpty() ? map.size() : set.first();
            map.putIfAbsent(id, new ArrayDeque<>());
            Deque<Integer> q = map.get(id);
            q.push(val);
            if (q.size() == capacity) {
                set.remove(id);
            } else {
                set.add(id);
            }
        }

        public int pop() {
            if (map.isEmpty()) {
                return -1;
            }
            Map.Entry<Integer, Deque<Integer>> entry = map.lastEntry();
            Deque<Integer> q = entry.getValue();
            int ans = q.pop();
            set.add(entry.getKey());
            if (q.isEmpty()) {
                map.remove(entry.getKey());
            }
            return ans;
        }

        public int popAtStack(int index) {
            if (!map.containsKey(index)) {
                return -1;
            }
            Deque<Integer> q = map.get(index);
            int ans = q.pop();
            set.add(index);
            if (q.isEmpty()) {
                map.remove(index);
            }
            return ans;
        }
    }

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
}

class Problem1172DinnerPlateStacks0 {

    class DinnerPlates {

        int N = 2 * (int) 1e5;
        int capacity;

        class Node {
            int start, end, count;
            Node left, right;
            Deque<Integer> q;

            Node(int s, int e) {
                start = s;
                end = e;
            }

            void push(int val) {
                if (start + 1 == end) {
                    if (q == null) {
                        q = new ArrayDeque<>();
                    }
                    q.push(val);
                    count++;
                    return;
                }
                int mid = getMid();
                if (this.left == null) {
                    this.left = new Node(this.start, mid);
                }
                long total = (long) capacity * (mid - this.start);
                if (this.left.count < total) {
                    this.left.push(val);
                } else {
                    if (this.right == null) {
                        this.right = new Node(mid, this.end);
                    }
                    this.right.push(val);
                }
                this.count++;
            }

            int pop() {
                if (count == 0) {
                    return -1;
                }
                count--;
                if (start + 1 == end) {
                    return q.pop();
                }
                if (this.right == null || this.right.count == 0) {
                    return this.left.pop();
                }
                return this.right.pop();
            }

            int popAtStack(int index) {
                if (this.count == 0) {
                    return -1;
                }
                if (start + 1 == end) {
                    count--;
                    return q.pop();
                }
                int mid = getMid();
                int ans = -1;
                if (index < mid) {
                    if (left == null) {
                        return -1;
                    }
                    ans = left.popAtStack(index);
                } else {
                    if (right == null) {
                        return -1;
                    }
                    ans = right.popAtStack(index);
                }
                if (ans != -1) {
                    count--;
                }
                return ans;
            }

            int getMid() {
                return start + ((end - start) >> 1);
            }
        }

        Node root;

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
            root = new Node(0, N);
        }

        public void push(int val) {
            root.push(val);
        }

        public int pop() {
            return root.pop();
        }

        public int popAtStack(int index) {
            return root.popAtStack(index);
        }
    }
}

class DinnerPlates {

    List<LinkedList> plates;
    int capacity;
    int right;
    List<Integer> emptyPlace;

    public DinnerPlates(int capacity) {
        plates = new ArrayList<>();
        this.capacity = capacity;
        right = 0;
        emptyPlace = new ArrayList<>();
    }

    public void push(int val) {
        if (emptyPlace.isEmpty() || right <= emptyPlace.get(0)) {
            if (plates.isEmpty() || plates.size() <= right) {
                LinkedList<Integer> newPlate = new LinkedList<>();
                newPlate.push(val);
                plates.add(newPlate);
                if (newPlate.size() == capacity) {
                    right++;
                }
            } else {
                LinkedList<Integer> plate = plates.get(right);
                plate.push(val);
                if (plate.size() == capacity) {
                    right++;
                }
            }
        } else {
            LinkedList<Integer> plate = plates.get(emptyPlace.get(0));
            plate.push(val);
            emptyPlace.remove(0);
        }
    }

    public int pop() {
        if (plates.isEmpty()) {
            return -1;
        }
        while (plates.size() <= right) {
            if (--right < 0) {
                return -1;
            }
        }
        while (plates.get(right).isEmpty()) {
            plates.remove(right--);
            if (right < 0) {
                return -1;
            }
        }

        LinkedList<Integer> plate = plates.get(right);
        int pop = plate.pop();
        while (plate.isEmpty()) {
            plates.remove(right--);
            if (right > 0) {
                plate = plates.get(right);
            } else {
                break;
            }
        }
        return pop;
    }

    public int popAtStack(int index) {
        if (plates.isEmpty() || index >= plates.size() || plates.get(index).isEmpty()) {
            return -1;
        }

        if (index != right) {
            emptyPlace.add(index);
            Collections.sort(emptyPlace);
        }

        LinkedList<Integer> plate = plates.get(index);
        return plate.pop();
    }
}

