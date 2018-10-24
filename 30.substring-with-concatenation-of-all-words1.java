import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    List<Integer> result = new ArrayList<>();
    Map<Character, List<String>> map;
    Map<Character, List<String>> tempmap = new HashMap<>();

    boolean isTrueSub(String s, int len) {
        if (s.length() == 0) {
            return true;
        }

        if (s.length() < len) {
            return false;
        }

        if (map.get(s.charAt(0)) == null) {
            return false;
        }

        List<String> charList = map.get(s.charAt(0));
        for (String str : charList) {
            boolean find = false;
            if (str.equals(s.substring(0, len))) {
                List<String> destList = new ArrayList<String>(charList);
                destList.remove(str);
                map.put(str.charAt(0), destList);
                find = isTrueSub(s.substring(len, s.length()), len);
            }
            if (find) {
                return true;
            }
        }
        return false;
    }

    Map<Character, List<String>> copyMap(Map<Character, List<String>> chaMap){
        Map<Character, List<String>> mymap = new HashMap<>();
        for (Character c : chaMap.keySet()) {
            mymap.put(c, new ArrayList<String>(chaMap.get(c)));
        }
        return mymap;
    }

    /** 记录每一个子串的位置，然后看一下一个字串后面的地方《位置，列表》<Integer,String> */
    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length == 0 || s.length() == 0 || words[0].length() == 0) {
            return result;
        }

        int wordslen = words[0].length();
        int sublen = wordslen * words.length;

        for (String str : words) {
            if (tempmap.get(str.charAt(0)) == null) {
                tempmap.put(str.charAt(0), new ArrayList());
            }
            tempmap.get(str.charAt(0)).add(str);
        }
        int len = s.length() - sublen + 1;
        for (int i = 0; i < len; i++) {
            map = copyMap(tempmap);
            if (isTrueSub(s.substring(i, i + sublen), wordslen)) {
                result.add(i);
            }
        }
        return result;
    }
}