package com.yglong.leetcode.dailypractice._2021_08;

/**
 * 443. 压缩字符串
 * <p>
 * <p>
 * 给你一个字符数组 chars ，请使用下述算法压缩：
 * <p>
 * 从一个空字符串 s 开始。对于 chars 中的每组 连续重复字符 ：
 * <p>
 * 如果这一组长度为 1 ，则将字符追加到 s 中。
 * <p>
 * 否则，需要向 s 追加字符，后跟这一组的长度。
 * <p>
 * 压缩后得到的字符串 s 不应该直接返回 ，需要转储到字符数组 chars 中。
 * 需要注意的是，如果组长度为 10 或 10 以上，则在 chars 数组中会被拆分为多个字符。
 * <p>
 * <p>
 * 请在 修改完输入数组后 ，返回该数组的新长度。
 * <p>
 * <p>
 * 你必须设计并实现一个只使用常量额外空间的算法来解决此问题。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：chars = ["a","a","b","b","c","c","c"]
 * <p>
 * 输出：返回 6 ，输入数组的前 6 个字符应该是：["a","2","b","2","c","3"]
 * <p>
 * 解释：
 * <p>
 * "aa" 被 "a2" 替代。"bb" 被 "b2" 替代。"ccc" 被 "c3" 替代。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：chars = ["a"]
 * <p>
 * 输出：返回 1 ，输入数组的前 1 个字符应该是：["a"]
 * <p>
 * 解释：
 * <p>
 * 没有任何字符串被替代。
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 * <p>
 * 输出：返回 4 ，输入数组的前 4 个字符应该是：["a","b","1","2"]。
 * <p>
 * 解释：
 * <p>
 * 由于字符 "a" 不重复，所以不会被压缩。"bbbbbbbbbbbb" 被 “b12” 替代。
 * <p>
 * 注意每个数字在数组中都有它自己的位置。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= chars.length <= 2000
 * <p>
 * chars[i] 可以是小写英文字母、大写英文字母、数字或符号
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/string-compression
 */
public class Day_2021_08_21 {
    public static void main(String[] args) {
        System.out.println(compress(new char[]{'a', 'b', 'c'}));
        System.out.println(compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        System.out.println(compress(new char[]{'a'}));
        System.out.println(compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));

//        System.out.println((int) Math.log10(2));
    }

    public static int compress(char[] chars) {
        int n = chars.length;
        if (n < 2) {
            return n;
        }
        int len = 0; // 记录替换后的数组长度
        char cur = chars[0]; // 记录当前的重复字符
        int start = 0, end = start; // 记录相同字符的起始和结束位置
        int replaceIndex = 0; // 替换原数组的位置
        for (int i = 1; i <= n; i++) {
            // 如果字符重复，且没有到最后，跳过并继续检查下一个字符
            while (i < n && chars[i] == cur) {
                end = i;
                i++;
            }
            chars[replaceIndex++] = cur;
            int cnt = end - start + 1;
            len += cnt == 1 ? 1 : ((int) Math.log10(cnt)) + 2;

            if (cnt > 1) {
                String s = Integer.toString(cnt);
                char[] nc = s.toCharArray();
                System.arraycopy(nc, 0, chars, replaceIndex, nc.length);
                replaceIndex += nc.length;
            }
            start = i;
            end = i;
            if (i < n) {
                cur = chars[i];
            }
        }
        return len;
    }
}
