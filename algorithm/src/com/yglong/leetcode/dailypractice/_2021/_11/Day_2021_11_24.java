package com.yglong.leetcode.dailypractice._2021._11;

import java.util.*;

/**
 * 423. 从英文中重建数字
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/reconstruct-original-digits-from-english/
 * <p>
 * <p>
 * ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"]
 */
public class Day_2021_11_24 {

    public static void main(String[] args) {
        System.out.println(originalDigits("owoztneoer"));
        System.out.println(originalDigits("fviefuro"));
    }

    private static char[] singles = new char[]{'z', 'w', 'u', 'x', 'g', 'o', 't', 'f', 's', 'i'};
    private static int[] nums = new int[]{0, 2, 4, 6, 8, 1, 3, 5, 7, 9};
    private static List<char[]> chars = new ArrayList<>();

    static {
        chars.add(new char[]{'e', 'r', 'o'}); // zero
        chars.add(new char[]{'t', 'o'}); // two
        chars.add(new char[]{'f', 'o', 'r'}); // four
        chars.add(new char[]{'s', 'i'}); // six
        chars.add(new char[]{'e', 'i', 'h', 't'}); // eight
        chars.add(new char[]{'n', 'e'}); // one
        chars.add(new char[]{'h', 'r', 'e'}); // three
        chars.add(new char[]{'i', 'v', 'e'}); // five
        chars.add(new char[]{'e', 'v', 'n'}); // seven
        chars.add(new char[]{'n', 'e'}); // nine
    }


    public static String originalDigits(String s) {
        Map<Character, Integer> m = new HashMap<>();
        for (char c : s.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }

        List<Integer> ret = new ArrayList<>();

        // 依次去掉能唯一确定一个数字的字母
        // 比如 字母 z 能唯一确定就是zero，先去掉zero，以此类推。
        for (int i = 0; i < singles.length; i++) {
            char c = singles[i];
            if (m.containsKey(c) && m.get(c) > 0) {
                int cnt = m.get(c);
                m.put(c, 0);
                for (char other : chars.get(i)) {
                    m.put(other, m.get(other) - cnt);
                }
                while (cnt-- > 0) ret.add(nums[i]);
            }
        }

        Collections.sort(ret);
        StringBuilder sb = new StringBuilder();
        for (Integer i : ret) {
            sb.append(i);
        }
        return sb.toString();
    }
}

