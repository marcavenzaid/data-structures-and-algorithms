class Solution:
    def smallerNumbersThanCurrent(self, nums: list[int]) -> list[int]:
        smaller_than = [0 for _ in range(101)]

        for num in nums:
            smaller_than[num] += 1

        for i in range(1, 101):
            smaller_than[i] += smaller_than[i - 1]

        result = []
        for num in nums:
            if num == 0:
                result.append(0)
            else:
                result.append(smaller_than[num - 1])

        return result

    # This is working, but slower O(n logn).
    # def smallerNumbersThanCurrent(self, nums: list[int]) -> list[int]:
    #     occurrences = {}
    #     for num in nums:
    #         if num in occurrences:
    #             occurrences[num] = occurrences[num] + 1
    #         else:
    #             occurrences[num] = 1

    #     sorted_occurences = dict(sorted(occurrences.items()))
    #     smaller_than = {}
    #     occurrences_sum = 0
    #     for k, v in sorted_occurences.items():
    #         smaller_than[k] = occurrences_sum
    #         occurrences_sum = occurrences_sum + v

    #     result = []
    #     for num in nums:
    #         result.append(smaller_than[num])
        
    #     return result

def main():
    nums = list(map(int, input().split()))

    solution = Solution()
    print(solution.smallerNumbersThanCurrent(nums))

if __name__ == "__main__":
    main()


"""
Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.



Example 1:

Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation:
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1).
For nums[3]=2 there exist one smaller number than it (1).
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).

Example 2:

Input: nums = [6,5,4,8]
Output: [2,1,0,3]

Example 3:

Input: nums = [7,7,7,7]
Output: [0,0,0,0]



Constraints:

- 2 <= nums.length <= 500
- 0 <= nums[i] <= 100
"""