package com.shuangpeng.lcp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: LCP58（积木拼接）
 * @Date 2022/7/8 3:17 PM
 * @Version 1.0
 */
public class LCP58 {
}

// 抽工单建原（创建型设计模式5个）
// 享外装，适(世)代接合（结构型设计模式7个）
// 访问者写好策略备忘录，观察模板迭代的状态，命令中介解释责任链（行为型设计模式11个）
// 类字回复等等（Object主要方法）


class LCP58_0 {
    int n;

    // 8个旋转分别坐标偏移情况：
    // [r,c],    [c,n-1-r],[n-1-r,n-1-c],[n-1-c,r]
    // [r,n-1-c],[c,r],    [n-1-r,c],    [n-1-c,n-1-r]

    // a[0]*r+ a[1]*!r+a[2]*c+a3*!c, a[4]*r+ a[5]*!r+a[6]*c+a[7]*!c,
    int[][] r8 = {
            {1, 0, 0, 0, 0, 0, 1, 0}, {0, 0, 1, 0, 0, 1, 0, 0}, {0, 1, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 1, 0, 0, 0}, {0, 1, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 1, 0, 1, 0, 0}};

    // 预处理除shape0以外，其他5个面的200种情况。
    Map<Integer, Map<Integer, Map<Integer, Set<Integer>>>> csm;

    /**
     * side : 0:bottom 1:left 2:right 3:front 4:back 5:top
     *
     * @param shapes
     * @return
     */
    public boolean composeCube(String[][] shapes) {
        this.n = shapes[0].length;
        int cnt1 = 0;
        for (String[] s : shapes) {
            for (String ss : s) {
                for (char c : ss.toCharArray()) {
                    if (c == '1') {
                        cnt1++;
                    }
                }
            }
        }
        if (cnt1 != (n * n * n - (n - 2) * (n - 2) * (n - 2))) {
            return false;
        }

        // 固定第一个面，不作翻转。 points表示当前组合在空间中填掉的格
        Set<Integer> points = createPoints(shapes[0], 0, 0);
        csm = new HashMap<>();
        for (int i = 1; i < 6; i++) {
            for (int side = 1; side < 6; side++) {
                for (int ro = 0; ro < 8; ro++) {
                    Set<Integer> pts = createPoints(shapes[i], side, ro);
                    csm.computeIfAbsent(i, z -> new HashMap<>()).computeIfAbsent(side, z -> new HashMap<>())
                            .computeIfAbsent(ro, z -> new HashSet<>()).addAll(pts);
                }
            }
        }
        return dfs(points, 1, new boolean[6]);
    }

    private boolean dfs(Set<Integer> points, int side, boolean[] cs) {
        if (side > 5) {
            // 回溯终止，表示所有面都完成安放
            return true;
        }

        for (int next = 1; next < 6; next++) {
            if (cs[next]) {
                // 该数组记录那些面被选择过了
                continue;
            }
            cs[next] = true;
            for (int ro = 0; ro < 8; ro++) {
                // 新增的选择将在空间中增加的点
                Set<Integer> ns = csm.get(next).get(side).get(ro);
                Set<Integer> nextPoints = new HashSet<>(points);
                int sz1 = points.size();
                int sz2 = ns.size();
                nextPoints.addAll(ns);
                if (nextPoints.size() != sz1 + sz2) {
                    // 表示有点碰撞了
                    continue;
                }
                // next
                if (dfs(nextPoints, side + 1, cs)) {
                    return true;
                }
            }
            cs[next] = false;
        }

        return false;
    }

