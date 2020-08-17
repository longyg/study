package com.yglong.leetcode.array.utils;

import java.util.List;

public class Utils {
    public static String join(int[] a, String separator) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]).append(separator);
        }
        String result = sb.substring(0, sb.length() - separator.length());
        return result;
    }

    public static String join(List<Integer> list, String separator) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString()).append(separator);
        }
        String result = sb.substring(0, sb.length() - separator.length());
        return result;
    }

    public static void main(String[] args) {
        System.out.println(join(new int[] {1, 2, 3, 4}, ","));
    }
}
