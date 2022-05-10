package com.shuangpeng.Problem.p1701_1800;

import java.util.*;

/**
 * @Description: Problem1728CatAndMouseII（猫和老鼠II）
 * @Date 2022/5/10 10:11 AM
 * @Version 1.0
 */
public class Problem1728CatAndMouseII {

    String[] grid;
    int m = 0;
    int n = 0;
    int tx = 0;
    int ty = 0;
    int catJump = 0;
    int mouseJump = 0;
    static final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length();
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        int cx = 0, cy = 0, mx = 0, my = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                char c = grid[i].charAt(j);
                if (c == 'C') {
                    cx = i;
                    cy = j;
                } else if (c == 'M') {
                    mx = i;
                    my = j;
                } else if (c == 'F') {
                    this.tx = i;
                    this.ty = j;
                }
            }
        }
        int M = m * m * n * n;
        int[][] memo = new int[M][1001];
        for (int i = 0; i < M; ++i) {
            Arrays.fill(memo[i], -1);
        }
        return dfs(cx, cy, mx, my, 0, memo) == 1;
    }

    private int dfs(int cx, int cy, int mx, int my, int turn, int[][] memo) {
        if (turn > 1000) {
            return 0;
        }
        boolean mouseMove = (turn & 1) == 0;
        int key = (cx * n + cy) * m * n + mx * n + my;
        if (memo[key][turn] != -1) {
            return memo[key][turn];
        }
        int target = mouseMove ? 1 : 0;
        int x = mouseMove ? mx : cx;
        int y = mouseMove ? my : cy;
        int jump = mouseMove ? mouseJump : catJump;
        for (int[] dir : dirs) {
            for (int i = 1; i <= jump; ++i) {
                int nx = x + dir[0] * i;
                int ny = y + dir[1] * i;
                if (nx < 0 || nx >= m || ny < 0 || ny >= n || grid[nx].charAt(ny) == '#') {
                    break;
                }
                if (mouseMove && nx == cx && ny ==cy) {
                    continue;
                } else if (!mouseMove && nx == mx && ny == my) {
                    return memo[key][turn] = target;
                }
                if (nx == tx && ny == ty) {
                    return memo[key][turn] = target;
                }
                if (mouseMove && dfs(cx, cy, nx, ny, turn + 1, memo) == 1) {
                    return memo[key][turn] = 1;
                } else if (!mouseMove && dfs(nx, ny, mx, my, turn + 1, memo) == 0) {
                    return memo[key][turn] = 0;
                }
            }
        }
        return memo[key][turn] = dfs(cx, cy, mx, my, turn + 1, memo);
    }

//    public static void main(String[] args) {
//        Problem1728CatAndMouseII a = new Problem1728CatAndMouseII();
//        String[] grid = {".M...","..#..","#..#.","C#.#.","...#F"};
//        a.canMouseWin(grid, 3, 1);
//    }
}

class Problem1728CatAndMouseII0 {
    static int S = 8 * 8 * 8 * 8, K = 1000;
    static int[][] f = new int[S][K]; // mouse : 0 / cat : 1
    String[] g;
    int n, m, a, b, tx, ty;
    int[][] dirs = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
    // mouse : (x, y) / cat : (p, q)
    int dfs(int x, int y, int p, int q, int k) {
        int state = (x << 9) | (y << 6) | (p << 3) | q;
        if (k == K - 1) return f[state][k] = 1;
        if (x == p && y == q) return f[state][k] = 1;
        if (x == tx && y == ty) return f[state][k] = 0;
        if (p == tx && q == ty) return f[state][k] = 1;
        if (f[state][k] != -1) return f[state][k];
        if (k % 2 == 0) { // mouse
            for (int[] di : dirs) {
                for (int i = 0; i <= b; i++) {
                    int nx = x + di[0] * i, ny = y + di[1] * i;
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) break;
                    if (g[nx].charAt(ny) == '#') break;
                    if (dfs(nx, ny, p, q, k + 1) == 0) return f[state][k] = 0;
                }
            }
            return f[state][k] = 1;
        } else { // cat
            for (int[] di : dirs) {
                for (int i = 0; i <= a; i++) {
                    int np = p + di[0] * i, nq = q + di[1] * i;
                    if (np < 0 || np >= n || nq < 0 || nq >= m) break;
                    if (g[np].charAt(nq) == '#') break;
                    if (dfs(x, y, np, nq, k + 1) == 1) return f[state][k] = 1;
                }
            }
            return f[state][k] = 0;
        }
    }
    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        g = grid;
        n = g.length; m = g[0].length(); a = catJump; b = mouseJump;
        for (int i = 0; i < S; i++) Arrays.fill(f[i], -1);
        int x = 0, y = 0, p = 0, q = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i].charAt(j) == 'M') {
                    x = i; y = j;
                } else if (g[i].charAt(j) == 'C') {
                    p = i; q = j;
                } else if (g[i].charAt(j) == 'F') {
                    tx = i; ty = j;
                }
            }
        }
        return dfs(x, y, p, q, 0) == 0;
    }
}

