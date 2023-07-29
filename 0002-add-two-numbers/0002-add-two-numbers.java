// *
//  * Definition for singly-linked list.
//  * public class ListNode {
//  *     int val;
//  *     ListNode next;
//  *     ListNode() {}
//  *     ListNode(int val) { this.val = val; }
//  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//  * }
    
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return sum(l1, l2, 0);
    }
    
    public ListNode sum(ListNode l1, ListNode l2, int plus) {
        if (l1 == null && l2 == null && plus == 0) {
            return null;
        }
        
        int a = 0;
        int b = 0;
        if (l1 != null) {
            a = l1.val;
        } 
        if (l2 != null) {
            b = l2.val;
        }
        
        int sum = a + b + plus;
        
        ListNode node = new ListNode(sum % 10);
            
        node.next = sum((l1 == null) ? null : l1.next, (l2 == null) ? null : l2.next, sum / 10);
        
        return node;
    }
}