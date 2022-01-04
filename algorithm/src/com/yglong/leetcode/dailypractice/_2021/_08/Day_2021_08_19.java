package com.yglong.leetcode.dailypractice._2021._08;

import java.util.ArrayList;
import java.util.List;

/**
 * 345. 反转字符串中的元音字母
 * <p>
 * <p>
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入："hello"
 * <p>
 * 输出："holle"
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入："leetcode"
 * <p>
 * 输出："leotcede"
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 元音字母不包含字母 "y" 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
 */
public class Day_2021_08_19 {
    public static void main(String[] args) {
        System.out.println(reverseVowels2("hello"));
        System.out.println(reverseVowels2("leetcode"));
        System.out.println(reverseVowels2("aA"));
    }

    static String vowels = "aeiouAEIOU"; // 所有元音字母

    /**
     * 方法一：先找到所有元音字母的下标，再交换
     */
    public static String reverseVowels(String s) {
        List<Integer> vowelIndexes = new ArrayList<>();
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (isVowel(arr[i])) {
                vowelIndexes.add(i);
            }
        }

        int n = vowelIndexes.size();
        if (n < 2) {
            return s;
        }
        for (int i = 0; i < n / 2; i++) {
            swap(arr, vowelIndexes.get(i), vowelIndexes.get(n - i - 1));
        }
        return new String(arr);
    }

    private static void swap(char[] arr, int i, int j) {
        if (arr[i] != arr[j]) {
            char c = arr[i];
            arr[i] = arr[j];
            arr[j] = c;
        }
    }

    /**
     * 方法二：双指针法
     */
    public static String reverseVowels2(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0, j = n - 1;
        while (i < j) {
            if (isVowel(arr[i]) && isVowel(arr[j])) {
                swap(arr, i++, j--);
            } else {
                if (!isVowel(arr[i])) i++;
                if (!isVowel(arr[j])) j--;
            }
        }
        return String.valueOf(arr);
    }

    private static boolean isVowel(char c) {
//        return "aeiouAEIOU".indexOf(c) > -1;
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
