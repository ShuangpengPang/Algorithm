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

    int UNKNOWN = 0, MOUSE_WIN = 1, CAT_WIN = 2;
    int MOUSE_TURN = 0, CAT_TURN = 1;
    static final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static final int MAX_MOVES = 1000;
    String[] grid;
    int rows = 0, cols = 0;
    int mouseJump = 0, catJump = 0;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length();
        this.mouseJump = mouseJump;
        this.catJump = catJump;
        int total = rows * cols;
        int[][][][] result = new int[total][total][2][2];
        int[][][] degree = new int[total][total][2];
        int startMouse = -1, startCat = -1, food = -1;
        for (int i = 0; i < total && (startMouse == -1 || startCat == -1 || food == -1); ++i) {
            int r = i / cols, c = i % cols;
            char ch = grid[r].charAt(c);
            if (ch == 'M') {
                startMouse = i;
            } else if (ch == 'C') {
                startCat = i;
            } else if (ch == 'F') {
                food = i;
            }
        }
        for (int m = 0; m < total; ++m) {
            int mouseRow = m / cols, mouseCol = m % cols;
            if (grid[mouseRow].charAt(mouseCol) == '#') {
                continue;
            }
            for (int c = 0; c < total; ++c) {
                int catRow = c / cols, catCol = c % cols;
                if (grid[catRow].charAt(catCol) == '#') {
                    continue;
                }
                ++degree[m][c][MOUSE_TURN];
                ++degree[m][c][CAT_TURN];
                for (int[] dir : dirs) {
                    for (int nextRow = mouseRow + dir[0], nextCol = mouseCol + dir[1], jump = 1; nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && grid[nextRow].charAt(nextCol) != '#' && jump <= mouseJump; nextRow += dir[0], nextCol += dir[1], ++jump) {
                        ++degree[m][c][MOUSE_TURN];
                    }
                    for (int nextRow = catRow + dir[0], nextCol = catCol + dir[1], jump = 1; nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols && grid[nextRow].charAt(nextCol) != '#' && jump <= catJump; nextRow += dir[0], nextCol += dir[1], ++jump) {
                        ++degree[m][c][CAT_TURN];
                    }
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        for (int m = 0; m < total; ++m) {
            int mouseRow = m / cols, mouseCol = m % cols;
            if (grid[mouseRow].charAt(mouseCol) == '#' || m == food) {
                continue;
            }
            result[m][m][MOUSE_TURN][0] = CAT_WIN;
            result[m][m][MOUSE_TURN][1] = 0;
            result[m][m][CAT_TURN][0] = CAT_WIN;
            result[m][m][CAT_TURN][1] = 0;
            queue.offer(new int[]{m, m, MOUSE_TURN});
            queue.offer(new int[]{m, m, CAT_TURN});
        }
        for (int m = 0; m < total; ++m) {
            int mouseRow = m / cols, mouseCol = m % cols;
            if (grid[mouseRow].charAt(mouseCol) == '#' || m == food) {
                continue;
            }
            result[m][food][MOUSE_TURN][0] = CAT_WIN;
            result[m][food][MOUSE_TURN][1] = 0;
            result[m][food][CAT_TURN][0] = CAT_WIN;
            result[m][food][CAT_TURN][1] = 0;
            queue.offer(new int[]{m, food, MOUSE_TURN});
            queue.offer(new int[]{m, food, CAT_TURN});
        }
        for (int c = 0; c < total; ++c) {
            int catRow = c / cols, catCol = c % cols;
            if (grid[catRow].charAt(catCol) == '#' || c == food) {
                continue;
            }
            result[food][c][MOUSE_TURN][0] = MOUSE_WIN;
            result[food][c][MOUSE_TURN][1] = 0;
            result[food][c][CAT_TURN][0] = MOUSE_WIN;
            result[food][c][CAT_TURN][1] = 0;
            queue.offer(new int[]{food, c, MOUSE_TURN});
            queue.offer(new int[]{food, c, CAT_TURN});
        }
        while (!queue.isEmpty() && result[startMouse][startCat][MOUSE_TURN][0] == UNKNOWN) {
            int[] tuple = queue.poll();
            int m = tuple[0], c = tuple[1], t = tuple[2];
            int winner = result[m][c][t][0];
            int moves = result[m][c][t][1];
            for (int[] state : getPreviousState(m, c, t)) {
                int mouseState = state[0], catState = state[1], preTurn = state[2];
                if (result[mouseState][catState][preTurn][0] == UNKNOWN) {
                    if ((preTurn == MOUSE_TURN && winner == MOUSE_WIN) || (preTurn == CAT_TURN && winner == CAT_WIN)) {
                        result[mouseState][catState][preTurn][0] = winner;
                        result[mouseState][catState][preTurn][1] = moves + 1;
                        queue.offer(new int[]{mouseState, catState, preTurn});
                    } else {
                        --degree[mouseState][catState][preTurn];
                        if (degree[mouseState][catState][preTurn] == 0) {
                            result[mouseState][catState][preTurn][0] = preTurn == MOUSE_TURN ? CAT_WIN : MOUSE_WIN;
                            result[mouseState][catState][preTurn][1] = moves + 1;
                            queue.offer(new int[]{mouseState, catState, preTurn});
                        }
                    }
                }
            }
        }
        return result[startMouse][startCat][MOUSE_TURN][0] == MOUSE_WIN && result[startMouse][startCat][MOUSE_TURN][1] <= MAX_MOVES;
    }

    private List<int[]> getPreviousState(int m, int c, int t) {
        int preTurn = t == MOUSE_TURN ? CAT_TURN : MOUSE_TURN;
        int mouseRow = m / cols, mouseCol = m % cols;
        int catRow = c / cols, catCol = c % cols;
        int row = preTurn == MOUSE_TURN ? mouseRow : catRow;
        int col = preTurn == MOUSE_TURN ? mouseCol : catCol;
        int maxJump = preTurn == MOUSE_TURN ? mouseJump : catJump;
        List<int[]> previousState = new ArrayList<>();
        previousState.add(new int[]{m, c, preTurn});
        for (int[] dir : dirs) {
            for (int i = row + dir[0], j = col + dir[1], jump = 1; jump <= maxJump && i >= 0 && i < rows && j >= 0 && j < cols && grid[i].charAt(j) != '#'; i += dir[0], j += dir[1], ++jump) {
                int mouseState = preTurn == MOUSE_TURN ? i * cols + j : m;
                int catState = preTurn == MOUSE_TURN ? c : i * cols + j;
                previousState.add(new int[]{mouseState, catState, preTurn});
            }
        }
        return previousState;
    }
}

class Problem1728CatAndMouseII2 {

    static int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        int M = grid.length, N = grid[0].length();
        int mouse = 0, cat = 0, food = 0;
        for (int x = 0; x < M; ++x) {
            for (int y = 0; y < N; ++y) {
                char ch = grid[x].charAt(y);
                if (ch == 'M') mouse = x * N + y;
                else if (ch == 'C') cat = x * N + y;
                else if (ch == 'F') food = x * N + y;
            }
        }
        // mdp[i][j] 表示 老鼠和猫分别在 i,j 点时，老鼠的胜负状态
        // cdp[i][j] 表示 老鼠和猫分别在 i,j 点时，猫的胜负状态
        // 状态分三种 1必胜 -1必败 0未知
        int[][] mdp = new int[M * N][M * N], cdp = new int[M * N][M * N];
        // 初始化结束边界状态
        for (int i = 0; i < M * N; ++i) {
            if (i == food) continue;
            // 两者到达同一点，老鼠必败，猫必胜
            mdp[i][i] = -1;
            cdp[i][i] = 1;
            // 老鼠到达食物，老鼠必胜，猫必败
            mdp[food][i] = 1;
            cdp[food][i] = -1;
            // 猫到达食物，老鼠必败，猫必胜
            mdp[i][food] = -1;
            cdp[i][food] = 1;
        }
        // 时光倒流大法，从结束状态，往前转移，最终目标是求初始状态mdp[mouse][cat]是否必胜
        for (int round = 0; round < 1000; ++round) {
            boolean changed = false;// 标记这一轮是否有状态发生了改变
            for (int i = 0; i < M * N; ++i) {
                int mx = i / N, my = i % N;
                if (grid[mx].charAt(my) == '#') continue;
                for (int j = 0; j < M * N; ++j) {
                    int cx = j / N, cy = j % N;
                    if (grid[cx].charAt(cy) == '#') continue;
                    if (mdp[i][j] == 0) {// 老鼠的回合
                        boolean win = false, lose = true;
                        // 四个方向搜索相邻猫的状态，因为老鼠跳完了，就轮到猫了
                        // 根据相邻的猫的状态，转移到当前老鼠的状态
                        for (int[] d : DIRECTIONS) {
                            for (int jump = 0; jump <= mouseJump; ++jump) {
                                int mx2 = mx + d[0] * jump, my2 = my + d[1] * jump;
                                if (mx2 < 0 || mx2 >= M || my2 < 0 || my2 >= N) break;
                                if (grid[mx2].charAt(my2) == '#') break;
                                int k = mx2 * N + my2;
                                lose &= cdp[k][j] == 1;// 对方全部必胜，我们则必败
                                if (cdp[k][j] == -1) {// 对方只要有一个必败，我们则必胜
                                    win = true;
                                    break;
                                }
                            }
                            if (win) break;
                        }
                        mdp[i][j] = win ? 1 : lose ? -1 : 0;
                        if (win || lose) changed = true;
                    }
                    if (cdp[i][j] == 0) {// 猫的回合，与上面同理
                        boolean win = false, lose = true;
                        for (int[] d : DIRECTIONS) {
                            for (int jump = 0; jump <= catJump; ++jump) {
                                int cx2 = cx + d[0] * jump, cy2 = cy + d[1] * jump;
                                if (cx2 < 0 || cx2 >= M || cy2 < 0 || cy2 >= N) break;
                                if (grid[cx2].charAt(cy2) == '#') break;
                                int k = cx2 * N + cy2;
                                lose &= mdp[i][k] == 1;
                                if (mdp[i][k] == -1) {
                                    win = true;
                                    break;
                                }
                            }
                            if (win) break;
                        }
                        cdp[i][j] = win ? 1 : lose ? -1 : 0;
                        if (win || lose) changed = true;
                    }
                }
            }
            if (mdp[mouse][cat] == 1) return true;
            if (mdp[mouse][cat] == -1) return false;
            // 优化时间的关键点，200+ms --> 30+ms
            // 如果这一轮没有任何状态发生改变，那么没必要继续下一轮了，下一轮肯定还是不会变
            if (!changed) return false;
        }
        return false;// 跑满1000轮，实际上跑不满

    }
}