    private Set<Integer> createPoints(String[] shape, int side, int rotate) {
        Set<Integer> res = new HashSet<>();
        int[] rr = r8[rotate];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int r = rr[0] * i + rr[1] * (n - 1 - i) + rr[2] * j + rr[3] * (n - 1 - j);
                int c = rr[4] * i + rr[5] * (n - 1 - i) + rr[6] * j + rr[7] * (n - 1 - j);
                if (shape[r].charAt(c) == '1') {
                    // 每个面的XYZ。
                    if (side == 0) {
                        res.add(offset(i, j, 0));
                    } else if (side == 1) {
                        res.add(offset(0, i, j));
                    } else if (side == 2) {
                        res.add(offset(n - 1, i, j));
                    } else if (side == 3) {
                        res.add(offset(i, 0, j));
                    } else if (side == 4) {
                        res.add(offset(i, n - 1, j));
                    } else {
                        res.add(offset(i, j, n - 1));
                    }
                }
            }
        }

        return res;
        // (x, y) -> (x, y)
        // (x, y) -> (y, n - 1 - x)
        // (x, y) -> (n - 1 - x, n - 1 - y)
        // (x, y) -> (n - 1 - y, x)

        // (x, y) -> (n - 1 - x, y)
        // (x, y) -> (y, x)
        // (x, y) -> (x, n - 1 - y)
        // (x, y) -> (n - 1 - y, n - 1 - x)

    }

    int offset(int x, int y, int z) {
        return (x * 100) + (y * 10) + z;
    }
}



//class Solution {
//    int n;
//    List<List<String>> shapes;
//    // 整个立方体有哪些位置被占用了
//    boolean[][][] A = new boolean[10][10][10];
//    // 哪些面被占用了
//    short[] used = new short[6];
//
//    // 每个面的起始位置
//    int[][] CUBE_BASE = {
//            {0, 0, 0},
//            {0, 0, 1},
//            {1, 0, 1},
//            {0, 0, 1},
//            {0, 0, 1},
//            {0, 1, 1}
//    };
//    // 每个面 xyz 的增加“方向”
//    int[][] CUBE_DIR = {
//            {1, 0, 0, 0, 1, 0},
//            {1, 0, 0, 0, 1, 0},
//            {0, 0, -1, 0, 1, 0},
//            {0, 0, -1, 0, 1, 0},
//            {0, 0, -1, 1, 0, 0},
//            {0, 0, -1, 1, 0, 0}
//    };
//
//    // 形状旋转后的起始位置
//    int[][] SHAPE_BASE = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
//    // 形状旋转后行和列的增加“方向”
//    int[][] SHAPE_DIR = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
//
//    // x：哪个形状
//    // k：哪个面
//    // s：哪种旋转
//    // rc：行列是否交换
//    // modify：0 = 检查是否占用；1 = 占用；-1 = 取消占用
//    bool gao(int x, int k, int s, bool rc, int modify) {
//        for (int a = 0; a < n; a++) for (int b = 0; b < n; b++) {
//            int i = CUBE_BASE[k][0] * (n - 1) + CUBE_DIR[k][0] * a + CUBE_DIR[k][3] * b;
//            int j = CUBE_BASE[k][1] * (n - 1) + CUBE_DIR[k][1] * a + CUBE_DIR[k][4] * b;
//            int z = CUBE_BASE[k][2] * (n - 1) + CUBE_DIR[k][2] * a + CUBE_DIR[k][5] * b;
//            int si = SHAPE_BASE[s][0] * (n - 1) + SHAPE_DIR[s][0] * (rc ? b : a);
//            int sj = SHAPE_BASE[s][1] * (n - 1) + SHAPE_DIR[s][1] * (rc ? a : b);
//            if (shapes[x][si][sj] == '1') {
//                if (modify == 1) A[i][j][z] = true;
//                else if (modify == -1) A[i][j][z] = false;
//                else if (A[i][j][z]) return false;
//            }
//        }
//        return true;
//    }
//
//    bool dfs(int x) {
//        if (x == 6) return true;
//        for (int k = 0; k < 6; k++) if (!used[k]) {
//            for (int i = 0; i < 4; i++) for (int j = 0; j <= 1; j++) {
//                if (!gao(x, k, i, j, 0)) continue;
//                gao(x, k, i, j, 1);
//                used[k] = true;
//                if (dfs(x + 1)) return true;
//                used[k] = false;
//                gao(x, k, i, j, -1);
//            }
//        }
//        return false;
//    }
//
//    public:
//    bool composeCube(vector<vector<string>>& shapes) {
//        n = shapes[0].size();
//        int expected = n * n * n - (n - 2) * (n - 2) * (n - 2);
//        int actual = 0;
//        for (auto &shape : shapes) for (auto &line : shape) for (char c : line) actual += c - '0';
//        if (expected != actual) return false;
//        this->shapes = shapes;
//        return dfs(0);
//    }
//};

