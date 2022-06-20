package com.shuangpeng.Problem.p0701_0800;

/**
 * @Description: Problem0715RangeModule（range模块）
 * @Date 2022/6/20 10:00 AM
 * @Version 1.0
 */
public class Problem0715RangeModule {

    class RangeModule {

        class Node {
            int start, end;
            Node left, right;
            boolean cover;

            Node(int s, int e) {
                this.start = s;
                this.end = e;
            }

            int getMid() {
                return start + ((end - start) >> 1);
            }

            void add(int s, int e) {
                if (this.cover) {
                    return;
                }
                if (s <= this.start && e >= this.end) {
                    this.cover = true;
                    return;
                }
                int mid = getMid();
                if (s < mid && this.left == null) {
                    this.left = new Node(this.start, mid);
                }
                if (e > mid && this.right == null) {
                    this.right = new Node(mid, this.end);
                }
                if (e <= mid) {
                    this.left.add(s, e);
                } else if (s >= mid) {
                    this.right.add(s, e);
                } else {
                    this.left.add(s, mid);
                    this.right.add(mid, e);
                }
                this.cover = this.left != null && this.right != null && this.left.cover && this.right.cover;
            }

            boolean query(int s, int e) {
                if (this.cover) {
                    return true;
                }
                if (s <= this.start && e >= this.end) {
                    return false;
                }
                int mid = getMid();
                if (s < mid && this.left == null) {
                    return false;
                } else if (e > mid && this.right == null) {
                    return false;
                }
                if (e <= mid) {
                    return this.left.query(s, e);
                } else if (s >= mid) {
                    return this.right.query(s, e);
                } else {
                    return this.left.query(s, mid) && this.right.query(mid, e);
                }
            }

            void remove(int s, int e) {
                int mid = getMid();
                if (this.cover) {
                    if (this.left == null) {
                        this.left = new Node(this.start, mid);
                    }
                    if (this.right == null) {
                        this.right = new Node(mid, this.end);
                    }
                    this.left.cover = true;
                    this.right.cover = true;
                }
                if (s <= this.start && e >= mid) {
                    this.left = null;
                }
                if (s <= mid && e >= this.end) {
                    this.right = null;
                }
                if (e <= mid && this.left != null) {
                    this.left.remove(s, e);
                } else if (s >= mid && this.right != null) {
                    this.right.remove(s, e);
                } else if (s < mid && e > mid) {
                    if (this.left != null) {
                        this.left.cover |= this.cover;
                        this.left.remove(s, mid);
                    }
                    if (this.right != null) {
                        this.right.remove(mid, e);
                    }
                }
                this.cover = false;
            }
        }

        Node root;

        public RangeModule() {
            root = new Node(1, (int) 1e9);
        }

        public void addRange(int left, int right) {
            root.add(left, right);
        }

        public boolean queryRange(int left, int right) {
            return root.query(left, right);
        }

        public void removeRange(int left, int right) {
            root.remove(left, right);
        }
    }
}

class RangeModule {

    class Node {
        int start, end;
        Node left, right;
        boolean cover;

        Node(int s, int e) {
            this.start = s;
            this.end = e;
        }

        int getMid() {
            return start + ((end - start) >> 1);
        }

        void add(int s, int e) {
            if (this.cover) {
                return;
            }
            if (s <= this.start && e >= this.end) {
                this.cover = true;
                return;
            }
            int mid = getMid();
            if (s < mid && this.left == null) {
                this.left = new Node(this.start, mid);
            }
            if (e > mid && this.right == null) {
                this.right = new Node(mid, this.end);
            }
            if (e <= mid) {
                this.left.add(s, e);
            } else if (s >= mid) {
                this.right.add(s, e);
            } else {
                this.left.add(s, mid);
                this.right.add(mid, e);
            }
            this.cover = this.left != null && this.right != null && this.left.cover && this.right.cover;
        }

        boolean query(int s, int e) {
            if (this.cover) {
                return true;
            }
            if (s <= this.start && e >= this.end) {
                return false;
            }
            int mid = getMid();
            if (s < mid && this.left == null) {
                return false;
            } else if (e > mid && this.right == null) {
                return false;
            }
            if (e <= mid) {
                return this.left.query(s, e);
            } else if (s >= mid) {
                return this.right.query(s, e);
            } else {
                return this.left.query(s, mid) && this.right.query(mid, e);
            }
        }

        void remove(int s, int e) {
            int mid = getMid();
            if (this.cover) {
                if (this.left == null) {
                    this.left = new Node(this.start, mid);
                }
                if (this.right == null) {
                    this.right = new Node(mid, this.end);
                }
                this.left.cover = true;
                this.right.cover = true;
            }
            if (s <= this.start && e >= mid) {
                this.left = null;
            }
            if (s <= mid && e >= this.end) {
                this.right = null;
            }
            if (e <= mid && this.left != null) {
                this.left.remove(s, e);
            } else if (s >= mid && this.right != null) {
                this.right.remove(s, e);
            } else if (s < mid && e > mid) {
                if (this.left != null) {
                    this.left.cover |= this.cover;
                    this.left.remove(s, mid);
                }
                if (this.right != null) {
                    this.right.remove(mid, e);
                }
            }
            this.cover = false;
        }
    }

    Node root;

    public RangeModule() {
        root = new Node(1, (int) 1e9);
    }

    public void addRange(int left, int right) {
        root.add(left, right);
    }

    public boolean queryRange(int left, int right) {
        return root.query(left, right);
    }

    public void removeRange(int left, int right) {
        root.remove(left, right);
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
