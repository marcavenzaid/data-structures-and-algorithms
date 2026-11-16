class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution:
    # def deleteDuplicates(self, head: ListNode | None) -> ListNode | None:
    #     seen = set()
    #     node = head
    #     while node != None:
    #         seen.add(node.val)

    #         while node.next != None and node.next.val in seen:
    #             node.next = node.next.next

    #         node = node.next

    #     return head

    def deleteDuplicates(self, head: ListNode | None) -> ListNode | None:
        node = head
        while node != None and node.next != None:
            if node.next.val == node.val:
                node.next = node.next.next
            else:
                node = node.next
        return head

def print_list_node(head):
    node = head
    while node != None:
        print(node.val, end=" -> ")
        node = node.next
    print("None")

def main():
    head = ListNode(1)
    n2 = ListNode(1)
    n3 = ListNode(2)
    n4 = ListNode(3)
    n5 = ListNode(3)
    head.next = n2
    n2.next = n3
    n3.next = n4
    n4.next = n5

    solution = Solution()
    print_list_node(head)
    head = solution.deleteDuplicates(head)
    print_list_node(head)

if __name__ == "__main__":
    main()

"""
Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well.



Example 1:

Input: head = [1,1,2]
Output: [1,2]

Example 2:

Input: head = [1,1,2,3,3]
Output: [1,2,3]



Constraints:

- The number of nodes in the list is in the range [0, 300].
- -100 <= Node.val <= 100
- The list is guaranteed to be sorted in ascending order.
"""