import java.util.Arrays;

/*
 * [4] Median of Two Sorted Arrays
 *
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (30.67%)
 * Total Accepted:    17.2K
 * Total Submissions: 56.2K
 * Testcase Example:  '[1,3]\n[2]'
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2 。
 * 
 * 请找出这两个有序数组的中位数。要求算法的时间复杂度为 O(log (m+n)) 。
 * 
 * 你可以假设 nums1 和 nums2 不同时为空。
 * 
 * 示例 1:
 * 
 * nums1 = [1, 3]  
 * nums2 = [2]
 * 
 * 中位数是 2.0
 * 
 * 
 * 示例 2:
 * 
 * nums1 = [1, 2]  
 * nums2 = [3, 4]  
 * 
 * 中位数是 (2 + 3)/2 = 2.5
 * 
 * 
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return findMedian(nums1, nums2);
    }

    private double findMedian(int[] nums1, int[] nums2) {

        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 == 0) {
            int med = len2 / 2;
            if (len2 % 2 == 0) {
                return (double) (nums2[med - 1] + nums2[med]) / 2;
            } else {
                return nums2[med];
            }
        }
        if (len2 == 0) {
            int med = len1 / 2;
            if (len1 % 2 == 0) {
                return (double) (nums1[med - 1] + nums1[med]) / 2;
            } else {
                return nums1[med];
            }
        }

        if (len1 + len2 == 2) {
            if (len1 == 2) {
                return (double) (nums1[0] + nums1[1]) / 2;
            } else if (len2 == 2) {
                return (double) (nums2[0] + nums2[1]) / 2;
            } else {
                return (double) (nums1[0] + nums2[0]) / 2;
            }
        }

        // 两种情况，一种是，每一个队列都去掉一个最大或者最小值，还有一种情况是有一个队列去掉了最大和最小值

        // 首先判断去掉最大值的
        if (nums1[len1 - 1] > nums2[len2 - 1]) {
            nums1 = Arrays.copyOfRange(nums1, 0, len1 - 1);
            len1--;
        } else {
            nums2 = Arrays.copyOfRange(nums2, 0, len2 - 1);
            len2--;
        }

        // 然后去掉最小值的
        if (len1 == 0) {
            nums2 = Arrays.copyOfRange(nums2, 1, len2);
            return findMedian(nums1, nums2);

        } else if (len2 == 0) {
            nums1 = Arrays.copyOfRange(nums1, 1, len1);
            return findMedian(nums1, nums2);
        } else {
            if (nums1[0] < nums2[0]) {
                nums1 = Arrays.copyOfRange(nums1, 1, len1);
            } else {
                nums2 = Arrays.copyOfRange(nums2, 1, len2);
            }
            return findMedian(nums1, nums2);
        }
    }
}
