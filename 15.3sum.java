import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * [15] 3Sum
 *
 * https://leetcode-cn.com/problems/3sum/description/
 *
 * algorithms
 * Medium (16.10%)
 * Total Accepted:    16.3K
 * Total Submissions: 101K
 * Testcase Example:  '[-1,0,1,2,-1,-4]'
 *
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0
 * ？找出所有满足条件且不重复的三元组。
 * 
 * 注意：答案中不可以包含重复的三元组。
 * 
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 
 * 满足要求的三元组集合为：
 * [
 * ⁠ [-1, 0, 1],
 * ⁠ [-1, -1, 2]
 * ]
 * 
 * 
 */
class Solution {

    List<List<Integer>> result = new ArrayList();

    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> posList = new ArrayList<>();
        List<Integer> zeroList = new ArrayList<>();
        List<Integer> negList = new ArrayList<>();
        Set<Integer> posSet = new HashSet();
        Set<Integer> negSet = new HashSet();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                posList.add(nums[i]);
                posSet.add(nums[i]);
            } else if (nums[i] < 0) {
                negList.add(nums[i]);
                negSet.add(nums[i]);
            } else {
                zeroList.add(nums[i]);
            }
        }

        addResutlt(posList, negSet);
        addResutlt(negList, posSet);
        addHasZero(posSet,negSet,zeroList.size());

        return result;
    }

    private void addHasZero(Set<Integer> posSet ,Set<Integer> negSet ,int zeroNumber){
        if (zeroNumber > 0) {
            for (Integer pos : posSet) {
                if (negSet.contains(0 - pos)) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(0 - pos);
                    temp.add(0);
                    temp.add(pos);
                    result.add(temp);
                }
            }
            if (zeroNumber >= 3) {
                List<Integer> temp = new ArrayList<>();
                temp.add(0);
                temp.add(0);
                temp.add(0);
                result.add(temp);
            }
        }
    }

    private void addResutlt(List<Integer> list, Set<Integer> set) {
        Map<Integer, Set<String>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int max = Math.max(list.get(i), list.get(j));
                int min = Math.min(list.get(i), list.get(j));
                int k = 0 - (max + min);

                if (set.contains(k)) {
                    if (map.get(k) == null) {
                        map.put(k, new HashSet<>());
                    }
                    if (map.get(k).contains("" + min + max)) {
                        continue;
                    }
                    map.get(k).add("" + min + max);
                    List<Integer> temp = new ArrayList<>();
                    temp.add(min);
                    temp.add(max);
                    temp.add(k);
                    result.add(temp);
                }
            }
        }
    }
}
