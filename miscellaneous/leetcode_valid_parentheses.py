class Solution:

    PAIRS = {
        ")": "(",
        "}": "{",
        "]": "["
    }

    def isValid(self, s: str) -> bool:
        left_pairs = ["(", "{", "["]
        right_pairs = [")", "}", "]"]
        stack = []
        for c in s:
            if c in left_pairs:
                stack.append(c)
            if c in right_pairs:
                if len(stack) > 0 and stack[-1] == Solution.PAIRS[c]:
                    stack.pop()
                else:
                    return False
        return len(stack) == 0


def main():
    s = input()
    solution = Solution()
    print(solution.isValid(s))

if __name__ == "__main__":
    main()

"""
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

1. Open brackets must be closed by the same type of brackets.
2. Open brackets must be closed in the correct order.
3. Every close bracket has a corresponding open bracket of the same type.



Example 1:

Input: s = "()"

Output: true

Example 2:

Input: s = "()[]{}"

Output: true

Example 3:

Input: s = "(]"

Output: false

Example 4:

Input: s = "([])"

Output: true

Example 5:

Input: s = "([)]"

Output: false



Constraints:

- 1 <= s.length <= 10^4
- s consists of parentheses only '()[]{}'.
"""