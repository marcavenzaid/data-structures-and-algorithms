class Solution:
    def isPalindrome(self, x: int) -> bool:
        if x < 0:
            return False
        if x != 0 and x % 10 == 0:
            return False

        reversed_half = 0
        while x > reversed_half:
            reversed_half = (reversed_half * 10) + x % 10
            x = x // 10  # remove last digit

        return x == reversed_half or x == reversed_half // 10


    def isPalindromeStr(self, x: int) -> bool:
        x_str = str(x)
        for i in range(len(x_str) // 2):
            l = i
            r = len(x_str) - 1 - i
            if x_str[l] != x_str[r]:
                return False
        return True
    

def main():
    x = int(input())

    solution = Solution()
    isPalindrome = solution.isPalindromeStr(x)
    print(isPalindrome)

if __name__ == "__main__":
    main()

"""
Given an integer x, return true if x is a , and false otherwise.



Example 1:

Input: x = 121
Output: true
Explanation: 121 reads as 121 from left to right and from right to left.

Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.



Constraints:

- -2^31 <= x <= 2^31 - 1



Follow up: Could you solve it without converting the integer to a string?
"""