class LCP58_1 {
    // (x, y) -> (x, y)
    // (x, y) -> (y, n - 1 - x)
    // (x, y) -> (n - 1 - x, n - 1 - y)
    // (x, y) -> (n - 1 - y, x)

    // (x, y) -> (n - 1 - x, y)
    // (x, y) -> (y, x)
    // (x, y) -> (x, n - 1 - y)
    // (x, y) -> (n - 1 - y, n - 1 - x)

    int[][] rotate = {
            {1, 0, 0, 0, 1, 0},
            {0, 1, 0, -1, 0, 1},
            {-1, 0, 1, 0, -1, 1},
            {0, -1, 1, 1, 0, 0},
            {-1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0},
            {1, 0, 0, 0, -1, 1},
            {0, -1, 1, -1, 0, 1}
    };

    Set<Integer>[][][] pointSet = new Set[6][6][8];

    public boolean composeCube(String[][] shapes) {
        int n = shapes[0].length, cnt = 0;
        for (String[] shape : shapes) {
            for (String s : shape) {
                for (int i = 0; i < n; i++) {
                    if (s.charAt(i) == '1') {
                        cnt++;
                    }
                }
            }
        }
        if (cnt != n * n * n - (n - 2) * (n - 2) * (n - 2)) {
            return false;
        }
        for (int i = 0; i < 6; i++) {
            pointSet[i] = new Set[6][8];
            for (int j = 0; j < 6; j++) {
                pointSet[i][j] = new Set[8];
                for (int k = 0; k < 8; k++) {
                    pointSet[i][j][k] = getPoints(shapes, i, j, k);
                }
            }
        }
        boolean[] visited = new boolean[6];
        visited[0] = true;
        return dfs(1, pointSet[0][0][0], visited);
    }

    private boolean dfs(int side, Set<Integer> points, boolean[] visited) {
        if (side == 6) {
            return true;
        }
        for (int s = 0; s < 6; s++) {
            if (visited[s]) {
                continue;
            }
            visited[s] = true;
            for (int r = 0; r < rotate.length; r++) {
                Set<Integer> copy = new HashSet<>(points);
                copy.addAll(pointSet[s][side][r]);
                if (copy.size() != points.size() + pointSet[s][side][r].size()) {
                    continue;
                }
                if (dfs(side + 1, copy, visited)) {
                    return true;
                }
            }
            visited[s] = false;
        }
        return false;
    }

    private Set<Integer> getPoints(String[][] shapes, int s, int side, int r) {
        int n = shapes[0].length;
        Set<Integer> points = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = i * rotate[r][0] + j * rotate[r][1] + (n - 1) * rotate[r][2];
                int y = i * rotate[r][3] + j * rotate[r][4] + (n - 1) * rotate[r][5];
                if (shapes[s][x].charAt(y) == '1') {
                    if (side == 0) {
                        points.add(transform(i, j, 0));
                    } else if (side == 1) {
                        points.add(transform(0, j, n - 1 - i));
                    } else if (side == 2) {
                        points.add(transform(j, n - 1, n - 1 - i));
                    } else if (side == 3) {
                        points.add(transform(n - 1, n - 1 - j, n - 1 - i));
                    } else if (side == 4) {
                        points.add(transform(n - 1 - j, 0, n - 1 - i));
                    } else {
                        points.add(transform(i, j, n - 1));
                    }
                }
            }
        }
        return points;
    }

    private int transform(int x, int y, int z) {
        return x * 100 + y * 10 + z;
    }
}

