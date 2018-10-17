/*
 * [23] Merge k Sorted Lists
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (39.35%)
 * Total Accepted:    6.5K
 * Total Submissions: 16.6K
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 
 * 示例:
 * 
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode result = lists[0];
        for (int i = 1; i < lists.length; i++) {
            result = mergeTwoLists(result, lists[i]);
        }
        return result;
    }

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
