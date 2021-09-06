package com.yglong.leetcode.dailypractice._2021_09;

/**
 * 165. 比较版本号
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/compare-version-numbers
 */
public class Day_2021_09_01 {
    public static void main(String[] args) {
        System.out.println(compareVersion("1.01", "1.001")); // 0
        System.out.println(compareVersion("1.0", "1.0.0")); // 0
        System.out.println(compareVersion("0.1", "1.1")); // -1
        System.out.println(compareVersion("1.0.1", "1")); // 1
        System.out.println(compareVersion("7.5.2.4", "7.5.3")); // -1
    }

    public static int compareVersion(String v1, String v2) {
        while (true) {
            boolean f1 = v1.contains(".");
            boolean f2 = v2.contains(".");
            int i1 = Integer.parseInt(v1.substring(0, f1 ? v1.indexOf(".") : v1.length()));
            int i2 = Integer.parseInt(v2.substring(0, f2 ? v2.indexOf(".") : v2.length()));

            if (i1 == i2) { // 如果相等，则继续判断下一个修订号
                if (!f1 && !f2) {
                    // 两个版本号都遍历到了最后一个修订号，结束
                    break;
                }
                v1 = f1 ? v1.substring(v1.indexOf(".") + 1) : "0";
                v2 = f2 ? v2.substring(v2.indexOf(".") + 1) : "0";

            } else {
                return i1 > i2 ? 1 : -1;
            }
        }
        return 0;
    }
}
