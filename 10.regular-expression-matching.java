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

    private LinkedList<String> split(String p) {
        LinkedList<String> result = new LinkedList();
        for (int i = p.length() - 1; i >= 0; i--) {
            char curr = p.charAt(i);
            if (curr == '*') {
                /* 添加'[a-z]*' */
                result.add(String.valueOf(p.charAt(--i)) + curr);
            } else {
                /* 添加'[a-z.]' */
                result.add(String.valueOf(curr));
            }
        }
        return result;
    }

    private boolean match(String s, LinkedList<String> pattern) {

        /* 成功 */
        if (pattern.size() == 0 && s.length() == 0) { return true; }
        /* 字符串剩余 */
        if (pattern.size() == 0 && s.length() != 0) { return false; }

        String last = pattern.removeLast();
        char ch = last.charAt(0);
        int len = s.length();
        
        if (last.length() == 1) {
            if (len == 0) {
                return false;
            }

            if (s.charAt(0) != ch && ch != '.') {
                return false;
            }

            return match(s.substring(1, len), pattern);

            // if (len > 0 && (s.charAt(0) == ch || ch == '.')) {
            //     return match(s.substring(1, len), pattern);
            // }
            // return false;
        } else {
            int skipLen = ch == '.' ? len : findFirstCharLenth(s ,ch);
            return skipLenMatch(skipLen,s,pattern);   
        }
    }

    private boolean skipLenMatch(int length ,String s ,LinkedList<String> pattern ){
        for (int i = 0; i <= length; i++) {
            LinkedList<String> tempPattern = copyList(pattern);
            boolean isFind = match(s.substring(i, s.length()), tempPattern);
            if (isFind) {
                return true;
            }
        }
        return false;
    }

    private int findFirstCharLenth(String s ,char ch) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ch) {
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
