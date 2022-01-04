package com.yglong.leetcode.dailypractice._2021._08;

/**
 * 541. 反转字符串 II
 * <p>
 * <p>
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
 * <p>
 * <p>
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * <p>
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abcdefg", k = 2
 * <p>
 * 输出："bacdfeg"
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：s = "abcd", k = 2
 * <p>
 * 输出："bacd"
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= s.length <= 104
 * <p>
 * s 仅由小写英文组成
 * <p>
 * 1 <= k <= 104
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-string-ii
 */
public class Day_2021_08_20 {
    public static void main(String[] args) {
        System.out.println(reverseStr("abcdefg", 2));
        System.out.println(reverseStr("abcd", 2));
    }

    /**
     * 求商，求余，分段反转
     */
    public static String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = s.length();
        int dk = 2 * k; // double k
        int x = n / dk; // 商
        int y = n % dk; // 余
        for (int i = 0; i < x; i++) {
            int start = i * dk;
            int end = start + k - 1;
            reverse2(arr, start, end);
        }
        if (y > 0) {
            int start = x * dk;
            int end = start + Math.min(y, k) - 1;
            reverse2(arr, start, end);
        }
        return String.valueOf(arr);
    }

    /**
     * 模拟
     */
    public static String reverseStr2(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return String.valueOf(arr);
    }

    /**
     * 第一种反转方法
     */
    private static void reverse(char[] arr, int start, int end) {
        int len = end - start + 1;
        for (int i = 0; i < len / 2; i++) {
            swap(arr, i + start, (len - i - 1) + start);
        }
    }

    /**
     * 第二种反转方法
     */
    private static void reverse2(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
