class Solution:

    def addBinary(self, a: str, b: str) -> str:
        c = []
        i = len(a)-1
        j = len(b)-1
        carry = 0
        while i >= 0 or j >= 0 or carry:
            col_sum = carry
            if i >= 0:
                col_sum += int(a[i])
                i -= 1
            if j >= 0:
                col_sum += int(b[j])
                j -= 1

            c.append(col_sum % 2)
            carry = col_sum  // 2
        
        return "".join(map(str, reversed(c)))

def main():
    # a = input()
    # b = input()

    a = "1010"
    b = "1011"
    
    solution = Solution()
    print(solution.addBinary(a, b))

if __name__ == "__main__":
    main()

"""
Given two binary strings a and b, return their sum as a binary string.



Example 1:

Input: a = "11", b = "1"
Output: "100"

Example 2:

Input: a = "1010", b = "1011"
Output: "10101"



Constraints:

- 1 <= a.length, b.length <= 10^4
- a and b consist only of '0' or '1' characters.
- Each string does not contain leading zeros except for the zero itself.
"""
