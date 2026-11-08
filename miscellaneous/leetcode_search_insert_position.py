class Solution:

    def searchInsert(self, nums: list[int], target: int) -> int:
        l = 0
        r = len(nums) - 1
        while l <= r:
            m = int((l + r) / 2)
            if target == nums[m]:
                return m
            elif nums[m] < target:
                l = m + 1
            else:
                r = m - 1

        if nums[r] < target:
            return r + 1
        if nums[l] > target:
            if l == 0:
                return 0
            return l - 1
        return m

def main():
    nums = [1,3,5,6]
    target = 5

    solution = Solution()

    print(solution.searchInsert(nums, target))

if __name__ == "__main__":
    main()

"""
Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.



Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2

Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1

Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4



Constraints:

- 1 <= nums.length <= 10^4
- -10^4 <= nums[i] <= 10^4
- nums contains distinct values sorted in ascending order.
- -10^4 <= target <= 10^4
"""