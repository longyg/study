package com.yglong.leetcode.dailypractice._2021_08;

/**
 * 457. 环形数组是否存在循环
 * <p>
 * <p>
 * 存在一个不含 0 的 环形 数组nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：
 * <p>
 * <p>
 * 如果 nums[i] 是正数，向前 移动 nums[i] 步
 * <p>
 * 如果nums[i] 是负数，向后 移动 nums[i] 步
 * <p>
 * <p>
 * 因为数组是 环形 的，所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。
 * <p>
 * <p>
 * 数组中的 循环 由长度为 k 的下标序列 seq ：
 * <p>
 * <p>
 * 遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 * <p>
 * 所有 nums[seq[j]] 应当不是 全正 就是 全负
 * <p>
 * k > 1
 * <p>
 * 如果 nums 中存在循环，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [2,-1,1,2,2]
 * <p>
 * 输出：true
 * <p>
 * 解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [-1,2]
 * <p>
 * 输出：false
 * <p>
 * 解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * <p>
 * 输入：nums = [-2,1,-1,-2,-2]
 * <p>
 * 输出：false
 * <p>
 * 解释：按下标 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为 nums[1] 是正数，而 nums[2] 是负数。
 * <p>
 * 所有 nums[seq[j]] 应当不是全正就是全负。
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 5000
 * <p>
 * -1000 <= nums[i] <= 1000
 * <p>
 * nums[i] != 0
 * <p>
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(n) 且额外空间复杂度为 O(1) 的算法吗？
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/circular-array-loop
 */
public class Day_2021_08_07 {
    public static void main(String[] args) {
        System.out.println(circularArrayLoop(new int[]{2, -1, 1, 2, 2}));
        System.out.println(circularArrayLoop(new int[]{-1, 2}));
        System.out.println(circularArrayLoop(new int[]{-2, 1, -1, -2, -2}));
        System.out.println(circularArrayLoop(new int[]{3, 1, 2}));
        System.out.println(circularArrayLoop(new int[]{-1, -2, -3, -4, -5}));
        System.out.println(circularArrayLoop(new int[]{2, 2, 2, 2, 2, 4, 7}));
    }

    /**
     * 快慢指针
     */
    public static boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            // 判断是否已访问，如果是，跳过
            if (nums[i] == 0) {
                continue;
            }
            int slow = i, fast = next(nums, i);
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                if (slow == fast) { // 回到开头
                    if (slow != next(nums, slow)) { // 不是自环
                        return true;
                    } else {
                        break;
                    }
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }

            //标记已访问节点
            int add = i;
            while (nums[add] * nums[next(nums, add)] > 0) {
                int tmp = add;
                add = next(nums, add);
                nums[tmp] = 0;
            }
        }

        return false;
    }

    public static int next(int[] nums, int cur) {
        int n = nums.length;
        return ((cur + nums[cur]) % n + n) % n; // 保证返回值在 [0,n) 中
    }
}
