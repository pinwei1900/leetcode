import java.util.LinkedList;

/*
 * [20] Valid Parentheses
 *
 * https://leetcode-cn.com/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (32.90%)
 * Total Accepted:    22K
 * Total Submissions: 66.9K
 * Testcase Example:  '"()"'
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 
 * 有效字符串需满足：
 * 
 * 
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 
 * 
 * 注意空字符串可被认为是有效字符串。
 * 
 * 示例 1:
 * 
 * 输入: "()"
 * 输出: true
 * 
 * 
 * 示例 2:
 * 
 * 输入: "()[]{}"
 * 输出: true
 * 
 * 
 * 示例 3:
 * 
 * 输入: "(]"
 * 输出: false
 * 
 * 
 * 示例 4:
 * 
 * 输入: "([)]"
 * 输出: false
 * 
 * 
 * 示例 5:
 * 
 * 输入: "{[]}"
 * 输出: true
 * 
 */
class Solution {
    public boolean isValid(String s) {
        int len = s.length();

        if (len % 2 != 0) {
            return false;
        }

        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            int curr =s.charAt(i);
            if (curr == '(' || curr == '[' || curr == '{') {
                stack.addFirst(curr);
            } else {
                if (stack.size() == 0) {
                    return false;
                }
                int t = stack.pollFirst();
                switch (curr) {
                    case ')':
                        if (t != '(') {
                            return false;
                        }
                        break;
                    case ']':
                        if (t != '[') {
                            return false;
                        }
                        break;
                    case '}':
                        if (t != '{') {
                            return false;
                        }
                        break;
                    default:
                        return false;
                }
            }
        }
        return stack.size() == 0;
    }
}
