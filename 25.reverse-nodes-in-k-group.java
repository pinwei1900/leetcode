import java.util.ArrayList;
import java.util.LinkedList;

/*
 * [25] Reverse Nodes in k-Group
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/description/
 *
 * algorithms
 * Hard (44.78%)
 * Total Accepted:    3.4K
 * Total Submissions: 7.5K
 * Testcase Example:  '[1,2,3,4,5]\n2'
 *
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 * 
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * 
 * 示例 :
 * 
 * 给定这个链表：1->2->3->4->5
 * 
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 
 * 说明 :
 * 
 * 
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1) {
            return head;
        }

        LinkedList<ListNode> linked = new LinkedList<>();
        ListNode list = head;
        ListNode pre = head;
        while (list != null) {
            int n = k;
            while (n > 0 && list != null) {
                linked.addLast(list);
                list = list.next;
                n--;
            }
            if (n != 0) {
                break;
            }
            if (pre == head) {
                head = linked.getLast();
            }
            while (!linked.isEmpty()) {
                ListNode v = linked.pollLast();
                pre.next = v;
                pre = pre.next;
            }
            pre.next = list;
        }
        return head;
    }
}
