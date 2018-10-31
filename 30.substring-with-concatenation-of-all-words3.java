import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * [30] Substring with Concatenation of All Words
 *
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/description/
 *
 * algorithms
 * Hard (20.31%)
 * Total Accepted:    2K
 * Total Submissions: 9.9K
 * Testcase Example:  '"barfoothefoobarman"\n["foo","bar"]'
 *
 * 给定一个字符串 s 和一些长度相同的单词 words。在 s 中找出可以恰好串联 words 中所有单词的子串的起始位置。
 * 
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 
 * 示例 1:
 * 
 * 输入:
 * ⁠ s = "barfoothefoobarman",
 * ⁠ words = ["foo","bar"]
 * 输出: [0,9]
 * 解释: 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 
 * 
 * 示例 2:
 * 
 * 输入:
 * ⁠ s = "wordgoodstudentgoodword",
 * ⁠ words = ["word","student"]
 * 输出: []
 * 
 * 
 */
class Solution {
    boolean accept(String[] words, String subStr, int len) {
        String[] splits = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            splits[i] = subStr.substring(i * len, (i + 1) * len);
        }
        Arrays.sort(words);
        Arrays.sort(splits);
        for (int i = 0; i < words.length; i++) {
            if (!words[i].equals(splits[i])) {
                return false;
            }
        }
        return true;
    }
    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length == 0) {
            return new ArrayList();
        }
        List<Integer> result = new ArrayList<>();
        int len = words[0].length() * words.length;
        int wordLen = words[0].length();
        for (int i = 0; i <= s.length() - len; i++) {
            String p = s.substring(i, i + len);
            if (accept(words, p ,wordLen)) {
                result.add(i);
            }
        }
        return result;
    }
}
