package com.yglong.leetcode.dailypractice._2021._08;

/**
 * 551. 学生出勤记录 I
 * <p>
 * <p>
 * 给你一个字符串 s 表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
 * <p>
 * 'A'：Absent，缺勤
 * <p>
 * 'L'：Late，迟到
 * <p>
 * 'P'：Present，到场
 * <p>
 * <p>
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * <p>
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
 * <p>
 * 学生 不会 存在 连续 3 天或 3 天以上的迟到（'L'）记录。
 * <p>
 * <p>
 * 如果学生可以获得出勤奖励，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：s = "PPALLP"
 * <p>
 * 输出：true
 * <p>
 * 解释：学生缺勤次数少于 2 次，且不存在 3 天或以上的连续迟到记录。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：s = "PPALLL"
 * <p>
 * 输出：false
 * <p>
 * 解释：学生最后三天连续迟到，所以不满足出勤奖励的条件。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= s.length <= 1000
 * <p>
 * s[i] 为 'A'、'L' 或 'P'
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/student-attendance-record-i
 */
public class Day_2021_08_17 {
    public static void main(String[] args) {
        System.out.println(checkRecord("PPALLL"));
    }

    public static boolean checkRecord(String s) {
        int aCnt = 0; // 缺席次数
        int lCnt = 0; // 连续迟到次数
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            aCnt = c == 'A' ? aCnt + 1 : aCnt;
            lCnt = c == 'L' ? lCnt + 1 : 0;
            if (aCnt >= 2 || lCnt >= 3) {
                return false;
            }
        }
        return true;
    }
}
