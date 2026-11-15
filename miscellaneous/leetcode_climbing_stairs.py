import math

class Solution:
    def factorial(x: int) -> int:
        if x == 0:
            return 1

        total = 1
        for i in range(1, x + 1):
            total *= i

        return total

    # def permutation(n, r):
    #     if r < 0 or r > n:
    #         raise ValueError("Invalid values for n and r. Ensure that 0 <= r <= n.")
    #     return math.floor(Solution.factorial(n) / Solution.factorial(n - r))

    def combination(n, r):
        if r < 0 or r > n:
            raise ValueError("Invalid values for n and r. Ensure that 0 <= r <= n.")
        return math.floor(Solution.factorial(n) / (Solution.factorial(r) * Solution.factorial(n - r)))

    def climbStairs(self, n: int) -> int:
        total = 1
        for i in range(1, math.floor(n/2) + 1):
            x = i + (n - 2*i) 
            total += Solution.combination(x, i)
        return total

def main():
    # n = int(input())
    n = 5

    solution = Solution()
    print(solution.climbStairs(1))
    print(solution.climbStairs(2))
    print(solution.climbStairs(3))
    print(solution.climbStairs(4))
    print(solution.climbStairs(5))
    print(solution.climbStairs(6))
    print(solution.climbStairs(7))

if __name__ == "__main__":
    main()

"""
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step



Constraints:

- 1 <= n <= 45
"""