class Solution:
    def longestCommonPrefix(self, strs: list[str]) -> str:
        if len(strs) == 1:
            return strs[0]

        shortest = strs[0]
        for i in range(1, len(strs)):
            if len(strs[i]) < len(shortest):
                shortest = strs[i]

        for i in range(0, len(strs)):
            if len(strs[i]) == 0:
                return ""
            for j in range(0, len(shortest)):
                if shortest[j] != strs[i][j]:
                    shortest = shortest[:j]
                    break

        return shortest

def main():
    solution = Solution()

    strs = input().split()
    print(solution.longestCommonPrefix(strs))

if __name__ == "__main__":
    main()

"""
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".



Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.



Constraints:

- 1 <= strs.length <= 200
- 0 <= strs[i].length <= 200
- strs[i] consists of only lowercase English letters if it is non-empty.
"""