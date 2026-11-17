class Solution:
    def mySqrt(self, x: int) -> int:
        if x == 0:
            return 0
        if x == 1:
            return 1

        ans = 0
        l = 1
        r = x  # sqrt < x
        while l <= r:
            mid = (r + l) // 2
            squared = mid * mid

            if squared == x:
                return mid
            elif squared > x:
                r = mid - 1
            else:
                l = mid + 1
                ans = mid

        return ans
        

def main():
    # x = int(input())
    x = 8

    solution = Solution()
    print(solution.mySqrt(x))


if __name__ == "__main__":
    main()

"""
Given a non-negative integer x, return the square root of x rounded down to the nearest integer. The returned integer should be non-negative as well.

You must not use any built-in exponent function or operator.

- For example, do not use pow(x, 0.5) in c++ or x ** 0.5 in python.



Example 1:

Input: x = 4
Output: 2
Explanation: The square root of 4 is 2, so we return 2.

Example 2:

Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.



Constraints:

- 0 <= x <= 2^31 - 1
"""