package com.shuangpeng.competition.第079场双周赛;

/**
 * @Description: Problem2286BookingConcertTicketsInGroups（以组为单位订音乐会的门票）
 * @Date 2022/6/24 12:04 PM
 * @Version 1.0
 */
public class Problem2286BookingConcertTicketsInGroups {


}

class BookMyShow {

    class Node {
        int start, end, minValue;
        long sum;
        long total;
        Node left, right;

        public Node(int s, int e) {
            this.start = s;
            this.end = e;
            this.total = (long) (e - s + 1) * cols;
        }

        public Node findGather(int k) {
            int min = cols - k;
            if (this.minValue > min) {
                return null;
            }
            if (this.start == this.end) {
                return this;
            }
            int mid = getMid();
            if (this.left == null) {
                this.left = new Node(this.start, mid);
            }
            Node node = this.left.findGather(k);
            if (node != null) {
                return node;
            }
            if (this.right == null) {
                this.right = new Node(mid + 1, this.end);
            }
            return this.right.findGather(k);
        }

        private int findScatter(int k) {
            if (this.sum + k > this.total) {
                return -1;
            }
            if (this.start == this.end || this.sum + k == this.total) {
                return this.end;
            }
            int mid = getMid();
            if (this.left == null) {
                this.left = new Node(this.start, mid);
            }
            int row = this.left.findScatter(k);
            if (row != -1) {
                return row;
            }
            k -= this.left.total - this.left.sum;
            if (this.right == null) {
                this.right = new Node(mid + 1, this.end);
            }
            return this.right.findScatter(k);
        }

        private int updateGather(int k, int row) {
            if (this.start == this.end) {
                int value = this.minValue;
                this.minValue += k;
                this.sum += k;
                return value;
            }
            int mid = getMid();
            if (this.left == null) {
                this.left = new Node(this.start, mid);
            }
            if (this.right == null) {
                this.right = new Node(mid + 1, this.end);
            }
            int ans = row <= mid ? this.left.updateGather(k, row) : this.right.updateGather(k, row);
            this.minValue = Math.min(this.left.minValue, this.right.minValue);
            this.sum += k;
            return ans;
        }

        private void updateScatter(int k) {
            if (k >= this.total - this.sum) {
                this.sum = this.total;
                this.minValue = cols;
                return;
            }
            if (this.start == this.end) {
                this.sum += k;
                this.minValue += k;
                return;
            }
            int mid = getMid();
            if (this.left == null) {
                this.left = new Node(this.start, mid);
            }
            if (this.right == null) {
                this.right = new Node(mid + 1, this.end);
            }
            long t = k - (this.left.total - this.left.sum);
            this.left.updateScatter(k);
            if (t > 0) {
                if (this.right == null) {
                    this.right = new Node(mid + 1, this.end);
                }
                this.right.updateScatter((int) t);
            }
            this.sum += k;
            this.minValue = Math.min(this.left.minValue, this.right.minValue);
        }

        private int getMid() {
            return start + ((end - start) >> 1);
        }
    }

    int rows, cols, startRow;
//    int[] seats;
    Node root;

    public BookMyShow(int n, int m) {
        this.rows = n;
        this.cols = m;
        this.startRow = 0;
//        seats = new int[n];
        root = new Node(0, n - 1);
    }

    public int[] gather(int k, int maxRow) {
        Node node = root.findGather(k);
        if (node == null || node.start > maxRow) {
            return new int[0];
        }
        int[] ans = {node.start, node.minValue};
        root.updateGather(k, node.start);
        return ans;
    }

    public boolean scatter(int k, int maxRow) {
        int row = root.findScatter(k);
        if (row == -1 || row > maxRow) {
            return false;
        }
        root.updateScatter(k);
        return true;
    }

//    public static void main(String[] args) {
//        BookMyShow a = new BookMyShow(25, 941);
//        int[][] array = {{34,1},{296,21},{927,18},{695,15},{830,22},{638,2},{169,15}};
//        for (int[] p : array) {
//            a.gather(p[0], p[1]);
//        }
//    }
}

/**
 * Your BookMyShow object will be instantiated and called as such:
 * BookMyShow obj = new BookMyShow(n, m);
 * int[] param_1 = obj.gather(k,maxRow);
 * boolean param_2 = obj.scatter(k,maxRow);
 */
