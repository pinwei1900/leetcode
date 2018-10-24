/*
 * [28] Implement strStr()
 *
 * https://leetcode-cn.com/problems/implement-strstr/description/
 *
 * algorithms
 * Easy (35.96%)
 * Total Accepted:    20.4K
 * Total Submissions: 56.7K
 * Testcase Example:  '"hello"\n"ll"'
 *
 * 实现 strStr() 函数。
 * 
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置
 * (从0开始)。如果不存在，则返回  -1。
 * 
 * 示例 1:
 * 
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 
 * 
 * 示例 2:
 * 
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 
 * 
 * 说明:
 * 
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * 
 */
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0 || haystack.equals(needle)) {
            return 0;
        }
        int len = haystack.length() - needle.length();
        for (int i = 0; i <= len; i++) {
            Integer index = null;
            if (haystack.charAt(i) == needle.charAt(0)) {
                index = i;
            }
            if (index != null) {
                boolean issuss = true;
                for (int j = 0; j < needle.length(); j++) {
                    if (haystack.charAt(index++) != needle.charAt(j)) {
                        issuss = false;
                    }   
                }
                if (issuss) {
                    return index - needle.length();
                }
            }
        }
        return -1;
    }
}
