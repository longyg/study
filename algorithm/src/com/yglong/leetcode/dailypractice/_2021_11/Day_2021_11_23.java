package com.yglong.leetcode.dailypractice._2021_11;

/**
 * 859. 亲密字符串
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/buddy-strings/
 */
public class Day_2021_11_23 {

    public static void main(String[] args) {
        System.out.println(buddyStrings("abab", "abab"));
        System.out.println(buddyStrings("ab", "ba"));
        System.out.println(buddyStrings("aaaaaaabc", "aaaaaaacb"));
        System.out.println(buddyStrings("aa", "aa"));
        System.out.println(buddyStrings("ab", "ab"));
    }

    public static boolean buddyStrings(String s, String goal) {
        int n = s.length();
        if (n != goal.length()) return false;

        if (s.equals(goal)) {
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                int j = s.charAt(i) - 'a';
                count[j]++;
                // 当两个字符串相等时，只要有2个相同字母存在，
                // 则肯定可以通过交换这两个相同字母来而使得两个字符串依然相等
                if (count[j] > 1) {
                    return true;
                }
            }
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < goal.length(); i++) {
                if (s.charAt(i) != goal.charAt(i)) {
                    if (first == -1)
                        first = i; // 第一个不相同的
                    else if (second == -1)
                        second = i; // 第二个不相同的
                    else
                        // 如果有3个不相同的，则肯定不是亲密字符串，直接返回
                        return false;
                }
            }

            // 必须只有2个位置不同，且交换位置后也相同
            return (second != -1 && s.charAt(first) == goal.charAt(second) &&
                    s.charAt(second) == goal.charAt(first));
        }
    }
}
