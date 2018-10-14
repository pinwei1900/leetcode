import java.util.List;
import java.util.ArrayList;

/*
 * [17] Letter Combinations of a Phone Number
 *
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * algorithms
 * Medium (43.57%)
 * Total Accepted:    7.8K
 * Total Submissions: 17.9K
 * Testcase Example:  '"23"'
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 
 * 
 * 
 * 示例:
 * 
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * 
 */
class Solution {

    ArrayList<String> result = new ArrayList<>();

    int[][] map = new int[][] { {}, {}, { 'a', 'b', 'c' }, { 'd', 'e', 'f' }, { 'g', 'h', 'i' }, { 'j', 'k', 'l' },
            { 'm', 'n', 'o' }, { 'p', 'q', 'r', 's' }, { 't', 'u', 'v' }, { 'w', 'x', 'y', 'z' } };

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return result;
        }
        func(digits, 0, "");
        return result;
    }

    private void func(String digits, int index, String item) {
        if (index == digits.length()) {
            result.add(item);
            return;
        }
        int[] keyMap = map[digits.charAt(index) - '0'];
        for (int i = 0; i < keyMap.length; i++) {
            func(digits, index + 1, item + (char) keyMap[i]);
        }
    }
}
