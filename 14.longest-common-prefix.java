/*
 * [14] Longest Common Prefix
 *
 * https://leetcode-cn.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (28.97%)
 * Total Accepted:    25K
 * Total Submissions: 86.1K
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 示例 1:
 * 
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 
 * 
 * 示例 2:
 * 
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 
 * 
 * 说明:
 * 
 * 所有输入只包含小写字母 a-z 。
 * 
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String curr = "";
        int i = 1;
        for (; i <= strs[0].length(); i++) {
            String temp = strs[0].substring(0,i);
            boolean isComm = true;
            for (int j = 1; j < strs.length; j++) {
                if (!strs[j].startsWith(temp)) {
                    isComm = false;
                    break;
                }
            }
            if (isComm) {
                curr = temp;
            } else {
                break;
            }
        }

        return curr;
    }
}
