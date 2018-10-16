/*
 * [21] Merge Two Sorted Lists
 *
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
 *
 * algorithms
 * Easy (49.39%)
 * Total Accepted:    22.5K
 * Total Submissions: 45.5K
 * Testcase Example:  '[1,2,4]\n[1,3,4]'
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * 
 * 示例：
 * 
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) { return l2; }
        if (l2 == null) { return l1; }
        ListNode head = l1;
        ListNode pre = head;
        while (l2 != null) {
            if (l2.val < l1.val) {
                ListNode temp = l2;
                l2 = l2.next;

                if (l1 == head) {
                    temp.next = head;
                    head = pre = temp;
                    continue;
                }

                temp.next = pre.next;
                pre.next = temp;
                pre = temp;
            } else {
                while (l1 != null && l1.val <= l2.val) {
                    pre = l1;
                    l1 = l1.next;
                }
                
                if (l1 == null) {
                    pre.next = l2;
                    return head;
                }
            }

        }
        return head;
    }
}
