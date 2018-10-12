import java.util.Map;

/*
 * [6] ZigZag Conversion
 *
 * https://leetcode-cn.com/problems/zigzag-conversion/description/
 *
 * algorithms
 * Medium (35.78%)
 * Total Accepted:    10K
 * Total Submissions: 27.9K
 * Testcase Example:  '"PAYPALISHIRING"\n3'
 *
 * 将字符串 "PAYPALISHIRING" 以Z字形排列成给定的行数：
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * 
 * 之后从左往右，逐行读取字符："PAHNAPLSIIGYIR"
 * 
 * 实现一个将字符串进行指定行数变换的函数:
 * 
 * string convert(string s, int numRows);
 * 
 * 示例 1:
 * 
 * 输入: s = "PAYPALISHIRING", numRows = 3
 * 输出: "PAHNAPLSIIGYIR"
 * 
 * 
 * 示例 2:
 * 
 * 输入: s = "PAYPALISHIRING", numRows = 4
 * 输出: "PINALSIGYAHRPI"
 * 解释:
 * 
 * P     I    N     0    6      12
 * A   L S  I G     1  5 7   11 13
 * Y A   H R        2 4  8 10   14
 * P     I          3    9      15
 * 
 */
class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;

        int total = (numRows-1) * 2;
        int[] loc = new int[total];

        int j = 0;
        for (int i = 0; i < numRows-1; i++) { loc[j++] = i; }
        for (int i = numRows-1; i > 0; i--) { loc[j++] = i; }

        Map<Integer,StringBuilder> map = new HashMap();
        for (int i = 0; i < numRows; i++) {
            map.put(i, new StringBuilder());
        }

        for (int i = 0; i < s.length(); i++) {
            StringBuilder builder = map.get(loc[i%total]);
            builder.append(s.charAt(i));
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            result.append(map.get(i));
        }
        return result.toString();
    }
}
