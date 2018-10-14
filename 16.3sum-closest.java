import java.util.Arrays;

/*
 * [16] 3Sum Closest
 *
 * https://leetcode-cn.com/problems/3sum-closest/description/
 *
 * algorithms
 * Medium (34.51%)
 * Total Accepted:    7.7K
 * Total Submissions: 22.3K
 * Testcase Example:  '[-1,2,1,-4]\n1'
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target
 * 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * 
 * 
 */
class Solution {

    private int findClosedTarget(int[] nums, int start, int end, int target, int len) {
        if (len == 0) {
            return target;
        }
        if (end - start < len) {
            return Integer.MAX_VALUE;
        }
        int value1 = findClosedTarget(nums, start + 1, end, target - nums[start], len - 1);
        int value2 = findClosedTarget(nums, start + 1, end, target, len);
        return Math.abs(value1) > Math.abs(value2) ? value2 : value1;
    }

    public int threeSumClosest(int[] nums, int target) {
        return target - findClosedTarget(nums, 0, nums.length, target, 3);
    }
}