package com.yglong.leetcode.dailypractice._2021._09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 68. 文本左右对齐
 * <p>
 * 难度：困难
 * <p>
 * 链接：https://leetcode-cn.com/problems/text-justification/
 */
public class Day_2021_09_09 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(fullJustify(new String[]{"a", "b", "c", "d", "e"}, 3).toArray()));
        System.out.println(Arrays.toString(fullJustify(new String[]{"a"}, 2).toArray()));
        System.out.println(Arrays.toString(fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16).toArray()));
        System.out.println(Arrays.toString(fullJustify(new String[]{"what", "must", "be", "acknowledgment", "shall", "be"}, 16).toArray()));
        System.out.println(Arrays.toString(fullJustify(new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.",
                "Art", "is", "everything", "else", "we", "do"}, 20).toArray()));
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        int len = 0;
        int i = 0, j = 0;
        while (i < n && j <= n) {
            len += j == n ? len - 1 : words[j].length();
            // 如果遍历到最后了，则添加最后一个字符串，然后退出循环
            if (j == n) {
                ans.add(getPaddingString(words, i, j, len, n, maxWidth));
                break;
            }
            // 如果当前字符串长度小于maxWidth，则继续遍历下一个元素
            if (len < maxWidth) {
                j++;
                len++;
                continue;
            }
            // 如果刚好相等，则直接添加到结果集
            if (len == maxWidth) {
                j++;
                ans.add(getSubstring(words, i, j));
            } else {
                // 如果大于maxWidth了，则先填充空格，再添加到结果集
                ans.add(getPaddingString(words, i, j, len, n, maxWidth));
            }
            i = j;
            len = 0;
        }
        return ans;
    }

    private static String getSubstring(String[] arr, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(arr[i]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static String getPaddingString(String[] arr, int start, int end, int len, int n, int maxWidth) {
        // 最后一行，或者该行只有一个单词
        // 在后面补齐空格
        StringBuilder sb = new StringBuilder();
        if (end == n || end == start + 1) {
            for (int i = start; i < end; i++) {
                sb.append(arr[i]).append(" ");
            }
            for (int i = 0; i < maxWidth - sb.length(); i++) {
                sb.append(" ");
            }
        } else {
            // 需要补空格的槽位个数
            int slot = end - start - 1;
            // 需要补空格的数量
            int q = maxWidth - (len - arr[end].length() - 1);
            // 每个槽最少需要填的空格数
            int p = q / slot;
            // 余下的均摊到前y个槽中
            int y = q % slot;
            for (int i = 0; i <= slot; i++, y--) {
                sb.append(arr[i + start]);
                if (i != slot) {
                    sb.append(" ");
                    if (p > 0) {
                        for (int j = 0; j < p; j++) {
                            sb.append(" ");
                        }
                    }
                    if (y > 0) {
                        sb.append(" ");
                    }
                }
            }
        }
        return sb.toString();
    }
}
