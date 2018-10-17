import java.util.ArrayList;
import java.util.List;

/*
 * [22] Generate Parentheses
 *
 * https://leetcode-cn.com/problems/generate-parentheses/description/
 *
 * algorithms
 * Medium (64.89%)
 * Total Accepted:    7.3K
 * Total Submissions: 11.3K
 * Testcase Example:  '3'
 *
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * 
 * 例如，给出 n = 3，生成结果为：
 * 
 * [
 * ⁠ "((()))",
 * ⁠ "(()())",
 * ⁠ "(())()",
 * ⁠ "()(())",
 * ⁠ "()()()"
 * ]
 * 
 * 
 */
class Solution {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return new ArrayList();
        }
        gene(n * 2, "");
        List<String> mainList = new ArrayList<>(result);

        return mainList;
    }

    Set<String> result = new HashSet();

    void gene(int n, String s) {
        if (n == 0) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }
        n--;
        gene(n, s + "(");
        gene(n, s + ")");
    }


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
