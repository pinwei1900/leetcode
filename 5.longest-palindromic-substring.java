import java.util.Set;

/*
 * [5] Longest Palindromic Substring
 *
 * https://leetcode-cn.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (22.42%)
 * Total Accepted:    18.5K
 * Total Submissions: 82.5K
 * Testcase Example:  '"babad"'
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * 
 * 示例 1：
 * 
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 
 * 
 * 示例 2：
 * 
 * 输入: "cbbd"
 * 输出: "bb"
 * 
 * 
 */
class Solution {

    public String longestPalindrome(String s) {
        if(isPalindromic(s)) return s;

        String result = "";
        for (int i = 0; i < s.length(); i++) {
            
            if (s.substring(i).length() < result.length()) {
                continue;
            }

            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i ,j + 1);
                if(isPalindromic(sub)){
                    if(sub.length() > result.length()){
                        result = sub;
                    }
                }
            }    
        }
        return result;
    }

    private boolean isPalindromic(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
