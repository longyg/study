package com.yglong.leetcode.dailypractice._2021._10;

import java.util.ArrayList;
import java.util.List;

/**
 * 38. 外观数列
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/count-and-say/
 */
public class Day_2021_10_15 {
    public static void main(String[] args) {
        System.out.println(countAndSay(5));
    }

    /**
     * 递归
     */
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        String s = countAndSay(n - 1);
        int len = s.length();

        // 将n-1项的整数字符串进行分组
        List<String> groups = new ArrayList<>();
        char l = s.charAt(0);
        StringBuilder sb = new StringBuilder();
        sb.append(l);
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (c == l) {
                sb.append(c);
            } else {
                groups.add(sb.toString());
                sb = new StringBuilder();
                sb.append(c);
                l = c;
            }
        }
        groups.add(sb.toString());

        // 根据对第n-1项的整数字符串的分组结果，构建第n项整数字符串
        sb = new StringBuilder();
        for (String part : groups) {
            sb.append(part.length()).append(part.charAt(0));
        }
        return sb.toString();
    }


    /**
     * 静态表
     */
//    static String[] f = new String[35];
//    static {
//        f[1] = "1";
//        for (int i = 2; i < 35; i++) {
//            String prev = f[i - 1], cur = "";
//            int m = prev.length();
//            for (int j = 0; j < m; ) {
//                int k = j + 1;
//                while (k < m && prev.charAt(j) == prev.charAt(k)) k++;
//                int cnt = k - j;
//                cur += cnt + "" + prev.charAt(j);
//                j = k;
//            }
//            f[i] = cur;
//        }
//    }
//    public static String countAndSay(int n) {
//        return f[n];
//    }
}

