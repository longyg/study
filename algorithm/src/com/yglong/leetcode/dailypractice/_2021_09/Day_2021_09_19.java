package com.yglong.leetcode.dailypractice._2021_09;

/**
 * 650. 只有两个键的键盘
 * <p>
 * 难度：中等
 * <p>
 * 链接：https://leetcode-cn.com/problems/2-keys-keyboard/
 */
public class Day_2021_09_19 {
    public static void main(String[] args) {
        System.out.println(minSteps(3));
        System.out.println(minSteps(1));
        System.out.println(minSteps(4));
        System.out.println(minSteps(5));
        System.out.println(minSteps(6));
    }

    public static int minSteps(int n) {
        if (n <= 1) {
            return 0;
        }
        int curNum = 1; // 当前记事本中A的个数
        int curCopied = 0; // 当前粘贴板里A的个数
        int step = 0; // 记录执行的步骤数
        while (curNum != n) { // 当记事本里A的个数等于n时，结束循环
            // 当前还差多少个A到达目标个数n
            int gap = n - curNum;
            // 如果差的数量刚好是当前已有A的个数的整数倍，则新复制一次，再粘贴
            // 此时粘贴板里A的个数变为当前记事本A的总个数
            if (gap % curNum == 0 && gap / curNum >= 1) {
                step += 2; // 这里因为先复制再粘贴，所以步骤加2
                curCopied = curNum;
            } else {
                // 如果不是整数倍，则不复制，只粘贴，步骤加1
                step++;
            }
            // 每粘贴一次，记事本里A的个数增加当前粘贴板里A的个数
            curNum += curCopied;
        }
        return step;
    }
}
