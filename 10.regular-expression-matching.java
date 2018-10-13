import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * [10] Regular Expression Matching
 *
 * https://leetcode-cn.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (18.49%)
 * Total Accepted:    4.7K
 * Total Submissions: 25.7K
 * Testcase Example:  '"aa"\n"a"'
 *
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * 
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 
 * 
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 * 
 * 说明:
 * 
 * 
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 
 * 
 * 示例 1:
 * 
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * 
 * 
 * 示例 3:
 * 
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 * 
 * 
 * 示例 4:
 * 
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 * 
 * 
 * 示例 5:
 * 
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 * 
 */
class Solution {
    private boolean isChar(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    private LinkedList<String> split(String p) {
        LinkedList<String> result = new LinkedList();
        for (int i = p.length() - 1; i >= 0; i--) {
            char curr = p.charAt(i);
            if (curr == '*') { // 先判断会出现两种字符的情况
                result.add(String.valueOf(p.charAt(--i)) + curr);
            } else if (isChar(curr) || curr == '.') { // 判断出现一个字符的情况
                result.add(String.valueOf(curr));
            }
        }
        return result;
    }

    private boolean match(String s, LinkedList<String> pattern) {

        /* 只有模板和字符串都为空的时候才算是匹配成功 */
        if (pattern.size() == 0 && s.length() == 0) { return true; }

        /* 模板长度为0，但是字符串还剩余说明匹配不成功 */
        if (pattern.size() == 0 && s.length() != 0) { return false; }

        String last = pattern.removeLast();
        if (last.length() == 1) {
            if (s.length() == 0) {
                return false;
            }
            if (last.charAt(0) == s.charAt(0) || last.charAt(0) == '.') {
                return match(s.substring(1, s.length()), pattern);
            }
            return false;
        } else {
            char ch = last.charAt(0);
            if (ch == '.') {
                for (int i = 0; i <= s.length(); i++) {
                    LinkedList<String> tempPattern = copyList(pattern);
                    String temp = s.substring(i, s.length());
                    boolean isFind = match(temp, tempPattern);
                    if (isFind) {
                        return true;
                    }
                }
            } else if (isChar(ch)) {
                /* 模板需跳过 */
                if (s.length() == 0 || s.charAt(0) != ch) {
                    return match(s, pattern);
                }

                /* 每次跳过 0 ~ charLen 个字符 */
                if (s.charAt(0) == ch) {
                    int charLen = findFirstCharLenth(s);
                    for (int i = 0; i <= charLen; i++) {
                        LinkedList<String> tempPattern = copyList(pattern);
                        boolean isFind = match(s.substring(i, s.length()), tempPattern);
                        if (isFind) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private int findFirstCharLenth(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(0)) {
                return i;
            }
        }
        return s.length();
    }

    private LinkedList<String> copyList(LinkedList<String> list) {
        LinkedList<String> result = new LinkedList<String>();
        for (String str : list) {
            result.add(str);
        }
        return result;
    }

    public boolean isMatch(String s, String p) {
        if (p.length() != 0 && p.charAt(0) == '*') {
            return false;
        }
        LinkedList<String> pattern = split(p);
        return match(s, pattern);
    }
}
