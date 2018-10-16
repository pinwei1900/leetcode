/*
 * [19] Remove Nth Node From End of List
 *
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/description/
 *
 * algorithms
 * Medium (29.72%)
 * Total Accepted:    16.5K
 * Total Submissions: 55.3K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 
 * 示例：
 * 
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 
 * 
 * 说明：
 * 
 * 给定的 n 保证是有效的。
 * 
 * 进阶：
 * 
 * 你能尝试使用一趟扫描实现吗？
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr = head;
        ListNode pos = head;
        ListNode pre = null;
        while (n-- > 0) {
            curr = curr.next;
        }
        while(curr != null){
            curr = curr.next;
            pre = pos;
            pos = pos.next;
        }
        if (pos == head) {
            return head.next;
        }
        pre.next = pos.next;
        return head;
    }
}