class Problem1728CatAndMouseII1 {
    static final int MOUSE_TURN = 0, CAT_TURN = 1;
    static final int UNKNOWN = 0, MOUSE_WIN = 1, CAT_WIN = 2;
    static final int MAX_MOVES = 1000;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int rows, cols;
    String[] grid;
    int catJump, mouseJump;
    int food;
    int[][][] degrees;
    int[][][][] results;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        this.rows = grid.length;
        this.cols = grid[0].length();
        this.grid = grid;
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        int startMouse = -1, startCat = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = grid[i].charAt(j);
                if (c == 'M') {
                    startMouse = getPos(i, j);
                } else if (c == 'C') {
                    startCat = getPos(i, j);
                } else if (c == 'F') {
                    food = getPos(i, j);
                }
            }
        }
        int total = rows * cols;
        degrees = new int[total][total][2];
        results = new int[total][total][2][2];
        Queue<int[]> queue = new ArrayDeque<int[]>();
        // 计算每个状态的度
        for (int mouse = 0; mouse < total; mouse++) {
            int mouseRow = mouse / cols, mouseCol = mouse % cols;
            if (grid[mouseRow].charAt(mouseCol) == '#') {
                continue;
            }
            for (int cat = 0; cat < total; cat++) {
                int catRow = cat / cols, catCol = cat % cols;
                if (grid[catRow].charAt(catCol) == '#') {
                    continue;
                }
                degrees[mouse][cat][MOUSE_TURN]++;
                degrees[mouse][cat][CAT_TURN]++;
                for (int[] dir : dirs) {
                    for (int row = mouseRow + dir[0], col = mouseCol + dir[1], jump = 1; row >= 0 && row < rows && col >= 0 && col < cols && grid[row].charAt(col) != '#' && jump <= mouseJump; row += dir[0], col += dir[1], jump++) {
                        int nextMouse = getPos(row, col), nextCat = getPos(catRow, catCol);
                        degrees[nextMouse][nextCat][MOUSE_TURN]++;
                    }
                    for (int row = catRow + dir[0], col = catCol + dir[1], jump = 1; row >= 0 && row < rows && col >= 0 && col < cols && grid[row].charAt(col) != '#' && jump <= catJump; row += dir[0], col += dir[1], jump++) {
                        int nextMouse = getPos(mouseRow, mouseCol), nextCat = getPos(row, col);
                        degrees[nextMouse][nextCat][CAT_TURN]++;
                    }
                }
            }
        }
        // 猫和老鼠在同一个单元格，猫获胜
        for (int pos = 0; pos < total; pos++) {
            int row = pos / cols, col = pos % cols;
            if (grid[row].charAt(col) == '#') {
                continue;
            }
            results[pos][pos][MOUSE_TURN][0] = CAT_WIN;
            results[pos][pos][MOUSE_TURN][1] = 0;
            results[pos][pos][CAT_TURN][0] = CAT_WIN;
            results[pos][pos][CAT_TURN][1] = 0;
            queue.offer(new int[]{pos, pos, MOUSE_TURN});
            queue.offer(new int[]{pos, pos, CAT_TURN});
        }
        // 猫和食物在同一个单元格，猫获胜
        for (int mouse = 0; mouse < total; mouse++) {
            int mouseRow = mouse / cols, mouseCol = mouse % cols;
            if (grid[mouseRow].charAt(mouseCol) == '#' || mouse == food) {
                continue;
            }
            results[mouse][food][MOUSE_TURN][0] = CAT_WIN;
            results[mouse][food][MOUSE_TURN][1] = 0;
            results[mouse][food][CAT_TURN][0] = CAT_WIN;
            results[mouse][food][CAT_TURN][1] = 0;
            queue.offer(new int[]{mouse, food, MOUSE_TURN});
            queue.offer(new int[]{mouse, food, CAT_TURN});
        }
        // 老鼠和食物在同一个单元格且猫和食物不在同一个单元格，老鼠获胜
        for (int cat = 0; cat < total; cat++) {
            int catRow = cat / cols, catCol = cat % cols;
            if (grid[catRow].charAt(catCol) == '#' || cat == food) {
                continue;
            }
            results[food][cat][MOUSE_TURN][0] = MOUSE_WIN;
            results[food][cat][MOUSE_TURN][1] = 0;
            results[food][cat][CAT_TURN][0] = MOUSE_WIN;
            results[food][cat][CAT_TURN][1] = 0;
            queue.offer(new int[]{food, cat, MOUSE_TURN});
            queue.offer(new int[]{food, cat, CAT_TURN});
        }
        // 拓扑排序
        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int mouse = state[0], cat = state[1], turn = state[2];
            int result = results[mouse][cat][turn][0];
            int moves = results[mouse][cat][turn][1];
            List<int[]> prevStates = getPrevStates(mouse, cat, turn);
            for (int[] prevState : prevStates) {
                int prevMouse = prevState[0], prevCat = prevState[1], prevTurn = prevState[2];
                if (results[prevMouse][prevCat][prevTurn][0] == UNKNOWN) {
                    boolean canWin = (result == MOUSE_WIN && prevTurn == MOUSE_TURN) || (result == CAT_WIN && prevTurn == CAT_TURN);
                    if (canWin) {
                        results[prevMouse][prevCat][prevTurn][0] = result;
                        results[prevMouse][prevCat][prevTurn][1] = moves + 1;
                        queue.offer(new int[]{prevMouse, prevCat, prevTurn});
                    } else {
                        degrees[prevMouse][prevCat][prevTurn]--;
                        if (degrees[prevMouse][prevCat][prevTurn] == 0) {
                            int loseResult = prevTurn == MOUSE_TURN ? CAT_WIN : MOUSE_WIN;
                            results[prevMouse][prevCat][prevTurn][0] = loseResult;
                            results[prevMouse][prevCat][prevTurn][1] = moves + 1;
                            queue.offer(new int[]{prevMouse, prevCat, prevTurn});
                        }
                    }
                }
            }
        }
        return results[startMouse][startCat][MOUSE_TURN][0] == MOUSE_WIN && results[startMouse][startCat][MOUSE_TURN][1] <= MAX_MOVES;
    }

    public List<int[]> getPrevStates(int mouse, int cat, int turn) {
        List<int[]> prevStates = new ArrayList<int[]>();
        int mouseRow = mouse / cols, mouseCol = mouse % cols;
        int catRow = cat / cols, catCol = cat % cols;
        int prevTurn = turn == MOUSE_TURN ? CAT_TURN : MOUSE_TURN;
        int maxJump = prevTurn == MOUSE_TURN ? mouseJump : catJump;
        int startRow = prevTurn == MOUSE_TURN ? mouseRow : catRow;
        int startCol = prevTurn == MOUSE_TURN ? mouseCol : catCol;
        prevStates.add(new int[]{mouse, cat, prevTurn});
        for (int[] dir : dirs) {
            for (int i = startRow + dir[0], j = startCol + dir[1], jump = 1; i >= 0 && i < rows && j >= 0 && j < cols && grid[i].charAt(j) != '#' && jump <= maxJump; i += dir[0], j += dir[1], jump++) {
                int prevMouseRow = prevTurn == MOUSE_TURN ? i : mouseRow;
                int prevMouseCol = prevTurn == MOUSE_TURN ? j : mouseCol;
                int prevCatRow = prevTurn == MOUSE_TURN ? catRow : i;
                int prevCatCol = prevTurn == MOUSE_TURN ? catCol : j;
                int prevMouse = getPos(prevMouseRow, prevMouseCol);
                int prevCat = getPos(prevCatRow, prevCatCol);
                prevStates.add(new int[]{prevMouse, prevCat, prevTurn});
            }
        }
        return prevStates;
    }

    public int getPos(int row, int col) {
        return row * cols + col;
    }
}
