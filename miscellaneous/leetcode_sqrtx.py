class Solution:
    MAX_SIGNED_INTEGER = 2147483647  # Maximum signed integer value (2^31 - 1 or 2147483647).
    MAX_SQRT = 46340  # Rounded down square root of max signed integer value.

    def power(x: int, n: int) -> int:
        if n == 0:
            return 1
        if n < 1:
            return x
        
        p = x
        for _ in range(0, n-1):
            p *= x
        return p
    
    def mySqrt(self, x: int) -> int:
        if x == 0:
            return 0
        if x == 1:
            return 1
        if x == Solution.MAX_SIGNED_INTEGER:
            return Solution.MAX_SQRT

        l = 0
        r = Solution.MAX_SQRT
        while r - l > 1:
            mid = int((r + l) / 2)
            squared = Solution.power(mid, 2)
            if squared == x:
                return mid
            elif squared > x :
                r = mid
            else:
                l = mid

        if Solution.power(mid, 2) < x:
            if Solution.power(mid + 1, 2) == x:
                return mid + 1
            else:
                return mid
        else:
            return mid - 1
        

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