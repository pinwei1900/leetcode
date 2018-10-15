
/*
 * [18] 4Sum
 *
 * https://leetcode-cn.com/problems/4sum/description/
 *
 * algorithms
 * Medium (29.85%)
 * Total Accepted:    5.9K
 * Total Submissions: 19.8K
 * Testcase Example:  '[1,0,-1,0,-2,2]\n0'
 *
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c
 * + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 
 * 注意：
 * 
 * 答案中不可以包含重复的四元组。
 * 
 * 示例：
 * 
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 
 * 满足要求的四元组集合为：
 * [
 * ⁠ [-1,  0, 0, 1],
 * ⁠ [-2, -1, 1, 2],
 * ⁠ [-2,  0, 0, 2]
 * ]
 * 
 * 
 */
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solution {

    List<List<Integer>> result = new ArrayList<>();
    int ret;
    int size;

    Set<String> strSet = new HashSet<>();

    List<Integer> arrayToList(List<Integer> source, Integer... add) {
        List<Integer> target = new ArrayList<>();
        for (int i = 0; i < source.size(); i++) {
            target.add(source.get(i));
        }
        for (int i = 0; i < add.length; i++) {
            target.add(add[i]);
        }
        return target;
    }

    private void findClosedTarget(int[] nums, int start, List<Integer> target, int sum) {

        if (target.size() == size) {
            String tempStr = target.toString();
            if (sum != ret || strSet.contains(tempStr)) {
                return;
            }
            strSet.add(tempStr);
            result.add(target);
            return;
        }

        if (nums.length - start < size - target.size()) {
            return;
        }

        findClosedTarget(nums, start + 1, arrayToList(target, nums[start]), sum + nums[start]);
        findClosedTarget(nums, start + 1, arrayToList(target), sum);
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        ret = target;
        size = 4;

        findClosedTarget(nums, 0, new ArrayList(), 0);
        return result;
    }
}
