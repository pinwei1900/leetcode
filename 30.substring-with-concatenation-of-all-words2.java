import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * [30] Substring with Concatenation of All Words
 *
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/description/
 *
 * algorithms
 * Hard (20.16%)
 * Total Accepted:    2.1K
 * Total Submissions: 10.1K
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

    Set<String> wordSet = new HashSet<>();

    boolean swapAccept(String[] str, int start, int pos) {
        for (int i = start; i < pos; i++) {
            if (str[i] == str[pos]) {
                return false;
            }
        }
        return true;
    }

    public void swap(String[] str, int i, int j) {
        String temp = new String();
        temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public void arrange(String[] str, int st, int len) {
        if (st == len - 1) {
            String out = "";
            for (int i = 0; i < len; i++) {
                out += str[i];
            }
            wordSet.add(out);
        } else {
            for (int i = st; i < len; i++) {
                if (!swapAccept(str, st, i)) {
                    continue;
                }
                swap(str, st, i);
                arrange(str, st + 1, len);
                swap(str, st, i);
            }
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length == 0) {
            return new ArrayList();
        }
        int len = words[0].length() * words.length;
        arrange(words, 0, words.length);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= s.length() - len; i++) {
            String p = s.substring(i, i + len);
            if (wordSet.contains(p)) {
                result.add(i);
            }
        }
        return result;
    }
}
