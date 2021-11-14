package com.yglong.leetcode.dailypractice._2021_11;

import java.util.HashMap;
import java.util.Map;

/**
 * 488. 祖玛游戏
 * <p>
 * 难度：困难
 * <p>
 * 连接：https://leetcode-cn.com/problems/zuma-game/
 */
public class Day_2021_11_09 {

    public static void main(String[] args) {
        System.out.println(new Day_2021_11_09().findMinStep("WRRBBW", "RB"));
        System.out.println(new Day_2021_11_09().findMinStep("WWRRBBWW", "WRBRW"));
        System.out.println(new Day_2021_11_09().findMinStep("G", "GGGGG"));
        System.out.println(new Day_2021_11_09().findMinStep("RBYYBBRRB", "YRBGB"));
        System.out.println(new Day_2021_11_09().findMinStep("WWBBWBBWW", "BB"));
        System.out.println(new Day_2021_11_09().findMinStep("WWGWGW", "GWBWR"));
    }

    int INF = 0x3f3f3f3f;
    String b;
    int m;
    Map<String, Integer> map = new HashMap<>();

    public int findMinStep(String board, String hand) {
        b = hand;
        m = b.length();
        int ans = dfs(board, 1 << m);
        return ans == INF ? -1 : ans;
    }

    int dfs(String board, int cur) {
        if (board.length() == 0) return 0;
        if (map.containsKey(board)) return map.get(board);
        int ans = INF;
        int n = board.length();
        for (int i = 0; i < m; i++) {
            if (((cur >> i) & 1) == 1) continue;
            int next = (1 << i) | cur;
            for (int j = 0; j <= n; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(board.substring(0, j)).append(b.substring(i, i + 1));
                if (j != n) sb.append(board.substring(j));
                int k = j;
                while (0 <= k && k < sb.length()) {
                    char c = sb.charAt(k);
                    int l = k, r = k;
                    while (l >= 0 && sb.charAt(l) == c) l--;
                    while (r < sb.length() && sb.charAt(r) == c) r++;
                    if (r - l - 1 >= 3) {
                        sb.delete(l + 1, r);
                        k = l >= 0 ? l : r;
                    } else {
                        break;
                    }
                }
                ans = Math.min(ans, dfs(sb.toString(), next) + 1);
            }
        }
        map.put(board, ans);
        return ans;
    }

    int step = -1;
    Map<Character, Integer> mm = new HashMap<>();

    /**
     * 未实现完成
     */
    public int findMinStep1(String board, String hand) {
        for (char c : hand.toCharArray()) {
            mm.put(c, mm.getOrDefault(c, 0) + 1);
        }

        dfs1(board, 0);

        return step == 0 ? -1 : step;
    }

    private void dfs1(String board, int s) {
        if (board.length() == 0) {
            step = step == -1 ? s : Math.min(s, step);
            return;
        }

        char c = board.charAt(0);
        int start = 0, end = 0;
        for (int i = 1; i < board.length(); i++) {
            if (board.charAt(i) == c) {
                end++;
            } else {
                int hc = end - start + 1;
                String sub = board.substring(0, start) + board.substring(end + 1);
                if (hc <= 2 && mm.get(c) != null && mm.get(c) >= (3 - hc)) {
                    int cnt = mm.get(c);
                    mm.put(c, cnt - (3 - hc));
                    sub = removeStr(sub);
                    dfs(sub, s + (3 - hc));
                    mm.put(c, cnt);
                }
                start = i;
                end = i;
                c = board.charAt(i);
            }
        }
        int hc = end - start + 1;
        String sub = board.substring(0, start) + board.substring(end + 1);
        if (hc <= 2 && mm.get(c) != null && mm.get(c) >= (3 - hc)) {
            int cnt = mm.get(c);
            mm.put(c, cnt - (3 - hc));
            dfs(sub, s + (3 - hc));
            mm.put(c, cnt);
        }
    }

    private String removeStr(String board) {
        char c = board.charAt(0);
        int start = 0, end = 0;
        for (int i = 1; i < board.length(); i++) {
            if (board.charAt(i) == c) {
                end++;
            } else {
                int hc = end - start + 1;
                if (hc > 2) {
                    return removeStr(board.substring(0, start) + board.substring(end + 1));
                }
            }
        }
        int hc = end - start + 1;
        if (hc > 2) {
            return board.substring(0, start) + board.substring(end + 1);
        }
        return board;
    }
}
