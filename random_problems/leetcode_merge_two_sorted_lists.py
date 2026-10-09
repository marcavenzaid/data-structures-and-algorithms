class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    def mergeTwoLists(self, list1: ListNode | None, list2: ListNode | None) -> ListNode | None:
        if list1 is None:
            return list2
        if list2 is None:
            return list1
        
        if list1.val < list2.val:
            merged = list1
            list1 = list1.next
        else:
            merged = list2
            list2 = list2.next
        merged_head = merged

        while list1 and list2:
            if list1.val < list2.val:
                merged.next = list1
                list1 = list1.next
            else:
                merged.next = list2
                list2 = list2.next

            merged = merged.next
        
        merged.next = list1 or list2

        return merged_head

def traverse_and_print_linked_list(list: ListNode):
    current = list
    while current:
        print(f"{current.val}", end=" -> ")
        current = current.next
    print("None")

def main():
    list1_node1 = ListNode(1)
    list1_node2 = ListNode(2)
    list1_node3 = ListNode(4)
    list1_node1.next = list1_node2
    list1_node2.next = list1_node3

    list2_node1 = ListNode(1)
    list2_node2 = ListNode(3)
    list2_node3 = ListNode(4)
    list2_node1.next = list2_node2
    list2_node2.next = list2_node3

    print("list1: ", end="")
    traverse_and_print_linked_list(list1_node1)
    print("list2: ", end="")
    traverse_and_print_linked_list(list2_node1)
    
    solution = Solution()
    merged = solution.mergeTwoLists(list1_node1, list2_node1)

    print("merged: ", end="")
    traverse_and_print_linked_list(merged)

if __name__ == "__main__":
    main()

"""
You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.



Example 1:

Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:

Input: list1 = [], list2 = []
Output: []

Example 3:

Input: list1 = [], list2 = [0]
Output: [0]



Constraints:

- The number of nodes in both lists is in the range [0, 50].
- -100 <= Node.val <= 100
- Both list1 and list2 are sorted in non-decreasing order.
"""