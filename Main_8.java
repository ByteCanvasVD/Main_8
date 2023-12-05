public class Main {
    static final int n = 4;
    static final int MAX = 1000000;
    static int[][] dist = {
            {0, 0, 0, 0, 0},
            {0, 0, 10, 15, 20},
            {0, 10, 0, 25, 25},
            {0, 15, 25, 0, 30},
            {0, 20, 25, 30, 0}
    };
    static int[][] memo = new int[n + 1][1 << (n + 1)];
    static int fun(int i, int mask) {
        if (mask == ((1 << i) | 3))
            return dist[1][i];
        if (memo[i][mask] != 0)
            return memo[i][mask];
        int res = MAX;
        for (int j = 1; j <= n; j++)
            if ((mask & (1 << j)) != 0 && j != i && j != 1)
                res = Math.min(res, fun(j, mask & (~(1 << i))) + dist[j][i]);
        return memo[i][mask] = res;
    }
    public static void main(String[] args) {
        int ans = MAX;
        for (int i = 1; i <= n; i++)
            ans = Math.min(ans, fun(i, (1 << (n + 1)) - 1) + dist[i][1]);
        System.out.printf("The cost of most efficient tour = %d", ans);
    }
}
//                      using backtring
// import java.util.Arrays;
// import java.util.Vector;
// public class TSP {
//     static final int V = 4;
//     static void tsp(int[][] graph, boolean[] v, int currPos, int n, int count, int cost, int[] ans) {
//         if (count == n && graph[currPos][0] != 0) {
//             ans[0] = Math.min(ans[0], cost + graph[currPos][0]);
//             return;
//         }
//         for (int i = 0; i < n; i++) {
//             if (!v[i] && graph[currPos][i] != 0) {
//                 v[i] = true;
//                 tsp(graph, v, i, n, count + 1, cost + graph[currPos][i], ans);
//                 v[i] = false;
//             }
//         }
//     }
//     public static void main(String[] args) {
//         int n = 4;
//         int[][] graph = {
//                 {0, 10, 15, 20},
//                 {10, 0, 35, 25},
//                 {15, 35, 0, 30},
//                 {20, 25, 30, 0}
//         };
//         boolean[] v = new boolean[n];
//         Arrays.fill(v, false);
//         v[0] = true;
//         int[] ans = {Integer.MAX_VALUE};
//         tsp(graph, v, 0, n, 1, 0, ans);
//         System.out.println(ans[0]);
//     }
// }