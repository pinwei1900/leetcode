/*
 * [2] Add Two Numbers
 *
 * https://leetcode-cn.com/problems/add-two-numbers/description/
 *
 * algorithms
 * Medium (29.24%)
 * Total Accepted:    40.9K
 * Total Submissions: 139.9K
 * Testcase Example:  '[2,4,3]\n[5,6,4]'
 *
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 
 * 示例：
 * 
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * 
 * 
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode temp = head;

        while(l1 != null && l2 != null){
            int val = l1.val + l2.val + temp.val;
            temp.val = val % 10;
            temp.next = new ListNode(val / 10);

            l1 = l1.next;
            l2 = l2.next;
            if(l1 ==null && l2 ==null && temp.next.val == 0){
                temp.next = null;
                break;
            }
            temp = temp.next;
        }

        while(l1 != null){
            int val = temp.val + l1.val;
            temp.val = val % 10;
            temp.next = new ListNode(val / 10);

            l1 = l1.next;
            if(l1 == null && temp.next.val == 0){
                temp.next = null;
                break;
            }
            temp = temp.next;
        }
        while(l2 != null){
            int val = temp.val + l2.val;
            temp.val = val % 10;
            temp.next = new ListNode(val / 10);

            l2 = l2.next;
            if(l2 == null && temp.next.val == 0){
                temp.next = null;
                break;
            }
            temp = temp.next;
        }

        return head;
    }
}
