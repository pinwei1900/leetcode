/*
 * [29] Divide Two Integers
 *
 * https://leetcode-cn.com/problems/divide-two-integers/description/
 *
 * algorithms
 * Medium (15.27%)
 * Total Accepted:    4.3K
 * Total Submissions: 28.1K
 * Testcase Example:  '10\n3'
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 
 * 示例 1:
 * 
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 
 * 示例 2:
 * 
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 
 * 说明:
 * 
 * 
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * 
 * 
 */
class Solution {

    private long divideR(long front, long end, long result) {
        if (front == end) {
            return ++result;
        }
        if (front < end) {
            return result;
        }
        long sum = end;
        long add = 1;
        while (true) {
            sum = sum << 1;
            if (sum > front) {
                break;
            }
            add = add << 1;
        }
        sum = sum >> 1;
        return divideR(front - sum, end, result + add);
    }

    public int divide(int dividend, int divisor) {
        long numA = Math.abs(new Long(dividend));
        long numB = Math.abs(new Long(divisor));
        boolean flag = (dividend >= 0 && divisor > 0) || (dividend < 0 && divisor < 0);        
        Long result = divideR(numA, numB, 0);
        if (flag && (result > Integer.MAX_VALUE)) {
            return Integer.MAX_VALUE;
        } 
        if (!flag && new Long(0 - result) < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return flag ? result.intValue() : new Long(0 - result).intValue();
    }
}
