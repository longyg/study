package com.yglong.leetcode.dailypractice._2021_10;

import java.util.ArrayList;
import java.util.List;

/**
 * 273. 整数转换英文表示
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/integer-to-english-words/
 */
public class Day_2021_10_11 {

    public static void main(String[] args) {
        System.out.println(numberToWords(1000000));
    }

    public static String numberToWords(int num) {
        if (num == 0) return "Zero";
        String numStr = String.valueOf(num);
        int n = numStr.length();

        // 将整数字符串分为k部分，每部分最多3个数
        // 如：1,234,567,891
        int k = n / 3;
        if (n % 3 != 0) k++;

        String[] parts = new String[k];
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        int j = k - 1;
        for (int i = n - 1; i >= 0; i--) {
            if (cnt == 3) {
                parts[j] = sb.reverse().toString();
                j--;
                sb = new StringBuilder();
                sb.append(numStr.charAt(i));
                cnt = 1;
                continue;
            }
            sb.append(numStr.charAt(i));
            cnt++;
        }
        parts[j] = sb.reverse().toString();

        String[] partStrs = new String[k];
        for (int i = k - 1; i >= 0; i--) {
            partStrs[i] = getPart(parts, i, k - 1);
        }

        sb = new StringBuilder();
        for (String s : partStrs) {
            if (!s.equals("")) {
                sb.append(s).append(" ");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }


    private static final String[] ss = new String[]{"Thousand", "Million", "Billion"};
    private static final String[] g = new String[]{"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] s2 = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] s3 = new String[]{"Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    private static String getPart(String[] parts, int i, int k) {
        List<String> l = new ArrayList<>();
        String part = parts[i];

        int n = part.length();
        if (n == 1) {
            l.add(g[Integer.parseInt(part) - 1]);
        } else if (n == 2) {
            char c1 = part.charAt(0);
            char c2 = part.charAt(1);
            if (c1 == '1') {
                l.add(s2[Integer.parseInt(part) - 10]);
            } else {
                // 2 - 9
                l.add(s3[Integer.parseInt(String.valueOf(c1)) - 1]);
                if (c2 != '0') {
                    // 1 - 9
                    l.add(g[Integer.parseInt(String.valueOf(c2)) - 1]);
                }
            }
        } else {
            char c1 = part.charAt(0);
            char c2 = part.charAt(1);
            char c3 = part.charAt(2);
            if (c1 != '0') {
                // 1 - 9
                l.add(g[Integer.parseInt(String.valueOf(c1)) - 1]);
                l.add("Hundred");
            }
            if (c2 != '0') {
                if (c2 == '1') {
                    l.add(s2[Integer.parseInt(part.substring(1)) - 10]);
                } else {
                    l.add(s3[Integer.parseInt(String.valueOf(c2)) - 1]);
                    if (c3 != '0') {
                        l.add(g[Integer.parseInt(String.valueOf(c3)) - 1]);
                    }
                }
            } else {
                if (c3 != '0') {
                    l.add(g[Integer.parseInt(String.valueOf(c3)) - 1]);
                }
            }
        }
        if (!part.equals("000") && i != k) {
            l.add(ss[k - i - 1]);
        }
        return String.join(" ", l);
    }
}
