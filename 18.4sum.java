
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Set<String> keySet = new HashSet<>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int m = j + 1, n = nums.length - 1;
                while (m < n) {
                    int sum = nums[i] + nums[j] + nums[m] + nums[n];
                    if (sum == target) {
                        List<Integer> temp = new ArrayList<>();
                        Collections.addAll(temp, nums[i], nums[j], nums[m], nums[n]);
                        if (keySet.contains(temp.toString())) {
                            m++;
                            n--;
                            continue;
                        }
                        keySet.add(temp.toString());
                        result.add(temp);
                        m++;
                        n--;
                    } else if (sum < target) {
                        m++;
                    } else {
                        n--;
                    }

                }
            }
        }
        return result;
    }
}
