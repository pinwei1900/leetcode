/*
 * [31] Next Permutation
 *
 * https://leetcode-cn.com/problems/next-permutation/description/
 *
 * algorithms
 * Medium (27.48%)
 * Total Accepted:    4.5K
 * Total Submissions: 16.5K
 * Testcase Example:  '[1,2,3]'
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 
 * 必须原地修改，只允许使用额外常数空间。
 * 
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 */
class Solution {
    void swap(int[] nums, int p1, int p2) {
        int temp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = temp;
    }

    /**
     * 思路：
     * 1，从后往前找逆序 
     * 2，从这个逆序中找到一个大于当前位置元素的最小值 
     * 3，交换这个最小值和当前位置元素
     * 4，对当前位置以后的数据从小到大排序
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len == 0)
            return;

        int i = len - 1;
        boolean flag = false;
        for (; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                flag = true;
                break;
            }
        }
        if (i == 0) {
            for (; i < len/2; i++) {
                swap(nums ,i ,len -1 - i);
            }
            return;
        }
        int z = i;
        for (int j = i; j < len; j++) {
            if (nums[j] > nums[i - 1]) {
                if (nums[j] < nums[z]) {
                    z = j;
                }
            }
        }
        swap(nums, i - 1, z);
        Arrays.sort(nums ,i ,len);
    }
}
