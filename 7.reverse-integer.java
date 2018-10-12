/*
 * [7] Reverse Integer
 *
 * https://leetcode-cn.com/problems/reverse-integer/description/
 *
 * algorithms
 * Easy (28.98%)
 * Total Accepted:    42.6K
 * Total Submissions: 147K
 * Testcase Example:  '123'
 *
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * 
 * 示例 1:
 * 
 * 输入: 123
 * 输出: 321
 * 
 * 
 * 示例 2:
 * 
 * 输入: -123
 * 输出: -321
 * 
 * 
 * 示例 3:
 * 
 * 输入: 120
 * 输出: 21
 * 
 * 
 * 注意:
 * 
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 * 
 */
class Solution {
    public int reverse(int x) {
        boolean isNeg = x < 0 ? true : false;

        String str = String.valueOf(x);
        if(isNeg) str = str.substring(1);
        Long result = func(str);

        if (result > Integer.MAX_VALUE) {
            return 0;
        }
        if (0 - result < Integer.MIN_VALUE) {
            return 0;
        }
        return isNeg ?  0-result.intValue(): result.intValue();
    }

    private long func(String x) {
        if (x.length() == 1)
            return Long.valueOf(x);
        long t = Long.valueOf(x.charAt(0) + "");
        long y = func(x.substring(1));

        long result = t + y * 10;
        return result;
    }
}
