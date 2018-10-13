/*
 * [11] Container With Most Water
 *
 * https://leetcode-cn.com/problems/container-with-most-water/description/
 *
 * algorithms
 * Medium (43.63%)
 * Total Accepted:    12.7K
 * Total Submissions: 29K
 * Testcase Example:  '[1,8,6,2,5,4,8,3,7]'
 *
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为
 * (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 
 * 
 * 
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 
 * 
 * 
 * 示例:
 * 
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 * 
 */
class Solution {
    public int maxArea(int[] height) {
        int result = 0;
        int len =  height.length;
        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j > i; j--) {
                int area = Math.min(height[i], height[j]) * (j - i);
                if (area > result) {
                    result = area;
                }
            }
        }
        return result;
    }
}
